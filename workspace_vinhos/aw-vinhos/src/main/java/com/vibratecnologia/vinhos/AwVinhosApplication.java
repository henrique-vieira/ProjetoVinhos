package com.vibratecnologia.vinhos;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@EntityScan(basePackages = "com.vibratecnologia.vinhos.model")
@EnableJpaRepositories(basePackages = "com.vibratecnologia.vinhos.repository")
@SpringBootApplication(scanBasePackages = "com.vibratecnologia.vinhos")
public class AwVinhosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwVinhosApplication.class, args);
	}
	
	
	@Bean
	public LocaleResolver localeResolver(){
		return new FixedLocaleResolver(new Locale("pt", "BR")); //configura moedas e outras configurações para pt-BR
	}
}
