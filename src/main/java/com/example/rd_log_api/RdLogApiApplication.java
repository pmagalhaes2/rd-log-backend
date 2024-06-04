package com.example.rd_log_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(info = @Info(title="RD Log API", version = "1.0", description = "A simple application for managing a logistic module with CRUD operations for logistic companies."))
public class RdLogApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RdLogApiApplication.class, args);
	}

}
