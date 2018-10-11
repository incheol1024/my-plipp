package net.plipp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan( basePackages = {"net.plipp.domain"} )
@SpringBootApplication
public class MyPlippApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyPlippApplication.class, args);
	}
}
