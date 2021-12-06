package com.ecommerce.mobile.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(indexes = {@Index(name = "subcat01", columnList = "id_categoria")})
public class SubCategoria implements Serializable {

    @EmbeddedId @JsonProperty("id")                                                private SubCategoriaPK subCategoriaPK;
    @Column(nullable = false,
            length = 30, unique = true)                                            private String nombre;


    @ManyToOne
    @JoinColumn(name = "ID_CATEGORIA",
                referencedColumnName = "ID_CATEGORIA",
                insertable = false, updatable = false,
                foreignKey = @ForeignKey(name = "fk_subcategoria_categoria_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)                                      private Categoria categoria;


}
