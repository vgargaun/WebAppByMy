package com.vicsla.vicsla.controller;

import com.vicsla.vicsla.models.ClientBank;
import com.vicsla.vicsla.repo.ClientBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicBoolean;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class ClientBankController {

    @Autowired
    ClientBankRepository clientBankRepository;

    @PostMapping("/register")
    public @ResponseBody void getClientBank(@RequestBody ClientBank clientBank){

        clientBankRepository.save(clientBank);
    }

    @PostMapping("/login")
    public @ResponseBody
    String login(@RequestBody ClientBank client,
                 HttpServletResponse response) {
        AtomicBoolean check = new AtomicBoolean(false);
        Iterable<ClientBank> clients = clientBankRepository.findAll();
        clients.forEach(c -> {
            if (c.getNickname().equals(client.getNickname()) && c.getPassword().equals(client.getPassword())) {
                check.set(true);
            }
        });

        return check.toString();
    }

    @PostMapping("/forgotPassword")
    public @ResponseBody String forgotPaswword(@RequestBody ClientBank client){
        AtomicBoolean check = new AtomicBoolean(false);
        Iterable<ClientBank> clientBanks = clientBankRepository.findAll();
        clientBanks.forEach(c ->{
            if (c.getEmail().equals(client.getEmail()) && c.getNickname().equals(client.getNickname())) {
                check.set(true);
            }
        });
        return  check.toString();
    }

    @PostMapping("/changePassword")
    public @ResponseBody String changePaswword(@RequestBody ClientBank client){
        AtomicBoolean check = new AtomicBoolean(false);
        Iterable<ClientBank> clientBanks = clientBankRepository.findAll();
        clientBanks.forEach(c ->{
            if (c.getEmail().equals(client.getEmail()) && c.getNickname().equals(client.getNickname())) {
                c.setPassword(client.getPassword());
                clientBankRepository.save(c);
                check.set(true);
            }
        });
        return  check.toString();
    }

}
