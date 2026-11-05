package com.ajay.development.ecommerce.order_service;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {


		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().systemProperties().load();
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
