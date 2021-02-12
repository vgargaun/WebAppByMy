package com.vicsla.vicsla.controller;

import com.vicsla.vicsla.client.ClientService;
import com.vicsla.vicsla.models.Client;
import com.vicsla.vicsla.models.Post;
import com.vicsla.vicsla.repo.ClientRepository;
import com.vicsla.vicsla.repo.PostRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class BlogController {

    @Autowired
    private PostRepositiry postRepositiry;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientService clientService;

    @GetMapping("/blog")
    public String blog(Model model) {
        Iterable<Post> posts = postRepositiry.findAll();
        model.addAttribute("posts", posts);
        return "blogTemplates";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        Post post = new Post(title, anons, full_text);
        postRepositiry.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if (!postRepositiry.existsById(id)) return "blog-details";
        Optional<Post> post = postRepositiry.findById(id);
        ArrayList<Post> postList = new ArrayList<>();
        post.ifPresent(postList::add);
        model.addAttribute("post", postList);
        return "table";
    }

    @GetMapping("/blog/{id}/remove")
    public String blogDelete(@PathVariable(value = "id") long id, Model model) {
        if (!postRepositiry.existsById(id)) return "blog-details";
        postRepositiry.deleteById(id);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if (!postRepositiry.existsById(id)) return "blog-details";
        Optional<Post> post = postRepositiry.findById(id);
        ArrayList<Post> postList = new ArrayList<>();
        post.ifPresent(postList::add);
        model.addAttribute("post", postList);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title,
                                 @RequestParam String anons, @RequestParam String full_text, Model model) throws Exception {
        if (!postRepositiry.existsById(id)) return "blog-details";
        Post post = postRepositiry.findById(id).orElseThrow(() -> new Exception("Student not found - "));
        post.setAnons(anons);
        post.setFull_text(full_text);
        post.setTitle(title);
        postRepositiry.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/test2")
    public String test2(Model model) {
        return "test";
    }

    @GetMapping("/test")
    public String test(Model model) {
        String https_url = "http://127.0.0.1:8080/test2";

//        URL url = new URl(https_url);
        return "test";
    }


    @GetMapping("/date")
    public @ResponseBody
    Iterable<Client> date(Model model, HttpServletResponse http) throws IOException {
//
//        List<String> list = new ArrayList<>();
//        list.add("one");
//        list.add("two");
//        list.add("three");


//        JSONObject jsonResponse = new JSONObject();
//        jsonResponse.put("TAIL", "MY TEXT");
//        return String.valueOf(jsonResponse);
//        return list;

//        Optional<Client> client = clientRepository.findById(14L);

        return clientRepository.findAll();
    }

    @PostMapping("/add")
    public @ResponseBody
    void registrationAdd(@RequestBody Client client,
                         HttpServletResponse response) {
        System.out.println("start");
        clientRepository.save(client);
        response.setStatus(200);
        System.out.println(clientService.getText());
        System.out.println("succes add " + client.getName());
    }

    @PostMapping("/delete")
    public @ResponseBody
    void delete(@RequestBody long id,
                HttpServletResponse response) {

        clientRepository.deleteById(id);
        System.out.println("start " + id);
    }


}