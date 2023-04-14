package com.bloomtechlabs.fp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.bloomtechlabs.fp", entityManagerFactoryRef="entityManagerFactory")

public class FamilyPromiseCaseMgmtBeAApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(FamilyPromiseCaseMgmtBeAApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error---> " + e.getMessage());
		}
	}
}
