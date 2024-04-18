package com.ClothesFriends.ClothesFriendsBackEnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ClothesFriendsBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClothesFriendsBackEndApplication.class, args);
	}

}
