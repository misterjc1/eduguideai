package com.wouti.zoom_academia;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
@EnableJpaAuditing
public class ZoomAcademiaBackApplication {

	@Value("${server.port}")
	private int serverPort;

	@Bean
	public RestTemplate restTemplate() {

		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(3000);
		factory.setReadTimeout(3000);
		return new RestTemplate(factory);
	}

	@RequestMapping("/test")
	public String verify() {
		return "Web Application Zoom Academia BACK Started at port [ " + serverPort + " ] !";
	}

	public static void main(String[] args) {
		SpringApplication.run(ZoomAcademiaBackApplication.class, args);
	}

}
