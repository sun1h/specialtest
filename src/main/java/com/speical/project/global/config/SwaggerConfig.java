package com.speical.project.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	@Bean
	public GroupedOpenApi allApi() {
		return GroupedOpenApi.builder()
				.group("")
				.pathsToMatch("/**")
				.build();
	}
	@Bean
	public GroupedOpenApi loginApi() {
		return GroupedOpenApi.builder()
				.group("데이터")
				.pathsToMatch("/api/data/**")
				.build();
	}

	@Bean
	public GroupedOpenApi memberApi() {
		return GroupedOpenApi.builder()
				.group("회원")
				.pathsToMatch("/api/user/**")
				.build();
	}


	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.addServersItem(new Server().url("/"))
				.info(new Info().title("D201")
						.description("특화 프로젝트 API")
						.version("v0.0.1"));
	}

}