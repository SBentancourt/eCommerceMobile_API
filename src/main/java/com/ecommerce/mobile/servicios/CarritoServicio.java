package com.ecommerce.mobile.servicios;

import com.ecommerce.mobile.entidades.Carrito;
import com.ecommerce.mobile.entidades.CarritoProducto;
import com.ecommerce.mobile.entidades.CarritoProductoPK;

import java.util.List;

public interface CarritoServicio {
    public Carrito obtenerCarritoPorId(int id);
    public Iterable<Carrito> obtenerCarritos();
    public Carrito guardarCarrito(Carrito carrito);
    public void eliminarCarrito(int id);

    public CarritoProducto obtenerCarritoProductoPorId(CarritoProductoPK id);
    public List<CarritoProducto> obtenerProductosDeCarrito(int id);
    public List<CarritoProducto> obtenerProductosDeCarritos();
    public CarritoProducto guardarCarritoProducto(CarritoProducto carritoProducto);
    public void eliminarProductoDeCarrito(CarritoProductoPK id);
}
