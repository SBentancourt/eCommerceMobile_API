package com.ecommerce.mobile.controladores;

import com.ecommerce.mobile.dto.CarritoDTO;
import com.ecommerce.mobile.dto.CarritoProductoDTO;
import com.ecommerce.mobile.entidades.*;
import com.ecommerce.mobile.servicios.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/carritos")
public class CarritoControlador {

    @Autowired
    private CarritoServicio carritoServicio;
    @Autowired
    private ProductoServicio productoServicio;
    @Autowired
    private MedioPagoServicio medioPagoServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;


    // -- Operaciones que unicamente afectan a la entidad Carrito
    @PostMapping("/agregar")
    public ResponseEntity<?> setCarrito(@RequestBody CarritoDTO carrito){
        return ResponseEntity.status(HttpStatus.CREATED).body(carritoServicio.guardarCarritoProducto(crearNuevoCarrito(carrito)));
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> getCarritoPorId(@PathVariable(value = "id") int id){
        Carrito carrito = carritoServicio.obtenerCarritoPorId(id);
        if (carrito == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carrito);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> setInfoCarrito(@RequestBody CarritoDTO infoCarrito, @PathVariable(value = "id") int id){
        Carrito carritoObtenido = carritoServicio.obtenerCarritoPorId(id);
        if (carritoObtenido == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(carritoServicio.guardarCarrito(actualizarInfoCarrito(id, infoCarrito)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarrito(@PathVariable(value = "id") int id){
        if (carritoServicio.obtenerCarritoPorId(id) == null){
            return ResponseEntity.notFound().build();
        }

        carritoServicio.eliminarCarrito(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Carrito> getCarritos(){
        List<Carrito> carritos = StreamSupport.stream(carritoServicio.obtenerCarritos().spliterator(), false)
                .collect(Collectors.toList());

        return carritos;
    }

    private CarritoProducto crearNuevoCarrito(CarritoDTO carritoDTO){

        Producto producto = productoServicio.obtenerProductoPorId(carritoDTO.getId_producto());
        double totalPrdPorCant = producto.getPrecio()*carritoDTO.getCantidad();
        Carrito nuevoCarrito = carritoServicio.guardarCarrito(new Carrito(totalPrdPorCant, carritoDTO.getDirFactura()));

        Usuario usuario = usuarioServicio.obtenerUsuarioPorId(carritoDTO.getId_usuario()).get();

        CarritoProducto nuevoCarritoProducto = new CarritoProducto(nuevoCarrito, producto, usuario,carritoDTO.getCantidad(), totalPrdPorCant);

        return nuevoCarritoProducto;
    }

    private Carrito actualizarInfoCarrito(int idCarrito, CarritoDTO infoNuevaCarrito){
        Carrito carrito = carritoServicio.obtenerCarritoPorId(idCarrito);
        carrito.setDirFactura(infoNuevaCarrito.getDirFactura());
        carrito.setMedioPago(medioPagoServicio.obtenerMedioDePagoPorId(infoNuevaCarrito.getId_mediopago()));

        return carrito;
    }


    // -- Operaciones que unicamente afectan a la entidad CarritoProducto
    @PostMapping("/productos/agregar")
    public ResponseEntity<?> setCarritoProducto(@RequestBody CarritoProductoDTO carritoProductoDTO){

        CarritoProductoPK idCarritoPrd = new CarritoProductoPK(carritoProductoDTO.getId_carrito(), carritoProductoDTO.getId_producto(),
                                                                carritoProductoDTO.getId_usuario());

        CarritoProducto carritoPrd = carritoServicio.obtenerCarritoProductoPorId(idCarritoPrd);

        Producto prd = productoServicio.obtenerProductoPorId(carritoProductoDTO.getId_producto());
        double totalPrd = prd.getPrecio()*carritoProductoDTO.getCantidad();
        Carrito carrito = carritoServicio.obtenerCarritoPorId(carritoProductoDTO.getId_carrito());
        Boolean esNuevoPrdEnCarrito = false;
        if (carritoPrd == null){
            Usuario usuario = usuarioServicio.obtenerUsuarioPorId(carritoProductoDTO.getId_usuario()).get();
            CarritoProducto nuevoCarritoProducto = new CarritoProducto(carrito, prd, usuario, carritoProductoDTO.getCantidad(), totalPrd);
            carritoPrd = nuevoCarritoProducto;
            esNuevoPrdEnCarrito = true;
        } else {
            carritoPrd.setCantidad(carritoProductoDTO.getCantidad());
            carritoPrd.setTotal(totalPrd);
        }

        // -- Aca se actualiza el importe total que se graba en la tabla Carrito
        List<CarritoProducto> productosDeCarrito = carritoServicio.obtenerProductosDeCarrito(carritoProductoDTO.getId_carrito());
        double importeTotalCarrito = 0;
        if (esNuevoPrdEnCarrito){
            importeTotalCarrito = totalPrd;
        }
        for (CarritoProducto iterPrd: productosDeCarrito){
            importeTotalCarrito = importeTotalCarrito + iterPrd.getTotal();
        }
        carrito.setImporteTotal(importeTotalCarrito);
        carritoServicio.guardarCarrito(carrito);    // -- aca actualizo el Carrito

        return ResponseEntity.status(HttpStatus.CREATED).body(carritoServicio.guardarCarritoProducto(carritoPrd));
    }

    @GetMapping("/productos/obtener/{idcarrito}/{idproducto}/{idusuario}")
    public ResponseEntity<?> getCarritoProductoPorId(@PathVariable(value = "idcarrito") int idCarrito,
                                                     @PathVariable(value = "idproducto") int idProducto,
                                                     @PathVariable(value = "idusuario") String idUsuario){
        CarritoProductoPK id = new CarritoProductoPK(idCarrito, idProducto, idUsuario);
        CarritoProducto carritoProducto = carritoServicio.obtenerCarritoProductoPorId(id);
        if (carritoProducto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carritoProducto);
    }

    @GetMapping("/productos/obtener/{idcarrito}")
    public List<CarritoProducto> getCarritoProductoPorId(@PathVariable(value = "idcarrito") int idCarrito){
        List<CarritoProducto> carritoProducto = StreamSupport.stream(carritoServicio.obtenerProductosDeCarrito(idCarrito).spliterator(), false)
                .collect(Collectors.toList());
        return carritoProducto;
    }

    @GetMapping("/productos")
    public List<CarritoProducto> getProductosDeCarritos(){
        List<CarritoProducto> carritoProducto = StreamSupport.stream(carritoServicio.obtenerProductosDeCarritos().spliterator(), false)
                .collect(Collectors.toList());
        return carritoProducto;
    }

    @DeleteMapping("/productos/{idcarrito}/{idproducto}/{idusuario}")
    public ResponseEntity<?> deleteCarritoProducto(@PathVariable(value = "idcarrito") int idCarrito,
                                                   @PathVariable(value = "idproducto") int idProducto,
                                                   @PathVariable(value = "idusuario") String idUsuario){
        CarritoProductoPK id = new CarritoProductoPK(idCarrito, idProducto, idUsuario);
        CarritoProducto carritoProductoBorrar = carritoServicio.obtenerCarritoProductoPorId(id);
        if ( carritoProductoBorrar == null){
            return ResponseEntity.notFound().build();
        }

        carritoServicio.eliminarProductoDeCarrito(id);

        // -- actualizo el total del carrito
        Carrito carrito = carritoServicio.obtenerCarritoPorId(idCarrito);
        carrito.setImporteTotal(carrito.getImporteTotal() - carritoProductoBorrar.getTotal());
        carritoServicio.guardarCarrito(carrito);

        return ResponseEntity.ok().build();
    }

}
