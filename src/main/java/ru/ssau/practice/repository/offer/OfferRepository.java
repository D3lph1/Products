package ru.ssau.practice.repository.offer;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ssau.practice.entity.Offer;
import ru.ssau.practice.entity.Product;
import ru.ssau.practice.repository.DeleteByIDsRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Long>, DeleteByIDsRepository
{
    Collection<Offer> getByProduct(Product product);

    @Query("SELECT o.id FROM Offer o WHERE o.id IN (:IDs)")
    long[] selectIDs(long[] IDs);

    @Modifying
    @Transactional
    @Query("DELETE FROM Offer o WHERE o.id IN (:IDs)")
    void deleteByIdIn(long[] IDs);
}
