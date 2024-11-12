package net.mahdi.bank_crud.controllers;

import net.mahdi.bank_crud.services.CompteService;
import net.mahdi.bank_crud.services.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private CompteService compteService;

    @Autowired
    EmployeService employeService;

    @GetMapping()
    public String dashboard(Model model) {
        long clientCount = compteService.countClientAccounts();
        long employeeCount = employeService.countEmployees();
        model.addAttribute("clientCount", clientCount);
        model.addAttribute("employeeCount", employeeCount);
        return "dashboard";
    }
}
