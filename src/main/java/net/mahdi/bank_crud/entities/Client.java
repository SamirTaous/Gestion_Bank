package net.mahdi.bank_crud.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "T_CLIENTS")
@Data
public class Client {

    @Id
    @Column(name = "CODE_CLI")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @Column(name = "NOM_CLI")
    private String nom;

    // Link to an employee
    @ManyToOne
    @JoinColumn(name = "NUM_EMP")
    @JsonBackReference("employe-client")  // Unique back reference name for Employe-Client relationship
    private Employe employe;

    // Relationship with accounts
    @OneToMany(mappedBy = "client")
    @JsonManagedReference("client-compte")  // Unique managed reference name for Client-Compte relationship
    private List<Compte> comptes;
}
