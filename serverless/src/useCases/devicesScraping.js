const axios = require("axios");
const cheerio = require("cheerio");
const { avg } = require("../utils/math");

module.exports.getAveragePriceForDevice = async (baseUrl, deviceName) => {
    const url = makeUrl(baseUrl, deviceName);

    const html = (await axios.get(url)).data;

    const prices = extractPricesFromHtml(html);

    return avg(prices);
}

const extractPricesFromHtml = (html) => {
    const PRICES_TO_FIND = 5;

    let $ = cheerio.load(html);
    $ = removeAds($);

    const products = findProducts($);

    return findFirstNProducts($, products, PRICES_TO_FIND);
};

const findFirstNProducts = ($, products, quantity) => {
    let count = 0;

    const prices = [];

    products.each((i, product) => {
        if (count >= quantity) return;
        count ++;

        const price = findPrice($, product);
        prices.push(price);
    });

    return prices;
};

const findPrice = ($, product) => {
    const priceText = $(product).children(".product-description")
        .children("[itemprop='offers']")
        .children("[itemprop='price']").text()
        .replace(".", "")
        .replace(",", ".");

    return parseFloat(priceText);
};

const findProducts = ($) => {
    return $("article[itemtype='http://schema.org/Product']");
}

const removeAds = ($) => {
    $("#store-highlighted").remove();
    return $;
};

const makeUrl = (url, deviceName) => {
    const nameWithoutBrand = removeBrandFromName(deviceName);
    const encodedName = encodeURIComponent(nameWithoutBrand);

    return url + encodedName;
};

const removeBrandFromName = (deviceName) => {
    return deviceName.toLowerCase().replace("amd", "")
        .replace("nvidia", "");
};
