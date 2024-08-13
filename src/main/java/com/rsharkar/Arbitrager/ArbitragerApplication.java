package com.rsharkar.Arbitrager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ArbitragerApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(ArbitragerApplication.class, args);
	}
}
