package net.mahdi.bank_crud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping("/")
    public String login(){
        return "login";
    }

    @PostMapping("/")
    public String verifyCredentials(@RequestParam String username, @RequestParam String password, Model model){
        boolean isVerified= username.equals("samir") && password.equals("00000");
        if(isVerified) return "home";
        String message="Username or password incorrect!";
        model.addAttribute("message",message);
        return "login";
    }
}
