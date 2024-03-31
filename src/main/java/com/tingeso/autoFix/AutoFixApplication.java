package com.tingeso.autoFix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutoFixApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoFixApplication.class, args);
		helloWorld();
	}

	public static void helloWorld(){
		System.out.println("Hola mundo esto es una prueba de Tingeso");
	}


}
