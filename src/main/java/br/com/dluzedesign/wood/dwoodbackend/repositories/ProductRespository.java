package br.com.dluzedesign.wood.dwoodbackend.repositories;

import br.com.dluzedesign.wood.dwoodbackend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRespository extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p JOIN p.categories c WHERE LOWER(c.name) = LOWER(:nome)")
    List<Product> findByCategoria(@Param("nome") String nome);
    @Query(value = "SELECT c.name FROM wood_category c " +
            "JOIN tb_product_category pc ON c.id = pc.category_id " +
            "WHERE pc.product_id = :productId LIMIT 1", nativeQuery = true)
    String findFirstCategoryNameByProductId(@Param("productId") Long productId);
}
