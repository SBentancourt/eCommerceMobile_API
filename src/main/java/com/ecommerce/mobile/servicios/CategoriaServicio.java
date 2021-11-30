package com.ecommerce.mobile.servicios;

import com.ecommerce.mobile.entidades.Categoria;
import com.ecommerce.mobile.entidades.SubCategoria;
import com.ecommerce.mobile.entidades.Usuario;

import java.util.List;
import java.util.Optional;

public interface CategoriaServicio {
    // -- Categorias
    public Categoria obtenerCategoriaPorId(int id);
    public Iterable<Categoria> obtenerCategorias();
    public Categoria guardarCategoria(Categoria categoria);
    public void eliminarCategoria(int id);

    // -- SubCategorias
    public Optional<SubCategoria> obtenerSubCategoriasDeCat(int id);
    public Iterable<SubCategoria> obtenerSubCategoria(int idcat, int idscat);
    public SubCategoria guardarSubCategoria(SubCategoria subCategoria);
    public void eliminarSubCategoria(int idcat, int idscat);
}
