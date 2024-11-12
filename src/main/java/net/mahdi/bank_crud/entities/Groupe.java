package net.mahdi.bank_crud.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "T_GROUPES")
public class Groupe {

    @Id
    @Column(name = "NUM_GR")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numGroupe;

    @Column(name = "NOM_GR")
    private String nomGroupe;

    // Utilisation de @JsonBackReference pour éviter la sérialisation infinie de la relation bidirectionnelle
    @ManyToMany(mappedBy = "groupes")
    @JsonBackReference
    private List<Employe> employes;
}
