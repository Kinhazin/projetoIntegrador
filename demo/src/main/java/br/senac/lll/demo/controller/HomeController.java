package br.senac.lll.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String paginaInicial() {
        return "index";
    }

    @GetMapping("/login")
    public String paginaDeLogin() {
        return "index";
    }

    @GetMapping("/register")
    public String paginaDeRegistro() {
        return "register";
    }

    @GetMapping("/about")
    public String paginaSobreNos() {
        return "about";
    }

    @GetMapping("/contact")
    public String paginaDeContato() {
        return "contact";
    }

}