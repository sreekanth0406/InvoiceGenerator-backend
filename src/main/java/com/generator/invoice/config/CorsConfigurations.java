package com.generator.invoice.config;

import java.net.http.HttpHeaders;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.generator.invoice.Utils.Constants;



@Configuration
public class CorsConfigurations implements WebMvcConfigurer{
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		registry.addMapping(Constants.API_PREFIX+"/**")
				.allowedOrigins("http://localhost:4200")
				.allowedMethods("POST","PUT","GET","DELETE","OPTIONS")
				.maxAge(3600)
				.allowedHeaders("*");
	}
}
