package com.ecommerce.mobile.entidades;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SubCategoriaPK implements Serializable {
    @Column(name = "id_categoria", nullable = false)                        private int idCategoria;
    @Column(name = "id_subcategoria", nullable = false)                     private int idSubCategoria;
}
