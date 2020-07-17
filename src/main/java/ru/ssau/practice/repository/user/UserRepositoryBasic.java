package ru.ssau.practice.repository.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.ssau.practice.entity.User;
import ru.ssau.practice.repository.DeleteByIDsRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepositoryBasic extends CrudRepository<User, Long>, DeleteByIDsRepository
{
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT u.id FROM User u WHERE u.id IN (:IDs)")
    long[] selectIDs(long[] IDs);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id IN (:IDs)")
    void deleteByIdIn(long[] IDs);
}
