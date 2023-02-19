package com.art.elastic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.art.elastic.dao")
public class SearchDogApplication {
	public static void main(String[] args) {
		SpringApplication.run(SearchDogApplication.class, args);
	}
}
