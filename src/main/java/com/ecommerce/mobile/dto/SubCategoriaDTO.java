package com.ecommerce.mobile.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class SubCategoriaDTO implements Serializable {
    @JsonProperty("idcategoria")
    private int idCategoria;
    @JsonProperty("nombre")
    private String nombreSubCat;
}
