package com.thinktic.cursosdedesarrollo.demo.controllers;

import com.thinktic.cursosdedesarrollo.demo.dtos.Dato;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MainController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    @GetMapping("/hola")
    public String hola(){
        return "hola";
    }
    @PostMapping("/envia")
    public Dato echoDato(@RequestBody Dato dato){
        return dato;
    }
    @GetMapping("/params")
    public String getParams(@RequestParam("name") String nombre){
        return nombre;
    }
    @GetMapping("/{slug}")
    public String manejaCosa(
            @PathVariable("slug") String slug){
        return slug;
    }

}
