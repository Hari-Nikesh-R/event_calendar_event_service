package com.dosmart.event_calendar_service;

import com.dosmart.event_calendar_service.utils.TokenValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EventCalendarServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(EventCalendarServiceApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	@Bean
	public TokenValidator getTokenValidator(){
		return TokenValidator.getInstance();
	}

}
