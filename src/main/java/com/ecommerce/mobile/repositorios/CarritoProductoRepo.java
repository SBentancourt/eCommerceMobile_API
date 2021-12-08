package com.ecommerce.mobile.repositorios;

import com.ecommerce.mobile.entidades.Carrito;
import com.ecommerce.mobile.entidades.CarritoProducto;
import com.ecommerce.mobile.entidades.CarritoProductoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CarritoProductoRepo extends JpaRepository<CarritoProducto, CarritoProductoPK> {
    CarritoProducto findCarritoProductoById(CarritoProductoPK id);
    List<CarritoProducto> findCarritoProductosByCarrito_Id(int idCarrito);
    void deleteCarritoProductoById(CarritoProductoPK id);
    // --> Al eliminar el carrito, automaticamente se borra CarritoProducto
    //void deleteCarritoProductosByCarrito_Id(int idCarrito);

    /*@Transactional
    @Modifying
    @Query("update CarritoProducto cp set cp.cantidad = ?1, cp.total = ?2 where cp.carrito = ?3 and cp.producto = ?4")
    void updateCantidadTotalCarritoProd(int cant, int total, int idCarrito, int idProducto);*/
}
