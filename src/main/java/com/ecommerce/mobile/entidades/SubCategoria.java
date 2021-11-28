package com.ecommerce.mobile.entidades;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(indexes = {@Index(name = "FORANEO", columnList = "categoria_id")})
public class SubCategoria implements Serializable {
    @Id                                                                         private int id;
    @Column(nullable = false, length = 30)                                      private String nombre;
    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id",
            foreignKey = @ForeignKey(name = "fk_subcategoria_categoria_id"))    private Categoria categoria;
}
