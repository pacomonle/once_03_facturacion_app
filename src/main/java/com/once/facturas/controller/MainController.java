package com.once.facturas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * MainController es el controlador de la aplicación Facturas,
 * que es una aplicación MVC (Modelo Vista Controlador)
 * 
 * 
 */

@Controller
class MainController {
    @GetMapping("/hello") // Escucho al GET en /time
    @ResponseBody // Haré un body html para devolver la página completa
    public String hello() { // Método para devolver un string para responsebody
        return "Hola Mundo"; // Devulevo Hola Mundo
    }
    
}