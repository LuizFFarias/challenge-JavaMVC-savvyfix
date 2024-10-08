package br.com.fiap.savvyfix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@EntityScan
@SpringBootApplication
public class SavvyFixApplication {

	public static void main(String[] args) {
		SpringApplication.run(SavvyFixApplication.class, args);
	}

}
