package com.wine.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return (container -> 
			container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"),
					new ErrorPage(HttpStatus.FORBIDDEN, "/403")));
	}
	
	//Configuração para nao utilizar o findone e o spring ja reconhecer o objeto vinho pelo codigo
	@Bean
	public DomainClassConverter<FormattingConversionService> domainClassConverter(
			FormattingConversionService conversionService) {
		return new DomainClassConverter<FormattingConversionService>(conversionService);
	}

	// redirecionar a requisicao de "/"
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/vinhos/novo");
	}
	
}