package org.nfa.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAuthorizationServer
@EnableSwagger2
@Import({ WebSecurityConfiguration.class })
//@Import({ WebSecurityConfiguration.class, AuthorizationServerConfiguration.class })
public class MagnetAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagnetAuthApplication.class, args);
	}

}
