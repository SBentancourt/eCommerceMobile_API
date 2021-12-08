package com.ecommerce.mobile.servicios;

import com.ecommerce.mobile.entidades.Carrito;
import com.ecommerce.mobile.entidades.CarritoProducto;
import com.ecommerce.mobile.entidades.CarritoProductoPK;
import com.ecommerce.mobile.entidades.Categoria;
import com.ecommerce.mobile.repositorios.CarritoProductoRepo;
import com.ecommerce.mobile.repositorios.CarritoRepo;
import com.ecommerce.mobile.repositorios.CategoriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarritoServicioImpl implements CarritoServicio {
    @Autowired
    private CarritoRepo carritoRepo;
    @Autowired
    private CarritoProductoRepo carritoProductoRepo;

    @Override
    @Transactional(readOnly = true)
    public Carrito obtenerCarritoPorId(int id) {
        return carritoRepo.findCarritoById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Carrito> obtenerCarritos() {
        return carritoRepo.findAll();
    }

    @Override
    @Transactional
    public Carrito guardarCarrito(Carrito carrito) {
        return carritoRepo.save(carrito);
    }

    @Override
    @Transactional
    public void eliminarCarrito(int id) {
        carritoRepo.deleteCarritoById(id);
    }

    // -- Servicios de carrito producto

    @Override
    @Transactional(readOnly = true)
    public CarritoProducto obtenerCarritoProductoPorId(CarritoProductoPK id) {
        return carritoProductoRepo.findCarritoProductoById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarritoProducto> obtenerProductosDeCarrito(int id) {
        return carritoProductoRepo.findCarritoProductosByCarrito_Id(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarritoProducto> obtenerProductosDeCarritos() {
        return carritoProductoRepo.findAll();
    }

    @Override
    @Transactional
    public CarritoProducto guardarCarritoProducto(CarritoProducto carritoProducto) {
        return carritoProductoRepo.save(carritoProducto);
    }

    @Override
    @Transactional
    public void eliminarProductoDeCarrito(CarritoProductoPK id) {
        carritoProductoRepo.deleteCarritoProductoById(id);
    }
}
