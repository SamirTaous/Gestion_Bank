package net.mahdi.bank_crud.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@DiscriminatorValue("R")
@Data
public class Retrait extends Operation {
}