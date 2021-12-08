package com.ecommerce.mobile.controladores;

import com.ecommerce.mobile.dto.SubCategoriaDTO;
import com.ecommerce.mobile.entidades.Categoria;
import com.ecommerce.mobile.entidades.SubCategoria;
import com.ecommerce.mobile.entidades.SubCategoriaPK;
import com.ecommerce.mobile.servicios.CategoriaServicio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    // -- ** SUB CATEGORIAS ** -- //

    // -- Insertar una nueva SubCategoria
    @PostMapping("/sub/agregar")
    public ResponseEntity<?> setSubCategoria(@RequestBody SubCategoriaDTO subCategoriaDTO){
        SubCategoria subCategoria = nuevaSubCategoria(subCategoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaServicio.guardarSubCategoria(subCategoria));
    }

    // -- Obtener una sub categoria determinada
    @GetMapping("/sub/obtener/{idcat}/{idscat}")
    public ResponseEntity<?> getSubCategoriaPorId(@PathVariable(value = "idcat") int idcat,
                                                  @PathVariable(value = "idscat") int idscat){
        SubCategoria subCategoria = obtenerSubCategoria(idcat, idscat);
        if (subCategoria == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(subCategoria);
    }

    // -- Obtener sub categorias de categoria
    @GetMapping("/sub/obtener/{idcat}")
    public ResponseEntity<?> getSubCategoriasDeCategoria(@PathVariable(value = "idcat") int idcat){
        List<SubCategoria> subCategorias = categoriaServicio.obtenerSubCategoriasDeCat(idcat);
        if (subCategorias.isEmpty() || subCategorias == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(subCategorias);
    }

    // -- Actualizar información de una subcategoria
    @PutMapping("/sub/{idcat}/{idscat}")
    public ResponseEntity<?> setInfoSubCategoria(@RequestBody SubCategoriaDTO infoSubCategoria, @PathVariable(value = "idcat") int idcat,
                                                 @PathVariable(value = "idscat") int idscat){

        categoriaServicio.actualizarSubCategoria(infoSubCategoria.getNombreSubCat(),idcat,idscat);
        return ResponseEntity.ok().build();
    }

    // -- Eliminar determinada sub categoria
    @DeleteMapping("/sub/{idcat}/{idscat}")
    public ResponseEntity<?> deleteSubCategoria(@PathVariable(value = "idcat") int idcat, @PathVariable(value = "idscat") int idscat){

        SubCategoriaPK subcatPK = crearSubCategoriaPK(idcat, idscat);

        if (categoriaServicio.obtenerSubCategoria(subcatPK) == null){
            return ResponseEntity.notFound().build();
        }

        categoriaServicio.eliminarSubCategoria(subcatPK);
        return ResponseEntity.ok().build();
    }

    // -- Operaciones internas -- //

    private SubCategoria nuevaSubCategoria(SubCategoriaDTO subCategoriaDTO){

        // -- Aca se obtiene la última subcategoria de la categoria indicada
        SubCategoria ultimaSCat = categoriaServicio.obtenerUltimaSubCategoria(subCategoriaDTO.getIdCategoria());
        int nuevaSCat = 0;
        // -- Aca se define el nuevo id de la nueva subcategoria que se va a agregar
        if (ultimaSCat == null){
            nuevaSCat = 1;
        } else {
            nuevaSCat = ultimaSCat.getSubCategoriaPK().getIdSubCategoria() + 1;
        }

        Categoria categoria = categoriaServicio.obtenerCategoriaPorId(subCategoriaDTO.getIdCategoria());

        SubCategoriaPK subCategoriaPK = crearSubCategoriaPK(subCategoriaDTO.getIdCategoria(), nuevaSCat);;

        SubCategoria subCategoria = new SubCategoria(subCategoriaPK, subCategoriaDTO.getNombreSubCat(), categoria);
        return subCategoria;
    }

    private SubCategoria obtenerSubCategoria(int idcat, int idscat){
        SubCategoriaPK subcatId = new SubCategoriaPK(idcat, idscat);
        return categoriaServicio.obtenerSubCategoria(subcatId);
    }

    private SubCategoriaPK crearSubCategoriaPK(int idcat, int idscat){
        SubCategoriaPK subcatId = new SubCategoriaPK(idcat, idscat);
        return subcatId;
    }

}
