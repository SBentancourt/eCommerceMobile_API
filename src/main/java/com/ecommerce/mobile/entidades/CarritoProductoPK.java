package com.ecommerce.mobile.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor @AllArgsConstructor
public class CarritoProductoPK implements Serializable {
    @Column(name = "id_carrito", nullable = false)      private int idCarrito;
    @Column(name = "id_producto", nullable = false)     private int idProducto;
    @Column(name = "id_usuario", nullable = false)      private String idUsuario;

}
