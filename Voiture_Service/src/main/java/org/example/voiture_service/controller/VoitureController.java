package org.example.voiture_service.controller;


import lombok.RequiredArgsConstructor;
import org.example.voiture_service.Client;
import org.example.voiture_service.ClientService;
import org.example.voiture_service.entities.Voiture;
import org.example.voiture_service.repository.VoitureRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VoitureController {

    private final VoitureRepository voitureRepository;
    private final ClientService clientService;

    @GetMapping
    public List<Voiture> getAllVoitures() {
        List<Voiture> voitures = voitureRepository.findAll();
        voitures.forEach(voiture -> {
            voiture.setClient(clientService.getClientbyId(voiture.getId_client()));
        });
        return voitures;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voiture> getVoitureById(@PathVariable Long id) {
        Voiture voiture = voitureRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Voiture not found with id " + id));
        voiture.setClient(clientService.getClientbyId(voiture.getId_client()));
        return ResponseEntity.ok(voiture);
    }

    @PostMapping
    public ResponseEntity<Voiture> createVoiture(@RequestBody Voiture voiture) {
        Client client = clientService.getClientbyId(voiture.getId_client());
        voiture.setClient(client);
        Voiture savedVoiture = voitureRepository.save(voiture);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVoiture);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voiture> updateVoiture(@PathVariable Long id, @RequestBody Voiture updatedVoiture) {
        Voiture existingVoiture = voitureRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Voiture not found with id " + id));
        existingVoiture.setMarque(updatedVoiture.getMarque());
        existingVoiture.setMatricule(updatedVoiture.getMatricule());
        existingVoiture.setModel(updatedVoiture.getModel());
        existingVoiture.setId_client(updatedVoiture.getId_client());

        Client client = clientService.getClientbyId(updatedVoiture.getId_client());
        existingVoiture.setClient(client);

        Voiture savedVoiture = voitureRepository.save(existingVoiture);
        return ResponseEntity.ok(savedVoiture);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoiture(@PathVariable Long id) {
        voitureRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}