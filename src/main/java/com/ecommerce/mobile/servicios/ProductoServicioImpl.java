package com.ecommerce.mobile.servicios;

import com.ecommerce.mobile.entidades.Producto;
import com.ecommerce.mobile.repositorios.ProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServicioImpl implements ProductoServicio{
    @Autowired
    private ProductoRepo productoRepo;

    @Override
    @Transactional(readOnly = true)
    public Producto obtenerProductoPorId(int id) {
        return productoRepo.findProductoById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Producto> obtenerProductos() {
        return productoRepo.findAll();
    }

    @Override
    @Transactional
    public Producto guardarProducto(Producto producto) {
        return productoRepo.save(producto);
    }

    @Override
    @Transactional
    public void eliminarProducto(int id) {
        productoRepo.deleteProductoById(id);
    }
}
