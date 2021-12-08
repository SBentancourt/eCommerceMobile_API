package com.ecommerce.mobile.dto;

import lombok.Data;

import java.util.List;

@Data
public class CarritoDTO {
    private String id_usuario;
    private String dirFactura;
    private int id_mediopago;
    private int id_producto;
    private int cantidad;
}
