package com.example.df;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class DemoFunctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoFunctionApplication.class, args);
	}

	@Bean
	public Supplier<String> hello() {
		return () -> "Hello, welcome to Spring Cloud Function!";
	}
	@Bean
	public Function<String, String> uppercase() {
		return String::toUpperCase;

	}
	@Bean
	public Function<String, String> reverse() {
		return str -> new StringBuilder(str).reverse().toString();
	}

}
