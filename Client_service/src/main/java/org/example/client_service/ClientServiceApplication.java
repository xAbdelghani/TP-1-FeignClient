package org.example.client_service;

import org.example.client_service.entities.Client;
import org.example.client_service.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class ClientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner start(ClientRepository clientRepository){
        return args -> {
            clientRepository.save(new Client(null,"abdelghania aboumada",21f));
            clientRepository.save(new Client(null,"hassan vdfv",212f));
            clientRepository.save(new Client(null,"rachid vvd",211f));
        };
    }
}
