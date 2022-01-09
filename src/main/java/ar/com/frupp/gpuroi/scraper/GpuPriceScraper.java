package ar.com.frupp.gpuroi.scraper;

import ar.com.frupp.gpuroi.model.DeviceJson;
import ar.com.frupp.gpuroi.service.DeviceService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class GpuPriceScraper {

    private final Logger logger = LoggerFactory.getLogger(GpuPriceScraper.class);

    private final String endpoint;
    private final DeviceService deviceService;

    public GpuPriceScraper(@Value("${app.endpoints.gpus}") String endpoint,
                           DeviceService deviceService) {
        this.endpoint = endpoint;
        this.deviceService = deviceService;
    }

    public void scrape() {
        var devicesWithoutPrice = this.deviceService.findAllWithoutPrice();

        for (DeviceJson device: devicesWithoutPrice) {
            scrapePrice(device);
        }
    }

    private void scrapePrice(DeviceJson device) {
        var url = makeUrl(device);

        var optional = getHtmlDocument(url);

        if (optional.isEmpty()) return;

        var page = optional.get();

        var prices = getPrices(page);

        var averagePrice = calculateAveragePrice(prices);

        updatePrice(device, averagePrice);
    }

    private String makeUrl(DeviceJson device) {
        var name = device.getName().toLowerCase();

        name = removeBrandFromName(name);

        var encoded = URLEncoder.encode(name, StandardCharsets.UTF_8);

        this.logger.debug("Encoded URL for device {}: {}", device.getName(), encoded);

        return this.endpoint + encoded;
    }

    private String removeBrandFromName(String name) {
        if (name.contains("amd")) {
            this.logger.debug("Removing AMD from device name {}", name);
            name = name.replace("amd", "");
        }
        if (name.contains("nvidia")) {
            this.logger.debug("Removing NVIDIA from device name {}", name);
            name = name.replace("nvidia", "");
        }
        return name;
    }

    private Optional<Document> getHtmlDocument(String url) {
        try {
            return Optional.of(Jsoup.connect(url).get());
        } catch (IOException e) {
            this.logger.error("Couldn't get HTML document for URL: {}", url);
            return Optional.empty();
        }
    }

    private List<BigDecimal> getPrices(Document page) {
        this.logger.debug("Finding prices");
        var prices = new LinkedList<BigDecimal>();
        try {
            var elements = page.getElementsByAttributeValue("itemtype", "http://schema.org/Product");
            for (var element : elements) {
                var priceStr = element.getElementsByAttributeValue("itemprop", "price").attr("content");
                this.logger.debug("Price found: {}", priceStr);
                var price = new BigDecimal(priceStr);
                prices.add(price);
            }
        } catch (Exception e) {
            this.logger.error(e.getMessage());
            this.logger.error("Error finding prices");
        }

        this.logger.debug("Found {} prices", prices.size());

        return prices;
    }

    private int calculateAveragePrice(List<BigDecimal> prices) {
        var defaultOrMaxSize = Math.min(3, prices.size());
        var relevantPrices = prices.subList(0, defaultOrMaxSize);
        var averagePrice = relevantPrices.stream().mapToInt(BigDecimal::intValue).sum() / defaultOrMaxSize;

        this.logger.debug("Average price: {}", averagePrice);

        return averagePrice;
    }

    private void updatePrice(DeviceJson device, int price) {
        this.logger.debug("Updating price (${}) for device id: {}", price, device.getId());

        this.deviceService.updatePriceAndROI(device.getId(), BigDecimal.valueOf(price));
    }
}
