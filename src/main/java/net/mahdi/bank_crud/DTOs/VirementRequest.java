package net.mahdi.bank_crud.DTOs;

import lombok.Data;

@Data
public class VirementRequest {
    private String compteSourceId;
    private String compteDestinationId;
    private double montant;
    private Long employeId;
}