package cl.falabella.boost.codingevaluation.data.repository;

import cl.falabella.boost.codingevaluation.data.entity.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Query("SELECT count(p) FROM Product p WHERE p.sku = :sku")
    int countProductBySku(@Param("sku") String sku);

    @Query("SELECT p FROM Product p WHERE p.sku = :sku")
    Optional<Product> findProductBySku(@Param("sku") String sku);

    @Transactional
    @Modifying
    @Query("DELETE FROM Product p WHERE p.sku = :sku")
    void deleteImageProductBySku(@Param("sku") String sku);
}
