/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tutocrud.crud.repository;

import com.tutocrud.crud.entity.Producto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
/**
 *
 * @author i3-nativan
 */
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Optional<Producto> findByNombre (String nombre);
    boolean existsByNombre (String nombre);
}
