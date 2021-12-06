package com.ecommerce.mobile.repositorios;

import com.ecommerce.mobile.entidades.Categoria;
import com.ecommerce.mobile.entidades.MedioPago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedioPagoRepo extends JpaRepository<MedioPago, Integer> {
    MedioPago findMedioPagoById(int id);
    int deleteMedioPagoById(int id);
}
