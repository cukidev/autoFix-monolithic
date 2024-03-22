package com.tingeso.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		helloWorld();
	}

	public static void helloWorld(){
		System.out.println("Hola mundo esto es una ayudantía de Tingeso");
	}


}
