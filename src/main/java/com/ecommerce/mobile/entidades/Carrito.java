package com.ecommerce.mobile.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Carrito implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = true)
    private double importeTotal;
    @Column(length = 50)
    private String dirFactura;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mediopago",
                referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "fk_carrito_mediopago_id"))
    private MedioPago medioPago;



    @JsonIgnore
    @OneToMany(mappedBy = "carrito")
    List<CarritoProducto> productosEnCarrito;

    public Carrito() {
    }

    public Carrito(double importeTotal, String dirFactura) {
        this.importeTotal = importeTotal;
        this.dirFactura = dirFactura;
    }
}
