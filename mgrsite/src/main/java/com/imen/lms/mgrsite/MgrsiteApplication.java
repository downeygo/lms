package com.imen.lms.mgrsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.imen.lms")
public class MgrsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MgrsiteApplication.class, args);
	}
}
