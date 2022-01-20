package cl.falabella.boost.codingevaluation.data.repository;

import cl.falabella.boost.codingevaluation.data.entity.ImageProduct;
import cl.falabella.boost.codingevaluation.data.entity.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ImageProductRepository extends CrudRepository<ImageProduct, Integer> {

    @Query("SELECT ip FROM ImageProduct ip WHERE ip.sku = :sku")
    List<ImageProduct> findImageProductBySku(@Param("sku") String sku);

    @Transactional
    @Modifying
    @Query("DELETE FROM ImageProduct ip WHERE ip.sku = :sku")
    void deleteImageProductBySku(@Param("sku") String sku);

}
