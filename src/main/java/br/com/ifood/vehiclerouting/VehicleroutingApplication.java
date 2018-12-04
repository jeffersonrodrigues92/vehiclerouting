package br.com.ifood.vehiclerouting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("br.com.ifood.vehiclerouting")
@EntityScan("br.com.ifood.vehiclerouting.entity")
@EnableJpaRepositories("br.com.ifood.vehiclerouting.repository")
public class VehicleroutingApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleroutingApplication.class, args);
	}



}
