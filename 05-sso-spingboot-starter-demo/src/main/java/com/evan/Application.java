package com.evan;

import com.evan.sso.config.EnableSSO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSSO
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
