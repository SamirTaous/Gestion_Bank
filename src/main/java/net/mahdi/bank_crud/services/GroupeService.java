package net.mahdi.bank_crud.services;

import net.mahdi.bank_crud.entities.Groupe;
import net.mahdi.bank_crud.exceptions.ResourceNotFoundException;

import java.util.List;

public interface GroupeService {
    Groupe save(Groupe groupe);
    void affecterEmployeAuGroupe(Long groupeId, Long employeId) throws ResourceNotFoundException;
    Groupe findById(Long id) throws ResourceNotFoundException;

    List<Groupe> findAll();
}