package PracticeProject.SecurityOauthRegister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages = {"service", "repository", "filter", "configs", "entity", "controller", "model"})
@EnableJpaRepositories(basePackages = {"repository"})
@EntityScan(basePackages = {"entity"})
public class SecurityOauthRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityOauthRegisterApplication.class, args);
	}

}
