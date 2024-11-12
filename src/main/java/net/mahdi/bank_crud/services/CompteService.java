package net.mahdi.bank_crud.services;

import net.mahdi.bank_crud.DTOs.CompteRequest;
import net.mahdi.bank_crud.entities.Compte;
import net.mahdi.bank_crud.entities.Operation;
import net.mahdi.bank_crud.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompteService {
    Compte save(CompteRequest compte);
    Compte findById(String id) throws ResourceNotFoundException;
    Page<Operation> getOperations(Long compteId, Pageable pageable);

    List<Compte> getAllComptes();

    long countClientAccounts();
}
