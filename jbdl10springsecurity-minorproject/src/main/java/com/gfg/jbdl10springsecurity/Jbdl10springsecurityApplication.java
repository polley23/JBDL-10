package com.gfg.jbdl10springsecurity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Jbdl10springsecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(Jbdl10springsecurityApplication.class, args);
		log.info("application started");
		log.debug("application started and debugging : saptarshi");
		log.trace("application started and tracing");
	}

}
