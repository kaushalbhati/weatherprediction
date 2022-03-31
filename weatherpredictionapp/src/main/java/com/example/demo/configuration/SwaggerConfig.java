package com.example.demo.configuration;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.plugin.core.SimplePluginRegistry;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	 @Bean
	    public Docket api() {
	       return new Docket(DocumentationType.SWAGGER_2);    
	 }

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Weather Prediction API")
				.description("Weather API reference for developers")
				.termsOfServiceUrl("http://bhati.com")
				.licenseUrl("kaushalbhati2@gmail.com").version("1.0").build();
	}
}