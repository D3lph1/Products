package ru.ssau.practice.repository.product;

import ru.ssau.practice.entity.Product;
import ru.ssau.practice.service.db.pagination.AbstractPaginationQueryBuilder;
import ru.ssau.practice.service.db.pagination.Paginator;
import ru.ssau.practice.service.util.ArrayUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.HashMap;
import java.util.Map;

public class JpaProductPaginationQueryBuilder extends AbstractPaginationQueryBuilder<Product> implements ProductPaginationQueryBuilder
{
    private final Map<String, Path<?>> orderByToPath;

    private Predicate nameLike;

    private Predicate nameLikeCount;

    private Predicate articleLike;

    private Predicate articleLikeCount;

    private Predicate brandNameLike;

    private Predicate brandNameLikeCount;

    public JpaProductPaginationQueryBuilder(EntityManager em, Paginator<Product> paginator)
    {
        super(Product.class, em, paginator);
        orderByToPath = new HashMap<String, Path<?>>() {{
            put("id", criteriaRoot.get("id"));
            put("name", criteriaRoot.get("name"));
            put("article", criteriaRoot.get("article"));
            put("barcode", criteriaRoot.get("barcode"));
            put("brand", criteriaRoot.join("brand").get("name"));
        }};
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <Y> Path<Y> mapOrderByToPath(String orderBy)
    {
        return (Path<Y>) orderByToPath.get(orderBy);
    }

    @Override
    protected void preExecute()
    {
        criteria.where(cb.or(ArrayUtil.returnNotNull(Predicate.class, nameLike, articleLike, brandNameLike)));
        criteriaCount.where(cb.or(ArrayUtil.returnNotNull(Predicate.class, nameLikeCount, articleLikeCount, brandNameLikeCount)));
    }

    @Override
    public ProductPaginationQueryBuilder whereNameLike(String name)
    {
        nameLike = whereLikePredicate(criteriaRoot.get("name"), name);
        nameLikeCount = whereLikePredicate(criteriaCountRoot.get("name"), name);

        return this;
    }

    @Override
    public ProductPaginationQueryBuilder whereArticleLike(String article)
    {
        articleLike = whereLikePredicate(criteriaRoot.get("article"), article);
        articleLikeCount = whereLikePredicate(criteriaCountRoot.get("article"), article);

        return this;
    }

    @Override
    public ProductPaginationQueryBuilder whereBrandNameLike(String brandName)
    {
        brandNameLike = whereLikePredicate(criteriaRoot.get("brand").get("name"), brandName);
        brandNameLikeCount = whereLikePredicate(criteriaCountRoot.get("brand").get("name"), brandName);

        return this;
    }
}
