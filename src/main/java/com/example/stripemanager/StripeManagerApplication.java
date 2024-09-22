package com.example.stripemanager;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Stripe Manager",
                description = "Simple Stripe manager for common operations",
                contact = @Contact(
                        name = "Mustafa Abuelmagd",
                        url = "https://www.linkedin.com/in/mustafa-abuelmagd/",
                        email = "mustafa.2buelmagd@gmail.com"
                ),
                version = "0.0.1"
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "local development server"
                )
        }
)
@SpringBootApplication
public class StripeManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StripeManagerApplication.class, args);
    }

}
