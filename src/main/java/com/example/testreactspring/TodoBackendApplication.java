package com.example.testreactspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.testreactspring.resource.CassandraConnector;
import com.example.testreactspring.resource.TodoRepository;

@SpringBootApplication
public class TodoBackendApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(TodoBackendApplication.class, args);
	}

	@Bean
	public TodoRepository todoRepository() {
		return new TodoRepository(new CassandraConnector().getSession());
	}
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*").allowedOrigins("http://localhost:3000");
            }
        };
    }
}
