package com.china.rescue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(basePackages = "com.china.rescue.business.*.mapper")
public class RescueApplication {

	public static void main(String[] args) {
		SpringApplication.run(RescueApplication.class, args);
	}

}
