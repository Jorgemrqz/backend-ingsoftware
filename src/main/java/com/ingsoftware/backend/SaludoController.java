package com.ingsoftware.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoController {

    @GetMapping("/saludo")
    public String saludar() {
        return "Hola mundo desde Spring Boot";
    }

}
