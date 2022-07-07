package com.dsumtsov.google.sheets.update.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class GoogleSheetsUpdateDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoogleSheetsUpdateDemoApplication.class, args);
	}

}
