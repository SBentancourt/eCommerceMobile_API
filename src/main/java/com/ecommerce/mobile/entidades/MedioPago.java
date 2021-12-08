package com.ecommerce.mobile.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedioPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)             private int id;
    @Column(length = 30, unique = true)                             private String nombre;
}
