package com.ecommerce.mobile.entidades;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(indexes = {@Index(name = "PRIMARY", columnList = "correo"),
                    @Index(name = "usuario02", columnList = "cedula", unique = true)})
public class Usuario implements Serializable {
    @Id                                     private String correo;
    @Column(length = 30, nullable = false)  private String clave;
    @Column(length = 40)                    private String nombre;
    @Column(length = 40)                    private String apellido;
                                            private int cedula;
                                            private Date fecha;
    @Column(length = 40)                    private String pais;
    @Column(length = 60)                    private String direccion;
                                            private int celular;
                                            private Boolean admin;
}
