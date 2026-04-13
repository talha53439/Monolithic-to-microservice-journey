package com.example.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.function.Consumer;
import java.util.function.Function;

@SpringBootApplication
public class DemoKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoKafkaApplication.class, args);
	}
	/*@Bean
	public NewTopic topic() {
		return TopicBuilder.name("topic1")
				.partitions(10)
				.replicas(1)
				.build();
	}*/
	/*@KafkaListener(id = "myId", topics = "uppercase-out-0")
	public void listen(String in) {
		System.out.println(in);
	}

	@Bean
	public ApplicationRunner runner(KafkaTemplate<String, String> template) {
		return args -> {
			template.send("uppercase-in-0", "TAlha Md. Anik");
			template.send("uppercase-in-0", "Sanjoy Tripura");
		};
	}
	@Bean
	public Function<String, String> uppercase() {
		return value -> value.toUpperCase();
	}*/

/*	@KafkaListener(id = "myId", topics = "topic1")
	public void listen(String in) {
		System.out.println(in);
	}

	@Bean
	public ApplicationRunner runner(KafkaTemplate<String, String> template) {
		return args -> {
			template.send("topic1", "TAlha Md. Anik");
			template.send("topic1", "Sanjoy Tripura");
		};
	}*/
	@Bean
	public Function<String, String> uppercase() {
		return value -> {
			System.out.println("Message :------- "+value);
			return value.toUpperCase();
		}
				;
	}
	@Bean
	public Consumer<String> view(){
		return message -> {
			System.out.println("Print from view:"+message);
		};
	}

	@Bean
	public ApplicationRunner runner(StreamBridge template) {
		return args -> {
//			template.send("uppercase-in-0", "TAlha Md. Anik");
//			template.send("topic1", "Sanjoy Tripura");
		};
	}
}
