package com.cubes4.CUBES4.services;

import com.cubes4.CUBES4.models.Client;
import com.cubes4.CUBES4.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + id));
    }

    public List<Client> getClientByNom(String nom) {
        return clientRepository.findByNomContainingIgnoreCase(nom);
    }

    public List<Client> getClientByPrenom(String prenom) {
        return clientRepository.findByPrenomContainingIgnoreCase(prenom);
    }

    public List<Client> getClientByEmail(String email) {
        return clientRepository.findByEmailContainingIgnoreCase(email);
    }

    public List<Client> getClientByTelephone(String telephone) {
        return clientRepository.findByTelephoneContainingIgnoreCase(telephone);
    }

    public Client createClient(Client client) {
        return clientRepository.saveAndFlush(client);
    }

    public Client updateClient(Long id, Client updatedclient) {
        return clientRepository.findById(id)
                .map(client -> {
                    client.setNom(updatedclient.getNom());
                    client.setPrenom(updatedclient.getPrenom());
                    client.setEmail(updatedclient.getEmail());
                    client.setTelephone(updatedclient.getTelephone());
                    client.setAdresse(updatedclient.getAdresse());
                    client.setCommandes(updatedclient.getCommandes());
                    return clientRepository.saveAndFlush(client);
                }).orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + id));
    }

    public void deleteClient(Long id) {
        Client client = getClientById(id);
        clientRepository.delete(client);
    }
}
