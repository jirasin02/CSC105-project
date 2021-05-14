package com.jirasin02.lemoningz;

import com.jirasin02.lemoningz.database.MySQLConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;

@SpringBootApplication
public class LemoningzApplication {

	public static void main(String[] args) {
		SpringApplication.run(LemoningzApplication.class, args);
		try {
			new MySQLConnector();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
