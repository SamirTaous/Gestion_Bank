package net.mahdi.bank_crud.services;

import net.mahdi.bank_crud.entities.Employe;
import net.mahdi.bank_crud.exceptions.ResourceNotFoundException;

import java.util.List;

public interface EmployeService {
    Employe save(Employe employe);
    Employe findById(Long id) throws ResourceNotFoundException;

    List<Employe> findAll();

    boolean deleteById(Long employeId);

    long countEmployees();
}