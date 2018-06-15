package com.daonguyen.blogcrudapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BlogCrudApp {
	public static void main(String[] args) {
		SpringApplication.run(BlogCrudApp.class, args);
	}
}
