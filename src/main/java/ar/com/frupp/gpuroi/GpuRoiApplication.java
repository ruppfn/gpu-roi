package ar.com.frupp.gpuroi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GpuRoiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GpuRoiApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplateBean(RestTemplateBuilder builder) {
        return builder.build();
    }
}
