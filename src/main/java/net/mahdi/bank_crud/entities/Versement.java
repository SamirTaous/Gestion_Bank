package net.mahdi.bank_crud.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("V")
public class Versement extends Operation {
}