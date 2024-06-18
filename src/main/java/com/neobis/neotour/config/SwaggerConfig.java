package com.neobis.neotour.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().addServersItem(new Server().url("https://neotour.shop"))
                .info(new Info()
                        .title("NeoTour API")
                        .version("1.0"));
    }
}
