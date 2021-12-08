package com.ecommerce.mobile.controladores;

import com.ecommerce.mobile.dto.ProductoDTO;
import com.ecommerce.mobile.entidades.Producto;
import com.ecommerce.mobile.entidades.SubCategoria;
import com.ecommerce.mobile.entidades.SubCategoriaPK;
import com.ecommerce.mobile.servicios.CategoriaServicio;
import com.ecommerce.mobile.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/productos")
public class ProductoControlador {

    @Autowired
    private ProductoServicio productoServicio;
    @Autowired
    private CategoriaServicio categoriaServicio;

    // -- Insertar una nuevo producto
    @PostMapping("/agregar")
    public ResponseEntity<?> setProducto(@RequestBody ProductoDTO producto){
        Producto nuevoPrd = nuevoProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoServicio.guardarProducto(nuevoPrd));
    }

    // -- Obtener un determinado producto
    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> getProductoPorId(@PathVariable(value = "id") int id){
        Producto producto = productoServicio.obtenerProductoPorId(id);
        if (producto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }

    // -- Actualizar información de un producto
    @PutMapping("/{id}")
    public ResponseEntity<?> setInfoProducto(@RequestBody ProductoDTO infoProducto, @PathVariable(value = "id") int id){
        Producto productoExistente = productoServicio.obtenerProductoPorId(id);
        if (productoExistente == null){
            return ResponseEntity.notFound().build();
        }

        // -- Aca actualizo la informacion del producto
        Producto productoActualizado = actualizarProducto(productoExistente, infoProducto);

        return ResponseEntity.status(HttpStatus.CREATED).body(productoServicio.guardarProducto(productoActualizado));
    }

    // -- Eliminar determinado producto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable(value = "id") int id){
        if (productoServicio.obtenerProductoPorId(id) == null){
            return ResponseEntity.notFound().build();
        }

        productoServicio.eliminarProducto(id);
        return ResponseEntity.ok().build();
    }

    // -- Obtener todos los productos
    @GetMapping
    public List<Producto> getProductos(){
        List<Producto> productos = StreamSupport.stream(productoServicio.obtenerProductos().spliterator(), false)
                .collect(Collectors.toList());

        return productos;
    }

    private Producto nuevoProducto(ProductoDTO producto) {

        SubCategoriaPK idSubCat = new SubCategoriaPK(producto.getId_categoria(), producto.getId_subcategoria());
        SubCategoria subCat = categoriaServicio.obtenerSubCategoria(idSubCat);

        Producto nuevoPrd = new Producto(producto.getNombre(), producto.getDescripcion(), producto.getStock(),
                                            producto.getPrecio(), subCat);
        return nuevoPrd;
    }

    private Producto actualizarProducto(Producto productoExistente, ProductoDTO infoProducto){

        SubCategoriaPK idSubCat = new SubCategoriaPK(infoProducto.getId_categoria(), infoProducto.getId_subcategoria());
        SubCategoria subCat = categoriaServicio.obtenerSubCategoria(idSubCat);

        productoExistente.setNombre(infoProducto.getNombre());
        productoExistente.setDescripcion(infoProducto.getDescripcion());
        productoExistente.setPrecio(infoProducto.getPrecio());
        productoExistente.setStock(infoProducto.getStock());
        productoExistente.setSubCategoria(subCat);

        return productoExistente;
    }
}
