package com.demo.ngsoft.configurations;

import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

   /* @Bean
    public OpenAPI customizeOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                        .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                        .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }*/
}
