package net.mahdi.bank_crud.repositories;

import net.mahdi.bank_crud.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
}