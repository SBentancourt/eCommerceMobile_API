package com.ecommerce.mobile.dto;

import lombok.Data;

@Data
public class CarritoProductoDTO {
    private int id_producto;
    private int id_carrito;
    private String id_usuario;
    private int cantidad;

}
