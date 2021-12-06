package com.ecommerce.mobile.controladores;

import com.ecommerce.mobile.entidades.Categoria;
import com.ecommerce.mobile.entidades.MedioPago;
import com.ecommerce.mobile.servicios.CategoriaServicio;
import com.ecommerce.mobile.servicios.MedioPagoServicio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/mediopagos")
public class MedioPagoControlador {
    @Autowired
    private MedioPagoServicio medioPagoServicio;

    // -- Insertar nuevo Medio de Pago
    @PostMapping("/agregar")
    public ResponseEntity<?> setMedioDePago(@RequestBody MedioPago medioPago){
        return ResponseEntity.status(HttpStatus.CREATED).body(medioPagoServicio.guardarMedioDePago(medioPago));
    }

    // -- Obtener un medio de pago
    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> getCategoriaPorId(@PathVariable(value = "id") int id){
        MedioPago medioPago = medioPagoServicio.obtenerMedioDePagoPorId(id);
        if (medioPago == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(medioPago);
    }

    // -- Actualizar información de un medio de pago
    @PutMapping("/{id}")
    public ResponseEntity<?> setInfoMedioDePago(@RequestBody MedioPago infoMedioPago, @PathVariable(value = "id") int id){
        MedioPago medioPagoObtenido = medioPagoServicio.obtenerMedioDePagoPorId(id);
        if (medioPagoObtenido == null){
            return ResponseEntity.notFound().build();
        }

        // -- Aca actualizo la informacion del medio de pago
        BeanUtils.copyProperties(infoMedioPago, medioPagoObtenido);

        return ResponseEntity.status(HttpStatus.CREATED).body(medioPagoServicio.guardarMedioDePago(medioPagoObtenido));
    }

    // -- Eliminar determinado medio de pago
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMedioDePago(@PathVariable(value = "id") int id){
        if (medioPagoServicio.obtenerMedioDePagoPorId(id) == null){
            return ResponseEntity.notFound().build();
        }

        medioPagoServicio.eliminarMedioDePago(id);
        return ResponseEntity.ok().build();
    }

    // -- Obtener todos los medios de pago
    @GetMapping
    public List<MedioPago> getMediosDePago(){
        List<MedioPago> medioPagos = StreamSupport.stream(medioPagoServicio.obtenerMediosDePago().spliterator(), false)
                .collect(Collectors.toList());

        return medioPagos;
    }
}
