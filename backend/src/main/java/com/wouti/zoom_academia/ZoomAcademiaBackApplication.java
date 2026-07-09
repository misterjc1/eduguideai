package com.wouti.zoom_academia;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

@RestController
@SpringBootApplication
@EnableJpaAuditing
public class ZoomAcademiaBackApplication {

	@Value("${server.port}")
	private int serverPort;

	@Bean
	FirebaseMessaging firebaseMessaging() throws IOException {

		GoogleCredentials googleCredentials = GoogleCredentials
				.fromStream(new ClassPathResource("djonan-firebase-account.json").getInputStream());
		FirebaseOptions firebaseOptions = FirebaseOptions.builder()
				.setCredentials(googleCredentials).build();
		FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "Djonan");

		return FirebaseMessaging.getInstance(app);

	}

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
