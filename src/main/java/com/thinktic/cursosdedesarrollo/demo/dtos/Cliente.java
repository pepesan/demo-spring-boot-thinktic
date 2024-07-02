package com.thinktic.cursosdedesarrollo.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private Integer id;
    private String nombre;

    public Cliente(ClienteDto clienteDto){
        this.nombre = clienteDto.getNombre();
        this.id = 0;
    }
    public Cliente(ClienteDto clienteDto, Integer id){
        this.nombre = clienteDto.getNombre();
        this.id = id;
    }
}
