package ru.ssau.practice.repository.user;

import org.springframework.stereotype.Repository;
import ru.ssau.practice.entity.User;
import ru.ssau.practice.repository.AbstractCustomRepository;
import ru.ssau.practice.service.db.pagination.PaginationQueryBuilder;
import ru.ssau.practice.service.db.pagination.Paginator;

import javax.persistence.EntityManager;

@Repository
public class UserRepositoryCustomImpl extends AbstractCustomRepository<User> implements UserRepositoryCustom
{
    public UserRepositoryCustomImpl(EntityManager em, Paginator<User> paginator)
    {
        super(em, paginator);
    }

    @Override
    public PaginationQueryBuilder<User> createPaginationQueryBuilder()
    {
        return new JpaUserPaginationQueryBuilder(em, paginator);
    }
}
