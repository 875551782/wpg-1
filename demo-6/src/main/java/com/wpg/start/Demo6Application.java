package com.wpg.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.wpg.*")
@MapperScan("com.wpg.dao")
public class Demo6Application {
	public static void main(String[] args) {
		SpringApplication.run(Demo6Application.class, args);
	}
}

