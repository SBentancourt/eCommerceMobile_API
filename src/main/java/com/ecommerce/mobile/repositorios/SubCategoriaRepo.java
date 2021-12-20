package com.ecommerce.mobile.repositorios;

import com.ecommerce.mobile.entidades.SubCategoria;
import com.ecommerce.mobile.entidades.SubCategoriaPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SubCategoriaRepo extends JpaRepository<SubCategoria, SubCategoriaPK> {
    SubCategoria findSubCategoriaBySubCategoriaPK(SubCategoriaPK subcat);

    List<SubCategoria> findSubCategoriasByCategoria_Id(int idCategoria);
    SubCategoria findTopByCategoria_IdOrderBySubCategoriaPKDesc(int idCategoria);

    int deleteSubCategoriaBySubCategoriaPK(SubCategoriaPK subcat);

    @Transactional
    @Modifying
    @Query("update SubCategoria sc set sc.nombre = ?1 where sc.subCategoriaPK.idCategoria = ?2 and sc.subCategoriaPK.idSubCategoria = ?3")
    void updateNombreSubCategoria(String nombre, int idcat, int idscat);


}
