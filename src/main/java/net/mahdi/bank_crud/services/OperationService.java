package net.mahdi.bank_crud.services;

import net.mahdi.bank_crud.entities.Operation;
import net.mahdi.bank_crud.exceptions.InvalidOperationException;
import net.mahdi.bank_crud.exceptions.ResourceNotFoundException;

import java.util.List;

public interface OperationService {
    Operation effectuerVersement(String compteId, double montant, Long employeId) throws ResourceNotFoundException;
    Operation effectuerRetrait(String compteId, double montant, Long employeId) throws ResourceNotFoundException, InvalidOperationException;
    Operation effectuerVirement(String compteSourceId, String compteDestinationId, double montant, Long employeId) throws InvalidOperationException, ResourceNotFoundException;
    List<Operation> findAll();
}