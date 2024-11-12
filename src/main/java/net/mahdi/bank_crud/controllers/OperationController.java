package net.mahdi.bank_crud.controllers;
import net.mahdi.bank_crud.DTOs.RetraitRequest;
import net.mahdi.bank_crud.DTOs.VersementRequest;
import net.mahdi.bank_crud.DTOs.VirementRequest;
import net.mahdi.bank_crud.entities.Operation;
import net.mahdi.bank_crud.exceptions.InvalidOperationException;
import net.mahdi.bank_crud.exceptions.ResourceNotFoundException;
import net.mahdi.bank_crud.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/transactions")
public class OperationController {
    @Autowired
    private OperationService operationService;

    @GetMapping
    public String afficherTousOperations(Model model) {
        List<Operation> operations = operationService.findAll();
        model.addAttribute("operations", operations);
        return "transactions";
    }


    @PostMapping("/versements")
    public ResponseEntity<Operation> effectuerVersement(@RequestBody VersementRequest request) throws ResourceNotFoundException {
        return new ResponseEntity<>(
                operationService.effectuerVersement(
                        request.getCompteId(),
                        request.getMontant(),
                        request.getEmployeId()
                ),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/retraits")
    public ResponseEntity<Operation> effectuerRetrait(@RequestBody RetraitRequest request) throws InvalidOperationException, ResourceNotFoundException {
        return new ResponseEntity<>(
                operationService.effectuerRetrait(
                        request.getCompteId(),
                        request.getMontant(),
                        request.getEmployeId()
                ),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/virements")
    public ResponseEntity<Operation> effectuerVirement(@RequestBody VirementRequest request) throws InvalidOperationException, ResourceNotFoundException {
        return new ResponseEntity<>(
                operationService.effectuerVirement(
                        request.getCompteSourceId(),
                        request.getCompteDestinationId(),
                        request.getMontant(),
                        request.getEmployeId()
                ),
                HttpStatus.CREATED
        );
    }
}