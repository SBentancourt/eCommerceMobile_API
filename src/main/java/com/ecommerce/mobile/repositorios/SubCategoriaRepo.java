package com.ecommerce.mobile.repositorios;

import com.ecommerce.mobile.entidades.SubCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoriaRepo extends JpaRepository<SubCategoria, String> {
}
