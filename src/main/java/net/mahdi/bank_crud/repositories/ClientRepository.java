package net.mahdi.bank_crud.repositories;

import net.mahdi.bank_crud.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}