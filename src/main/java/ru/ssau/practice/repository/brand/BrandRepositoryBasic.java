package ru.ssau.practice.repository.brand;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.ssau.practice.entity.Brand;
import ru.ssau.practice.repository.DeleteByIDsRepository;

import javax.transaction.Transactional;

public interface BrandRepositoryBasic extends CrudRepository<Brand, Long>, DeleteByIDsRepository
{
    boolean existsByName(String name);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Brand b WHERE b.name = :name AND b <> :exception")
    boolean existsByNameExcept(String name, Brand exception);

    @Query("SELECT b.id FROM Brand b WHERE b.id IN (:IDs)")
    long[] selectIDs(long[] IDs);

    @Modifying
    @Transactional
    @Query("DELETE FROM Brand b WHERE b.id IN (:IDs)")
    void deleteByIdIn(long[] IDs);
}
