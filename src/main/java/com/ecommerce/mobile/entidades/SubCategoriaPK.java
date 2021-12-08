package com.ecommerce.mobile.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoriaPK implements Serializable {
    @Column(name = "id_categoria", nullable = false)                        private int idCategoria;
    @Column(name = "id_subcategoria", nullable = false)                     private int idSubCategoria;

}
