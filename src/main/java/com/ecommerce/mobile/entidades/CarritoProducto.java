package com.ecommerce.mobile.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity     @NoArgsConstructor
@Table(indexes = {@Index(name = "CarritoProd01", columnList = "id_producto"),
                    @Index(name = "CarritoProd02", columnList = "id_usuario")})
public class CarritoProducto implements Serializable {
    @EmbeddedId
    @JsonProperty("id")                                                             private CarritoProductoPK id;

    @ManyToOne
    @MapsId("idCarrito")
    @JoinColumn(name = "id_carrito",
            foreignKey = @ForeignKey(name = "fk_carrito_producto_carrito_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)                                      private Carrito carrito;

    @ManyToOne
    @MapsId("idProducto")
    @JoinColumn(name = "id_producto",
            foreignKey = @ForeignKey(name = "fk_carrito_producto_producto_id"))     private Producto producto;

    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario",
            foreignKey = @ForeignKey(name = "fk_carrito_producto_usuario_id"))      private Usuario usuario;

                                                                                    private int cantidad;

                                                                                    private double total;

    public CarritoProducto(Carrito carrito, Producto producto, Usuario usuario,int cantidad, double total) {
        this.id = new CarritoProductoPK(carrito.getId(), producto.getId(), usuario.getCorreo());
        this.carrito = carrito;
        this.producto = producto;
        this.usuario = usuario;
        this.cantidad = cantidad;
        this.total = total;
    }
}
