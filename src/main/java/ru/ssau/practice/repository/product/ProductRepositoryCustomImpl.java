package ru.ssau.practice.repository.product;

import org.springframework.stereotype.Repository;
import ru.ssau.practice.entity.Product;
import ru.ssau.practice.repository.AbstractCustomRepository;
import ru.ssau.practice.service.db.pagination.PaginationQueryBuilder;
import ru.ssau.practice.service.db.pagination.Paginator;

import javax.persistence.EntityManager;

@Repository
public class ProductRepositoryCustomImpl extends AbstractCustomRepository<Product> implements ProductRepositoryCustom
{
    public ProductRepositoryCustomImpl(EntityManager em, Paginator<Product> paginator)
    {
        super(em, paginator);
    }

    @Override
    public ProductPaginationQueryBuilder createPaginationQueryBuilder()
    {
        return new JpaProductPaginationQueryBuilder(em, paginator);
    }
}
