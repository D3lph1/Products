package ru.ssau.practice.service.user;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ssau.practice.dto.UserDTO;
import ru.ssau.practice.entity.User;
import ru.ssau.practice.repository.user.JpaUserPaginationQueryBuilder;
import ru.ssau.practice.repository.user.UserRepository;
import ru.ssau.practice.service.AbstractListService;
import ru.ssau.practice.service.db.pagination.PaginationQueryBuilder;
import ru.ssau.practice.service.db.pagination.PaginationRequest;

@Service
public class UsersListService extends AbstractListService<User, UserDTO>
{
    public UsersListService(UserRepository userRepository, ModelMapper modelMapper)
    {
        super(UserDTO.class, userRepository, modelMapper);
    }

    @Override
    protected PaginationQueryBuilder<User> pagination(PaginationRequest request, PaginationQueryBuilder<User> qb)
    {
        return ((JpaUserPaginationQueryBuilder) qb).whereNameLike(request.getFilter());
    }
}
