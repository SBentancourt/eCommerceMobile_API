package com.ecommerce.mobile.servicios;

import com.ecommerce.mobile.entidades.Categoria;
import com.ecommerce.mobile.entidades.MedioPago;

public interface MedioPagoServicio {
    public MedioPago obtenerMedioDePagoPorId(int id);
    public Iterable<MedioPago> obtenerMediosDePago();
    public MedioPago guardarMedioDePago(MedioPago medioPago);
    public void eliminarMedioDePago(int id);
}
