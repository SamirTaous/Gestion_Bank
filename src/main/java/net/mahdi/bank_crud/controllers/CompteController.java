package net.mahdi.bank_crud.controllers;

import net.mahdi.bank_crud.DTOs.CompteRequest;
import net.mahdi.bank_crud.entities.Compte;
import net.mahdi.bank_crud.exceptions.ResourceNotFoundException;
import net.mahdi.bank_crud.services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/accounts")
public class CompteController {

    @Autowired
    private CompteService compteService;

    @PostMapping
    public ResponseEntity<Compte> ajouterCompte(@RequestBody CompteRequest compte) {
        Compte savedCompte = compteService.save(compte);
        return new ResponseEntity<>(savedCompte, HttpStatus.CREATED);
    }



    @GetMapping
    public String afficherTousLesComptes(Model model) {
        List<Compte> comptes = compteService.getAllComptes();
        model.addAttribute("accounts", comptes);
        return "accounts";
    }

    /*@DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerCompte(@PathVariable Long id) {
        try {
            compteService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/
}
