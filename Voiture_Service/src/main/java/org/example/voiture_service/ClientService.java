package org.example.voiture_service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="client-service")
public interface ClientService {

    @GetMapping(path = "/client/{id}")
    public Client getClientbyId(@PathVariable Long id);
}
