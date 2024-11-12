package net.mahdi.bank_crud.services;
import net.mahdi.bank_crud.entities.Employe;
import net.mahdi.bank_crud.entities.Groupe;
import net.mahdi.bank_crud.exceptions.ResourceNotFoundException;
import net.mahdi.bank_crud.repositories.EmployeRepository;
import net.mahdi.bank_crud.repositories.GroupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class GroupeServiceImpl implements GroupeService {
    @Autowired
    private GroupeRepository groupeRepository;
    @Autowired
    private EmployeRepository employeRepository;

    @Override
    public Groupe save(Groupe groupe) {
        return groupeRepository.save(groupe);
    }

    @Override
    public void affecterEmployeAuGroupe(Long groupeId, Long employeId) throws ResourceNotFoundException {
        Groupe groupe = findById(groupeId);
        Employe employe = employeRepository.findById(employeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employé non trouvé"));

        groupe.getEmployes().add(employe);
        employe.getGroupes().add(groupe);

        groupeRepository.save(groupe);
        employeRepository.save(employe);
    }

    @Override
    public Groupe findById(Long id) throws ResourceNotFoundException {
        return groupeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Groupe non trouvé"));
    }
    public List<Groupe> findAll() {
        return groupeRepository.findAll();
    }
}
