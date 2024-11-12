package net.mahdi.bank_crud.DTOs;

import lombok.Data;

@Data
public class VersementRequest {
    private String compteId;
    private double montant;
    private Long employeId;
}