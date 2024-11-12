package net.mahdi.bank_crud.services;
import net.mahdi.bank_crud.entities.*;
import net.mahdi.bank_crud.entities.*;
import net.mahdi.bank_crud.exceptions.InvalidOperationException;
import net.mahdi.bank_crud.exceptions.ResourceNotFoundException;
import net.mahdi.bank_crud.repositories.CompteRepository;
import net.mahdi.bank_crud.repositories.EmployeRepository;
import net.mahdi.bank_crud.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OperationServiceImpl implements OperationService {
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private EmployeRepository employeRepository;

    @Override
    public Operation effectuerVersement(String compteId, double montant, Long employeId) throws ResourceNotFoundException {
        Compte compte = compteRepository.findById(compteId)
                .orElseThrow(() -> new ResourceNotFoundException("Compte non trouvé"));
        Employe employe = employeRepository.findById(employeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employé non trouvé"));

        compte.setSolde(compte.getSolde() + montant);

        Versement versement = new Versement();
        versement.setDateOperation(new Date());
        versement.setMontant(montant);
        versement.setCompte(compte);
        versement.setEmploye(employe);

        compteRepository.save(compte);
        return operationRepository.save(versement);
    }

    @Override
    public Operation effectuerRetrait(String compteId, double montant, Long employeId) throws ResourceNotFoundException, InvalidOperationException {
        Compte compte = compteRepository.findById(compteId)
                .orElseThrow(() -> new ResourceNotFoundException("Compte non trouvé"));
        Employe employe = employeRepository.findById(employeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employé non trouvé"));

        if (compte instanceof CompteCourant) {
            CompteCourant cc = (CompteCourant) compte;
            if (compte.getSolde() + cc.getDecouvert() < montant) {
                throw new InvalidOperationException("Solde insuffisant");
            }
        } else if (compte.getSolde() < montant) {
            throw new InvalidOperationException("Solde insuffisant");
        }

        compte.setSolde(compte.getSolde() - montant);

        Retrait retrait = new Retrait();
        retrait.setDateOperation(new Date());
        retrait.setMontant(montant);
        retrait.setCompte(compte);
        retrait.setEmploye(employe);

        compteRepository.save(compte);
        return operationRepository.save(retrait);
    }

    @Override
    public Operation effectuerVirement(String compteSourceId, String compteDestinationId, double montant, Long employeId) throws InvalidOperationException, ResourceNotFoundException {
        effectuerRetrait(compteSourceId, montant, employeId);
        return effectuerVersement(compteDestinationId, montant, employeId);
    }

    @Override
    public List<Operation> findAll() {
        return operationRepository.findAll();
    }
}