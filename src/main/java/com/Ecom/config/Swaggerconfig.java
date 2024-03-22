package com.Ecom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;	
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Component
public class Swaggerconfig {

	
	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.Ecom.controller"))
					.paths(PathSelectors.any())
					.build().apiInfo(metadata());
	}

	private ApiInfo metadata() {
		// TODO Auto-generated method stub
		return new ApiInfoBuilder()
				.title("Ecom application -Swagger configuration")
				.description("swagger configuration for Ecom application")
				.version("1.00")
				.license("https://swagger.io/license2.0/")
				.licenseUrl("https://swagger.io/license")
				.build();
		
	}
}
