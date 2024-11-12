package net.mahdi.bank_crud.services;

import net.mahdi.bank_crud.entities.Employe;
import net.mahdi.bank_crud.exceptions.ResourceNotFoundException;
import net.mahdi.bank_crud.repositories.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class EmployeServiceImpl implements EmployeService {
    @Autowired
    private EmployeRepository employeRepository;

    @Override
    public Employe save(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    public Employe findById(Long id) throws ResourceNotFoundException {
        return employeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employé non trouvé"));
    }

    @Override
    public List<Employe> findAll() {
        return employeRepository.findAll();
    }

    @Override
    public boolean deleteById(Long employeId){
        employeRepository.deleteById(employeId);
        return false;
    }
    @Override
    public long countEmployees() {
        return employeRepository.count();
    }
}