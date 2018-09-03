package org.nfa.lucia;

import org.nfa.panel.config.EnableMagnetService;
import org.springframework.boot.SpringApplication;

@EnableMagnetService
public class MagnetLuciaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagnetLuciaApplication.class, args);
	}

}
