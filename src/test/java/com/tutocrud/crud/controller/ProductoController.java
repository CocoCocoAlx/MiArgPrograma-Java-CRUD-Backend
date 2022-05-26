/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tutocrud.crud.controller;

import com.tutocrud.crud.dto.Mensaje;
import com.tutocrud.crud.dto.ProductoDto;
import com.tutocrud.crud.entity.Producto;
import com.tutocrud.crud.service.ProductoService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author i3-nativan
 */
@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "http://localhost:4200")

public class ProductoController {

    @Autowired
    ProductoService productoService;
/** mostrar la lista de productos
     * @return  */
    @GetMapping("/lista")
    public ResponseEntity<List<Producto>> list() {
        List<Producto> list = productoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/test")
    public String decirHola(){
        return "Hola";
    }
    /** mostrar producto por ID, pero primero vefiricar que existe
     * @param id
     * @return  */
        @GetMapping("/detalle/{id}")
        public ResponseEntity<Producto> getById(@PathVariable("id") int id) {
        if (!productoService.existsById(id))
                return new ResponseEntity(new Mensaje ("El producto no existe") , HttpStatus.NOT_FOUND);
        Producto producto = productoService.getOne(id).get();
        return new ResponseEntity<Producto>(producto, HttpStatus.OK);
            }
        @GetMapping("/detallenombre/{nombre}")
        public ResponseEntity<Producto> getByNombre(@PathVariable("nombre") String nombre) {
        if (!productoService.existsByNombre(nombre))
                return new ResponseEntity(new Mensaje ("El producto no existe") , HttpStatus.NOT_FOUND);
        Producto producto = productoService.getByNombre(nombre).get();
        return new ResponseEntity<Producto>(producto, HttpStatus.OK);
            }
        /** espacio para crear producto con condiciones y verificando si no existe */
        @PostMapping("/crear")
        public ResponseEntity<?> create(@RequestBody ProductoDto productoDto){
            if (StringUtils.isBlank (productoDto.getNombre()))
                return new ResponseEntity(new Mensaje("El nombre del producto es obligatorio"),HttpStatus.BAD_REQUEST);
            if (productoDto.getPrecio()<0)
                return new ResponseEntity(new Mensaje("El precio debe ser igual o mayor a cero"),HttpStatus.BAD_REQUEST);
            if (productoService.existsByNombre(productoDto.getNombre()))
                return new ResponseEntity(new Mensaje("El nombre del producto ya existe"),HttpStatus.BAD_REQUEST);
            Producto producto = new Producto(productoDto.getNombre(),productoDto.getPrecio());
            productoService.save(producto);
            return new ResponseEntity(new Mensaje("Producto creado"),HttpStatus.OK);
        }
        @PutMapping("/actualizar/{id}")
        public ResponseEntity<?> update(@PathVariable("id") int id, ProductoDto productoDto){
            if (!productoService.existsById(id))
                return new ResponseEntity(new Mensaje ("El producto no existe") , HttpStatus.NOT_FOUND);
            if (productoService.existsByNombre(productoDto.getNombre()) && productoService.getByNombre(productoDto.getNombre()).get().getId() != id)
                return new ResponseEntity(new Mensaje ("Ese nombre de producto ya existe") , HttpStatus.BAD_REQUEST);
            if (StringUtils.isBlank (productoDto.getNombre()))
                return new ResponseEntity(new Mensaje("El nombre del producto es obligatorio"),HttpStatus.BAD_REQUEST);
            if (productoDto.getPrecio()<0)
                return new ResponseEntity(new Mensaje("El precio debe ser igual o mayor a cero"),HttpStatus.BAD_REQUEST);
            if (productoService.existsByNombre(productoDto.getNombre()))
                return new ResponseEntity(new Mensaje("El nombre del producto ya existe"),HttpStatus.BAD_REQUEST);
            Producto producto = productoService.getOne(id).get();
            producto.setNombre(productoDto.getNombre());
            producto.setPrecio(productoDto.getPrecio());
            productoService.save(producto);
            return new ResponseEntity(new Mensaje("Producto actualizado"),HttpStatus.OK);
        }
        @DeleteMapping("/borrar/{id}")
        public ResponseEntity<?> delete(@PathVariable("id") int id){
            if (!productoService.existsById(id))
                return new ResponseEntity(new Mensaje ("El producto no existe") , HttpStatus.NOT_FOUND);
            productoService.delete(id);
            return new ResponseEntity(new Mensaje("Producto eliminado"),HttpStatus.OK);
        }
        }
