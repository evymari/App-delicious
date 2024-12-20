package com.f5.delicious;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = "com.f5.delicious")
@SpringBootApplication
public class DeliciousApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliciousApplication.class, args);
	}

}
