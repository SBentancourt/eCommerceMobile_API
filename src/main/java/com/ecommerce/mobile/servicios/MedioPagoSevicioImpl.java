package com.ecommerce.mobile.servicios;

import com.ecommerce.mobile.entidades.MedioPago;
import com.ecommerce.mobile.repositorios.MedioPagoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedioPagoSevicioImpl implements MedioPagoServicio{

    @Autowired
    private MedioPagoRepo medioPagoRepo;

    @Override
    @Transactional(readOnly = true)
    public MedioPago obtenerMedioDePagoPorId(int id) {
        return medioPagoRepo.findMedioPagoById(id);
    }

    @Override
    public Iterable<MedioPago> obtenerMediosDePago() {
        return medioPagoRepo.findAll();
    }

    @Override
    public MedioPago guardarMedioDePago(MedioPago medioPago) {
        return medioPagoRepo.save(medioPago);
    }

    @Override
    public void eliminarMedioDePago(int id) {
        medioPagoRepo.deleteMedioPagoById(id);
    }
}
