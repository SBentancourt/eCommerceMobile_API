package com.ecommerce.mobile.repositorios;

import com.ecommerce.mobile.entidades.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepo extends JpaRepository<Carrito, Integer> {
    Carrito findCarritoById(int id);
    void deleteCarritoById(int id);
}
