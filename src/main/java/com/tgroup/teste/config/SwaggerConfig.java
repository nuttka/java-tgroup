package com.tgroup.teste.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private final ResponseMessage m201 = simpleMessage(201, "Created");
	private final ResponseMessage m204put = simpleMessage(204, "Updated");
	private final ResponseMessage m204del = simpleMessage(204, "Deleted");
	private final ResponseMessage m403 = simpleMessage(403, "Not authorized");
	private final ResponseMessage m404 = simpleMessage(404, "Not found");
	private final ResponseMessage m500 = simpleMessage(500, "Unexpected error");
	
	@Bean
	public Docket api() {
	return new Docket(DocumentationType.SWAGGER_2)
		.useDefaultResponseMessages(false)
		.globalResponseMessage(RequestMethod.GET, Arrays.asList(m403, m404, m500))
		.globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m403, m500))
		.globalResponseMessage(RequestMethod.PUT, Arrays.asList(m204put, m403, m404, m500))
		.globalResponseMessage(RequestMethod.DELETE, Arrays.asList(m204del, m403, m404, m500))
		.select()
		.apis(RequestHandlerSelectors.basePackage("com.tgroup.teste.controller"))
		.paths(PathSelectors.any())
		.build()
		.apiInfo(apiInfo());
	}
	
	
	private ApiInfo apiInfo() {
		return new ApiInfo("test api",
				"api with authentication and authorization", 
				"version 1.0",
				"https://github.com/nuttka/java-tgroup",
				new Contact("Lucas Augusto",
						"https://github.com/nuttka/java-tgroup",
						"lucasaaaguiar0@gmail.com"),
				"Free", 
				"https://github.com/nuttka/java-tgroup", 
				Collections.emptyList());
	}
	
	private ResponseMessage simpleMessage(int code, String msg) {
		return new ResponseMessageBuilder().code(code).message(msg).build();
	}
	
}








