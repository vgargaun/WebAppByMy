package com.vicsla.vicsla.controller;

import com.vicsla.vicsla.models.Client;
import com.vicsla.vicsla.repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.atomic.AtomicBoolean;

@Controller
public class AutorisationController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/in")
    public String autorisation(Model model){
        return "in";
    }

    @PostMapping("/in")
    public String autorisationIn(@RequestParam String name, @RequestParam String password, Model model){
        Iterable<Client> clients = clientRepository.findAll();
        AtomicBoolean aut = new AtomicBoolean(false);
        clients.forEach(client -> {
            if(client.getName().equals(name) && client.getPassword().equals(password))
                aut.set(true);
        });
        if(aut.get()) return "succes";
        else return "redirect:/in";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        return "registration.html";
    }

    @PostMapping("/registration")
    public String registrationAdd(@RequestParam String name, @RequestParam String password, Model model){
        Client client = new Client();
        client.setName(name);
        client.setPassword(password);
        clientRepository.save(client);

        return "/succes";
    }


}