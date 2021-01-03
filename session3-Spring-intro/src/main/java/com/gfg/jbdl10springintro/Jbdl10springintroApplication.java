package com.gfg.jbdl10springintro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Jbdl10springintroApplication {

	public static void main(String[] args) {
		//IoC container
		ApplicationContext applicationContext =
				SpringApplication.run(Jbdl10springintroApplication.class, args);

		//managed object
		Calculator calculator = applicationContext.getBean(Calculator.class);

		System.out.println("Output is "+ calculator.mul(2f,4f));
		Calculator calculator2 = applicationContext.getBean(Calculator.class);
		System.out.println("Output is "+ calculator2.div(8f,4f));
	}
}
