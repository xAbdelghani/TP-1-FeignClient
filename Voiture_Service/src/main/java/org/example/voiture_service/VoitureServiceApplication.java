package org.example.voiture_service;

import org.example.voiture_service.entities.Voiture;
import org.example.voiture_service.repository.VoitureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients(basePackageClasses = ClientService.class)
@SpringBootApplication
public class VoitureServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoitureServiceApplication.class, args);
	}


	@Bean
	CommandLineRunner start(VoitureRepository voitureRepository,ClientService clientService){
		return args -> {

			Client C1 = clientService.getClientbyId(1L);
			Client C2 = clientService.getClientbyId(2L);
			C1.toString();
			C2.toString();

			voitureRepository.save(new Voiture(null,"toyota",
					"b1","ee",1L,C2));
	     	voitureRepository.save(new Voiture(null,"bmw",
					"b12","ee2",1L,C2));
		    voitureRepository.save(new Voiture(null,"sasa",
					"b12","ee2",2L,C1));


		};
	}

}
