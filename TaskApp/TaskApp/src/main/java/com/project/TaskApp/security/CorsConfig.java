package com.project.TaskApp.security;
//import FE BE integration
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration//clas as spring config class
public class CorsConfig {

    @Bean//
    public WebMvcConfigurer webMvcConfigurer(){//interface
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedOrigins("http://localhost:3000");
            }
        };
    }

}
//@Component on classes, Spring auto-detects it
// @Bean on methods inside @Configuration class to register custom obj