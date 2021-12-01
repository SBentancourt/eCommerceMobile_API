package com.ecommerce.mobile.servicios;

import com.ecommerce.mobile.entidades.Categoria;
import com.ecommerce.mobile.entidades.SubCategoria;
import com.ecommerce.mobile.entidades.SubCategoriaPK;
import com.ecommerce.mobile.entidades.Usuario;
import com.ecommerce.mobile.repositorios.CategoriaRepo;
import com.ecommerce.mobile.repositorios.SubCategoriaRepo;
import com.ecommerce.mobile.repositorios.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServicioImpl implements CategoriaServicio{

    // -- Categorias
    @Autowired
    private CategoriaRepo categoriaRepo;

    @Override
    @Transactional(readOnly = true)
    public Categoria obtenerCategoriaPorId(int id) {
        return categoriaRepo.findCategoriaById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Categoria> obtenerCategorias() {
        return categoriaRepo.findAll();
    }

    @Override
    @Transactional
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepo.save(categoria);
    }

    @Override
    @Transactional
    public void eliminarCategoria(int id) {
        categoriaRepo.deleteCategoriaById(id);
    }

    // -- SubCategorias
    @Autowired
    private SubCategoriaRepo subCategoriaRepo;

    @Override
    @Transactional(readOnly = true)
    public List<SubCategoria> obtenerSubCategoriasDeCat(int idCategoria) {
        return subCategoriaRepo.findSubCategoriasByCategoria_Id(idCategoria);
    }

    @Override
    @Transactional(readOnly = true)
    public SubCategoria obtenerSubCategoria(SubCategoriaPK subcat) {
        return subCategoriaRepo.findSubCategoriaBySubCategoriaPK(subcat);
    }

    @Override
    @Transactional
    public SubCategoria guardarSubCategoria(SubCategoria subCategoria) {
        return subCategoriaRepo.save(subCategoria);
    }

    @Override
    @Transactional
    public void eliminarSubCategoria(SubCategoriaPK subcat) {
        subCategoriaRepo.deleteSubCategoriaBySubCategoriaPK(subcat);
    }

    @Override
    @Transactional(readOnly = true)
    public SubCategoria obtenerUltimaSubCategoria(int idcategoria) {
        return subCategoriaRepo.findTopByCategoria_IdOrderBySubCategoriaPKDesc(idcategoria);
    }

    @Override
    @Transactional
    public void actualizarSubCategoria(String nombre, int idcat, int idscat) {
        subCategoriaRepo.updateNombreSubCategoria(nombre, idcat, idscat);
    }
}
