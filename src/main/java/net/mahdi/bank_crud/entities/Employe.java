package net.mahdi.bank_crud.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "T_EMPLOYES")
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NUM_EMP")
    private Long codeEmploye;

    @Column(name = "NOM_EMP")
    private String nomEmploye;


    // Reference to a superior employee
    @ManyToOne
    @JoinColumn(name = "NUM_EMP_SUP")
    @JsonBackReference("superior-subordinate")  // Unique reference name for superior
    private Employe superieur;


    // Many-to-many relationship with Groupe
    @ManyToMany
    @JoinTable(
            name = "T_EMP_GR",
            joinColumns = @JoinColumn(name = "NUM_EMP"),
            inverseJoinColumns = @JoinColumn(name = "NUM_GR")
    )
    private List<Groupe> groupes;
}
