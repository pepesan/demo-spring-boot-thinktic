package com.thinktic.cursosdedesarrollo.demo.controllers;

import com.thinktic.cursosdedesarrollo.demo.dtos.Cliente;
import com.thinktic.cursosdedesarrollo.demo.dtos.ClienteDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2/dato")
public class ApiController
{
    List<Cliente> listado = new ArrayList<>();
    Integer lastId = 0;


    @GetMapping("/")
    public ResponseEntity<List<Cliente>> index(){
        return ResponseEntity.ok(this.listado);
    }

    @PostMapping("/")
    public ResponseEntity<Cliente> add(
            @Valid @RequestBody ClienteDto clienteDto
            ){
        lastId++;
        Cliente client = new Cliente(clienteDto, lastId);
        this.listado.add(client);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable("id") Integer id){
        Optional<Cliente> posibleCliente = this.listado
                .stream()
                .filter(cliente -> cliente.getId().equals(id))
                .findFirst();
        if (posibleCliente.isPresent()){
            return ResponseEntity.ok(posibleCliente.get());
        }else{
            return ResponseEntity.notFound().build();
        }
        /*
        return this.listado
                .stream()
                .filter(cliente -> cliente.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
         */
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateById(
            @PathVariable("id") Integer id,
            @Valid @RequestBody ClienteDto clienteDto
    ){
        Optional<Cliente> posibleCliente = this.listado
                .stream()
                .filter(cliente -> cliente.getId().equals(id))
                .findFirst();
        if (posibleCliente.isPresent()){
            Cliente clienteGuardado = posibleCliente.get();
            clienteGuardado.setNombre(clienteDto.getNombre());
            return ResponseEntity.ok(clienteGuardado);
        }else{
            return ResponseEntity.notFound().build();
        }
        /*
        return this.listado
                .stream()
                .filter(cliente -> cliente.getId().equals(id))
                .findFirst()
                .map(clienteEncontrado -> {
                    clienteEncontrado.setNombre(clienteDto.getNombre());
                    return ResponseEntity.ok(clienteEncontrado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
        */
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> deleteById(@PathVariable("id") Integer id){
        Optional<Cliente> posibleCliente = this.listado
                .stream()
                .filter(cliente -> cliente.getId().equals(id))
                .findFirst();
        if (posibleCliente.isPresent()){
            Cliente clienteEncontrado = posibleCliente.get();
            this.listado.remove(clienteEncontrado);
            return ResponseEntity.ok(clienteEncontrado);
        }else{
            return ResponseEntity.notFound().build();
        }
        /*
        return this.listado
                .stream()
                .filter(cliente -> cliente.getId().equals(id))
                .findFirst()
                .map( clienteEncontrado -> {
                    this.listado.remove(clienteEncontrado);
                    return ResponseEntity.ok(clienteEncontrado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
         */
    }
}
