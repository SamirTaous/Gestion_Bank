package net.mahdi.bank_crud.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "T_COMPTES")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_PTE")
@Data
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NUM_CPTE")
    private Long numCompte;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_CR", nullable = false)
    private Date dateCreation;

    @Column(name = "SOLDE", nullable = false)
    private double solde;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CODE_CLI", referencedColumnName = "CODE_CLI", foreignKey = @ForeignKey(name = "FK_COMPTE_CLIENT"))
    @JsonBackReference  // Prevents recursion when serializing the Client
    private Client client;

    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference  // Manages serialization of the list of operations
    private List<Operation> operations;
}
