package org.example.client_service.contoller;

import org.example.client_service.entities.Client;
import org.example.client_service.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ClientContoller {

    @Autowired
    private ClientRepository ClientRepository;


    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return ClientRepository.findAll();
    }

    @GetMapping("/client/{id}")
    public Client getClientById(@PathVariable Long id) throws Exception {
        return ClientRepository.findById(id).orElseThrow(()->new Exception());
    }


}
