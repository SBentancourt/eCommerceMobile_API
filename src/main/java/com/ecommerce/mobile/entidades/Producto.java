package com.ecommerce.mobile.entidades;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Producto {
    @Id                                             private int id;
    @Column(length = 20, nullable = false)          private String nombre;
    @Column(length = 50)                            private String descripcion;
                                                    private int Stock;
                                                    private Double precio;
    @ManyToOne
    @JoinColumns(value = {
            @JoinColumn(name = "id_categoria",
                        referencedColumnName = "ID_CATEGORIA"),
            @JoinColumn(name = "id_subcategoria",
                        referencedColumnName = "id_subcategoria"),
    }, foreignKey = @ForeignKey(name = "fk_producto_subcategoria_id"))
                                                    private SubCategoria subCategoria;
}
