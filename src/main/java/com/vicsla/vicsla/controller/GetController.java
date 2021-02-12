package com.vicsla.vicsla.controller;

import com.vicsla.vicsla.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/")
    public String greeting( Model model) {
        model.addAttribute("name", "hello");
        clientService.setText("new");
        return "home";
    }

}
