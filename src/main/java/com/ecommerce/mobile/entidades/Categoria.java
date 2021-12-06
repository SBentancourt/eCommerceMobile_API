package com.ecommerce.mobile.entidades;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATEGORIA")                              private int id;

    @Column(nullable = false, length = 30, unique = true)       private String nombre;
}

