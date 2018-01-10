package com.zjs;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringCloudApplication
@RestController
@EnableAutoConfiguration
@ComponentScan
@EnableSwagger2
@EnableCaching
@EnableEurekaClient
@EnableHystrixDashboard
@EnableTurbine
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	public Docket basedocApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("Hades监控系统")
				.apiInfo(apiInfo())
				.select()
				.paths(basedocPaths())
				.build();

	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Hades监控系统")
				.description("Hades监控系统")
				.build();
	}

	@SuppressWarnings("unchecked")
	private Predicate<String> basedocPaths() {
		return or(regex("/monitor/api/v1.*"));
	}
	
}
