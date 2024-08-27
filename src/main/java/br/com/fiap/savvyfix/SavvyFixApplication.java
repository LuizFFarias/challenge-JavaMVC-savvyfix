package br.com.fiap.savvyfix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan
@ComponentScan
@SpringBootApplication
public class SavvyFixApplication {

	public static void main(String[] args) {
		SpringApplication.run(SavvyFixApplication.class, args);
	}

}
