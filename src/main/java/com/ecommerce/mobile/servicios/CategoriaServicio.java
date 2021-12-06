package com.ecommerce.mobile.servicios;

import com.ecommerce.mobile.entidades.Categoria;
import com.ecommerce.mobile.entidades.SubCategoria;
import com.ecommerce.mobile.entidades.SubCategoriaPK;
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
    public List<SubCategoria> obtenerSubCategoriasDeCat(int id);
    public SubCategoria obtenerSubCategoria(SubCategoriaPK subcat);
    public SubCategoria guardarSubCategoria(SubCategoria subCategoria);
    public void eliminarSubCategoria(SubCategoriaPK subcat);
    public SubCategoria obtenerUltimaSubCategoria(int idcategoria);
    public void actualizarSubCategoria(String nombre, int idcat, int idscat);
}
