package net.mahdi.bank_crud.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@DiscriminatorValue("CE")
@Data
public class CompteEpargne extends Compte {
    @Column(name = "TAUX")
    private double taux;
}