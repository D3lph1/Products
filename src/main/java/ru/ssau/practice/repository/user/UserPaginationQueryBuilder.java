package ru.ssau.practice.repository.user;

import ru.ssau.practice.entity.User;
import ru.ssau.practice.service.db.pagination.PaginationQueryBuilder;

public interface UserPaginationQueryBuilder extends PaginationQueryBuilder<User>
{
    UserPaginationQueryBuilder whereNameLike(String name);
}
