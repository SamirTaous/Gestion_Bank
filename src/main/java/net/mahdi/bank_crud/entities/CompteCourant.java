package net.mahdi.bank_crud.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@DiscriminatorValue("CC")
@Data
public class CompteCourant extends Compte {
    @Column(name = "DECC")
    private double decouvert;
}