package com.ecommerce.mobile.entidades;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(indexes = {@Index(name = "categoria02", columnList = "nombre", unique = true)})
public class Categoria implements Serializable {
    @Id                                         private int id;
    @Column(nullable = false, length = 30)      private String nombre;
}
