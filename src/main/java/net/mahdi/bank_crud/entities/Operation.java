package net.mahdi.bank_crud.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "T_OPERATIONS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_OP")
@Data
public abstract class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NUM_OP")
    private Long numOperation;

    @Column(name = "DATE_OP")
    private Date dateOperation;

    @Column(name = "MONTANT")
    private double montant;

    // Relation Many-to-One avec Compte
    @ManyToOne(fetch = FetchType.EAGER)  // Lazy loading recommandé
    @JoinColumn(name = "NUM_CPTE")
    @JsonBackReference  // Evite la sérialisation récursive avec Compte
    private Compte compte;

    // Relation Many-to-One avec Employe
    @ManyToOne(fetch = FetchType.LAZY)  // Lazy loading recommandé
    @JoinColumn(name = "NUM_EMP")
    @JsonBackReference  // Evite la sérialisation récursive avec Employe
    private Employe employe;
}
