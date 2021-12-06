package com.ecommerce.mobile.entidades;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class MedioPago {
    @Id                                     private int id;
    @Column(length = 30, unique = true)     private String nombre;
}
