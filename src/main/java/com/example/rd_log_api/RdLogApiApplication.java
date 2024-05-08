package com.example.rd_log_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RdLogApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RdLogApiApplication.class, args);
	}

}
