package ru.ssau.practice.repository.user;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends UserRepositoryBasic, UserRepositoryCustom
{
}
