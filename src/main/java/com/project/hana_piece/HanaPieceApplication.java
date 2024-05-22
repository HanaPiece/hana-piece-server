package com.project.hana_piece;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HanaPieceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HanaPieceApplication.class, args);
	}

}
