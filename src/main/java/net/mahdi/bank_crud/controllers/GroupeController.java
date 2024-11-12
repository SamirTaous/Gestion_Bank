package net.mahdi.bank_crud.controllers;

import net.mahdi.bank_crud.entities.Groupe;
import net.mahdi.bank_crud.exceptions.ResourceNotFoundException;
import net.mahdi.bank_crud.services.GroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/groupes")
public class GroupeController {
    @Autowired
    private GroupeService groupeService;

    @PostMapping
    public ResponseEntity<Groupe> ajouterGroupe(@RequestBody Groupe groupe) {
        return new ResponseEntity<>(groupeService.save(groupe), HttpStatus.CREATED);
    }

    /*@PutMapping("/{groupeId}/employes/{employeId}")
    public ResponseEntity<Void> affecterEmployeAuGroupe(
            @PathVariable Long groupeId,
            @PathVariable Long employeId) throws ResourceNotFoundException {
        groupeService.affecterEmployeAuGroupe(groupeId, employeId);
        return ResponseEntity.ok().build();
    }*/

    @GetMapping
    public String afficherGroupes(Model model) {
        List<Groupe> groupes = groupeService.findAll();
        model.addAttribute("groupes",groupes);
        return "groupes";
    }

}
