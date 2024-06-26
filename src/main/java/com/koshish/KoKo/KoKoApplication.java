package com.koshish.KoKo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class }, scanBasePackages = {"com.koshish.KoKo", "com.koshish.RestController",
		"com.koshish.Service", "com.koshish.Security", "com.koshish.Model", "com.koshish.Repository"})
public class KoKoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KoKoApplication.class, args);
	}

}
