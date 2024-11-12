package net.mahdi.bank_crud.DTOs;

import lombok.Data;


@Data
public class CompteRequest {
    private double solde;
    private Long codeClient;
    private String type ;
}