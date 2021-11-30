package com.ecommerce.mobile.controladores;

import com.ecommerce.mobile.entidades.Categoria;
import com.ecommerce.mobile.entidades.Usuario;
import com.ecommerce.mobile.servicios.CategoriaServicio;
import com.ecommerce.mobile.servicios.UsuarioServicio;
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
@RequestMapping("/api/categorias")
public class CategoriaControlador {

    @Autowired
    private CategoriaServicio categoriaServicio;

    // -- Insertar una nueva Categoria
    @PostMapping("/agregar")
    public ResponseEntity<?> setCategoria(@RequestBody Categoria categoria){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaServicio.guardarCategoria(categoria));
    }

    // -- Obtener una categoria determinada
    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> getCategoriaPorId(@PathVariable(value = "id") int id){
        Categoria categoria = categoriaServicio.obtenerCategoriaPorId(id);
        if (categoria == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoria);
    }

    // -- Actualizar información de una categoria
    @PutMapping("/{id}")
    public ResponseEntity<?> setInfoCategoria(@RequestBody Categoria infoCategoria, @PathVariable(value = "id") int id){
        Categoria categoriaObtenida = categoriaServicio.obtenerCategoriaPorId(id);
        if (categoriaObtenida == null){
            return ResponseEntity.notFound().build();
        }

        // -- Aca actualizo la informacion de la categoria
        BeanUtils.copyProperties(infoCategoria, categoriaObtenida);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaServicio.guardarCategoria(categoriaObtenida));
    }

    // -- Eliminar determinada categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable(value = "id") int id){
        if (categoriaServicio.obtenerCategoriaPorId(id) == null){
            return ResponseEntity.notFound().build();
        }

        categoriaServicio.eliminarCategoria(id);
        return ResponseEntity.ok().build();
    }

    // -- Obtener todas las categorias
    @GetMapping
    public List<Categoria> getCategorias(){
        List<Categoria> categorias = StreamSupport.stream(categoriaServicio.obtenerCategorias().spliterator(), false)
                .collect(Collectors.toList());

        return categorias;
    }
}
