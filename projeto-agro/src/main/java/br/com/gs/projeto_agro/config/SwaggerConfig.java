package br.com.gs.projeto_agro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
	
	
final String AUTORIZACAO = "bearerAuth";
	
	@Bean
	OpenAPI configurarSwagger() {
		return new OpenAPI()
				
				.addSecurityItem(new SecurityRequirement().addList(AUTORIZACAO))
				
				.components(new Components().addSecuritySchemes(AUTORIZACAO, 
						new SecurityScheme()
						.name(AUTORIZACAO)
						.type(SecurityScheme.Type.HTTP)
						.scheme("bearer")
						.bearerFormat("JWT")))
				
				
				.info(new Info().title("Projeto Agro")
				.description("Este é um projeto que realiza o gerenciamento de entradas para Fazendas(Propietários, Empresas) "
						+ "Bioma,Alerta,Cultura,Cultivo,Leitura,Usuario,Sensor,Local com serviços de caching, paginação etc")
				.summary("Projeto para gestão de sua Plantação")
				.version("1.0.0")
				.termsOfService("Textão")
				.license(new License().url("/licenses")
						.name("Premium License")));
	
	

}
	}