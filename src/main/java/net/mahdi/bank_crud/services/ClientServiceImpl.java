package net.mahdi.bank_crud.services;

import net.mahdi.bank_crud.DTOs.ClientRequest;
import net.mahdi.bank_crud.entities.Client;
import net.mahdi.bank_crud.entities.Compte;
import net.mahdi.bank_crud.exceptions.ResourceNotFoundException;
import net.mahdi.bank_crud.repositories.ClientRepository;
import net.mahdi.bank_crud.repositories.CompteRepository;
import net.mahdi.bank_crud.repositories.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private EmployeRepository employeRepository;

    @Override
    public Client save(ClientRequest request) {
        Client newClient= new Client();
        newClient.setNom(request.getNom());
        newClient.setEmploye(employeRepository.findById(request.getCodeEmploye()).get());
        return clientRepository.save(newClient);
    }

    @Override
    public List<Compte> getComptesClient(Long clientId) {
        return compteRepository.findByClientCode(clientId);
    }

    @Override
    public Client findById(Long id) throws ResourceNotFoundException {
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client non trouvé"));
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}