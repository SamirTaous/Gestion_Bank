package net.mahdi.bank_crud.services;
import net.mahdi.bank_crud.DTOs.CompteRequest;
import net.mahdi.bank_crud.entities.Compte;
import net.mahdi.bank_crud.entities.CompteCourant;
import net.mahdi.bank_crud.entities.CompteEpargne;
import net.mahdi.bank_crud.entities.Operation;
import net.mahdi.bank_crud.exceptions.ResourceNotFoundException;
import net.mahdi.bank_crud.repositories.ClientRepository;
import net.mahdi.bank_crud.repositories.CompteRepository;
import net.mahdi.bank_crud.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CompteServiceImpl implements CompteService {
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Compte save(CompteRequest compte) {
        Compte newCompte;

        if (compte.getType().equals("CC")) {
            CompteCourant compteCourant = new CompteCourant();
            compteCourant.setDecouvert(300);
            newCompte = compteCourant;
        } else {
            CompteEpargne compteEpargne = new CompteEpargne();
            compteEpargne.setTaux(4);
            newCompte = compteEpargne;
        }

        newCompte.setSolde(compte.getSolde());
        newCompte.setDateCreation(new Date());
        newCompte.setClient(clientRepository.findById(compte.getCodeClient()).orElseThrow(() -> new RuntimeException("Client not found")));

        return compteRepository.save(newCompte);
    }

    @Override
    public Compte findById(String id) throws ResourceNotFoundException {
        return compteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compte non trouvé"));
    }

    @Override
    public Page<Operation> getOperations(Long compteId, Pageable pageable) {
        return operationRepository.findByCompteNumCompte(compteId, pageable);
    }
    @Override
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    @Override
    public long countClientAccounts() {
        return compteRepository.count();
    }
}