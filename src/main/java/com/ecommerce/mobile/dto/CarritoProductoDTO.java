package com.ecommerce.mobile.dto;

import lombok.Data;

@Data
public class CarritoProductoDTO {
    private int id_producto;
    private int id_carrito;
    private int cantidad;

}
