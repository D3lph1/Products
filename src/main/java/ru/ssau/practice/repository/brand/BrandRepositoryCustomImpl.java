package ru.ssau.practice.repository.brand;

import org.springframework.stereotype.Repository;
import ru.ssau.practice.entity.Brand;
import ru.ssau.practice.repository.AbstractCustomRepository;
import ru.ssau.practice.service.db.pagination.Paginator;

import javax.persistence.EntityManager;

@Repository
public class BrandRepositoryCustomImpl  extends AbstractCustomRepository<Brand> implements BrandRepositoryCustom
{
    public BrandRepositoryCustomImpl(EntityManager em, Paginator<Brand> paginator)
    {
        super(em, paginator);
    }

    @Override
    public BrandPaginationQueryBuilder createPaginationQueryBuilder()
    {
        return new JpaBrandPaginationQueryBuilder(Brand.class, em, paginator);
    }
}
