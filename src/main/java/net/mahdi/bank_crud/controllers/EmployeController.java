package net.mahdi.bank_crud.controllers;

import net.mahdi.bank_crud.entities.Employe;
import net.mahdi.bank_crud.entities.Groupe;
import net.mahdi.bank_crud.services.EmployeService;
import net.mahdi.bank_crud.services.GroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeController {

    @Autowired
    private EmployeService employeService;

    @Autowired
    private GroupeService groupeService;

    // Show the employee form with available groups
    @GetMapping("/add")
    public String showEmployeeForm(Model model) {
        List<Groupe> groupes = groupeService.findAll(); // Fetch all groups
        model.addAttribute("groupes", groupes); // Add groups to the model to display in the form
        return "employees"; // Return the Thymeleaf template for employees
    }

    // Handle the employee form submission
    @PostMapping
    public ResponseEntity<Employe> ajouterEmploye(@RequestBody Employe employe) {
        // Save the employee, including groups if they are selected
        Employe savedEmploye = employeService.save(employe);
        return new ResponseEntity<>(savedEmploye, HttpStatus.CREATED);
    }

    // Get all employees and show them in the list
    @GetMapping
    public String getAllEmployes(Model model) {
        List<Employe> employes = employeService.findAll();
        model.addAttribute("employes", employes);
        return "employees"; // Return the Thymeleaf template for displaying employees
    }

    // Delete an employee by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerEmploye(@PathVariable Long id) {
        boolean deleted = employeService.deleteById(id); // Implement this method in your service to delete by ID
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
