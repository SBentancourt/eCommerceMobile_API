package com.ecommerce.mobile.repositorios;

import com.ecommerce.mobile.entidades.Categoria;
import com.ecommerce.mobile.entidades.SubCategoria;
import com.ecommerce.mobile.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepo extends JpaRepository<Categoria, Integer>, JpaSpecificationExecutor<Categoria> {
    Categoria findCategoriaById(int id);

    int deleteCategoriaById(int id);
}

