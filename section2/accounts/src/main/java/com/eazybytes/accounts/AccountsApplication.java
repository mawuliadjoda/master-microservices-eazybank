package com.eazybytes.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservices REST API Documentation",
				description = "EazyBank Accounts microservices REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "ADJODA Mawuli",
						email = "koffimawuli.adjoda@gmail.com",
						url = "https://connect-228.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://connect-228.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "EazyBank Accounts microservices REST API Documentation",
				url = "https://connect-228.com/swagger-ui.html"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
