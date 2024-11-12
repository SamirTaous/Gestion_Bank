package net.mahdi.bank_crud.repositories;

import net.mahdi.bank_crud.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompteRepository extends JpaRepository<Compte, String> {
    List<Compte> findByClientCode(Long clientId);

}