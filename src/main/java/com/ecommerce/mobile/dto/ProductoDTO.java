package com.ecommerce.mobile.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductoDTO implements Serializable {
    private String nombre;
    private String descripcion;
    private int Stock;
    private Double precio;
    private int id_categoria;
    private int id_subcategoria;
}
