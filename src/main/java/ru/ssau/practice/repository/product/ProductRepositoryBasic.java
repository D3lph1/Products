package ru.ssau.practice.repository.product;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.ssau.practice.entity.Brand;
import ru.ssau.practice.entity.Product;
import ru.ssau.practice.repository.DeleteByIDsRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

public interface ProductRepositoryBasic extends CrudRepository<Product, Long>, DeleteByIDsRepository
{
    boolean existsByArticleAndBrand(String article, Brand brand);

    boolean existsByBarcode(String barcode);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Product p WHERE p.article = :article AND p.brand = :brand AND p <> :exception")
    boolean existsByArticleAndBrandExcept(String article, Brand brand, Product exception);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Product p WHERE p.barcode = :barcode AND p <> :exception")
    boolean existsByBarcodeExcept(String barcode, Product exception);

    @Query("SELECT p.id FROM Product p WHERE p.id IN (:IDs)")
    long[] selectIDs(long[] IDs);

    @Modifying
    @Transactional
    @Query("DELETE FROM Product p WHERE p.id IN (:IDs)")
    void deleteByIdIn(long[] IDs);
}
