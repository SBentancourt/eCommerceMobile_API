package com.ecommerce.mobile.repositorios;

import com.ecommerce.mobile.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer> {
    Producto findProductoById(int id);
    int deleteProductoById(int id);
}
