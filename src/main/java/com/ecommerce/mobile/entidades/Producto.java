package com.ecommerce.mobile.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     private int id;
    @Column(length = 20, nullable = false)                  private String nombre;
    @Column(length = 50)                                    private String descripcion;
                                                            private int stock;
                                                            private Double precio;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumns(value = {
            @JoinColumn(name = "id_categoria",
                        referencedColumnName = "ID_CATEGORIA"),
            @JoinColumn(name = "id_subcategoria",
                        referencedColumnName = "id_subcategoria"),
    }, foreignKey = @ForeignKey(name = "fk_producto_subcategoria_id"))
                                                            private SubCategoria subCategoria;


    @JsonIgnore
    @OneToMany(mappedBy = "producto")
    List<CarritoProducto> productosEnCarrito;

    public Producto(String nombre, String descripcion, int stock, Double precio, SubCategoria subCategoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
        this.subCategoria = subCategoria;
    }
}
