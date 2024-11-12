package net.mahdi.bank_crud.services;

import net.mahdi.bank_crud.DTOs.ClientRequest;
import net.mahdi.bank_crud.entities.Client;
import net.mahdi.bank_crud.entities.Compte;
import net.mahdi.bank_crud.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ClientService {
    Client save(ClientRequest request);
    List<Compte> getComptesClient(Long clientId);
    Client findById(Long id) throws ResourceNotFoundException;

    List<Client> findAll();
}
