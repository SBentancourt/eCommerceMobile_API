package com.ecommerce.mobile.servicios;

import com.ecommerce.mobile.entidades.Producto;

public interface ProductoServicio {
    public Producto obtenerProductoPorId(int id);
    public Iterable<Producto> obtenerProductos();
    public Producto guardarProducto(Producto producto);
    public void eliminarProducto(int id);
}
