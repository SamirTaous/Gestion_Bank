package net.mahdi.bank_crud.controllers;
import net.mahdi.bank_crud.DTOs.ClientRequest;
import net.mahdi.bank_crud.entities.Client;
import net.mahdi.bank_crud.entities.Compte;
import net.mahdi.bank_crud.entities.Employe;
import net.mahdi.bank_crud.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> ajouterClient(@RequestBody ClientRequest request) {
        return new ResponseEntity<>(clientService.save(request), HttpStatus.CREATED);
    }

    /*@GetMapping("/{clientId}/comptes")
    public ResponseEntity<List<Compte>> consulterComptesClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(clientService.getComptesClient(clientId));
    }*/

    @GetMapping
    public String afficherTousClients(Model model){
        List<Client> clients = clientService.findAll();
        model.addAttribute("clients", clients);
        return "clients";
    }
}