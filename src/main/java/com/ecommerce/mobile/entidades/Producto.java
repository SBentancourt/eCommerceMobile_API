package com.ecommerce.mobile.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     private int id;
    @Column(length = 20, nullable = false)                  private String nombre;
    @Column(length = 50)                                    private String descripcion;
                                                            private int Stock;
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

}
