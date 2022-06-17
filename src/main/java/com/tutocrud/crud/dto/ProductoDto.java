/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tutocrud.crud.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author i3-nativan
 */
public class ProductoDto {
    @NotBlank
    private String nombre;
    @Min(0)
    private float precio;
    @NotBlank
    private String descripcion;

    public ProductoDto() {
    }

    public ProductoDto(String nombre, float precio, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
