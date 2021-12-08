package com.ecommerce.mobile.dto;

import lombok.Data;

@Data
public class CarritoDTO {
    private String id_usuario;
    private int id_producto;
    private int id_carrito;
    private int id_mediopago;
    private String dirFactura;
    private int cantidad;
}
