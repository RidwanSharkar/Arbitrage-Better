/* src/main/java\com\rsharkar\arbitrager/ WebConfig.java */
package com.rsharkar.Arbitrager.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/scrape-fights").allowedOrigins("http://localhost:4200"); // Allow all from the Angular port
    }
}
