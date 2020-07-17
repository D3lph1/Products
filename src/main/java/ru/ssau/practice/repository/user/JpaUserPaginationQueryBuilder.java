package ru.ssau.practice.repository.user;

import ru.ssau.practice.entity.User;
import ru.ssau.practice.service.db.pagination.AbstractPaginationQueryBuilder;
import ru.ssau.practice.service.db.pagination.Paginator;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.HashMap;
import java.util.Map;

public class JpaUserPaginationQueryBuilder extends AbstractPaginationQueryBuilder<User> implements UserPaginationQueryBuilder
{
    private final Map<String, Path<?>> orderByToPath;

    private Predicate emailLike;

    private Predicate emailLikeCount;

    public JpaUserPaginationQueryBuilder(EntityManager em, Paginator<User> paginator)
    {
        super(User.class, em, paginator);
        orderByToPath = new HashMap<String, Path<?>>()
        {{
            put("id", criteriaRoot.get("id"));
            put("email", criteriaRoot.get("email"));
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
        criteria.where(emailLike);
        criteriaCount.where(emailLikeCount);
    }

    @Override
    public JpaUserPaginationQueryBuilder whereNameLike(String name)
    {
        emailLike = whereLikePredicate(criteriaRoot.get("email"), name);
        emailLikeCount = whereLikePredicate(criteriaCountRoot.get("email"), name);

        return this;
    }
}
