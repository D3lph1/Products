package ru.ssau.practice.repository.user;

import ru.ssau.practice.entity.User;
import ru.ssau.practice.service.db.pagination.PaginationQueryBuilderAware;

public interface UserRepositoryCustom extends PaginationQueryBuilderAware<User>
{
}
