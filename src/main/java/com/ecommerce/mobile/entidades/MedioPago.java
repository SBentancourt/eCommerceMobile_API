package com.ecommerce.mobile.entidades;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MedioPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)             private int id;
    @Column(length = 30, unique = true)                             private String nombre;
}
