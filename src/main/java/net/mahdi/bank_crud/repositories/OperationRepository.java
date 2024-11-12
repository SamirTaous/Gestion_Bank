package net.mahdi.bank_crud.repositories;

import net.mahdi.bank_crud.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    Page<Operation> findByCompteNumCompte(Long numCompte, Pageable pageable);
}