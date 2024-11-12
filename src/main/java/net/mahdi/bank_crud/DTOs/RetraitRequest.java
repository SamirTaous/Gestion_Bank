package net.mahdi.bank_crud.DTOs;

import lombok.Data;

@Data
public class RetraitRequest {
    private String compteId;
    private double montant;
    private Long employeId;
}