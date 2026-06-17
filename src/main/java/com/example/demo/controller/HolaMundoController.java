package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mundo")
public class HolaMundoController {

    // http://localhost:8080/mundo/hola
    @GetMapping("/hola")
    public String holaMundo() {
        return "hola_mundo";
    }

    // http://localhost:8080/mundo/chao
    @GetMapping("/chao")
    public String chaoMundo() {
        return "chao_mundo";
    }

}
