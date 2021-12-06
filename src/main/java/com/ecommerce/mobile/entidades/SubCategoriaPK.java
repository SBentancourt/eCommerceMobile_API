package com.ecommerce.mobile.entidades;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class SubCategoriaPK implements Serializable {
    @Column(name = "id_categoria", nullable = false)                        private int idCategoria;
    @Column(name = "id_subcategoria", nullable = false)                     private int idSubCategoria;

}
