package ru.ssau.practice.service.user;

import org.springframework.stereotype.Service;
import ru.ssau.practice.entity.User;
import ru.ssau.practice.repository.user.UserRepository;
import ru.ssau.practice.service.AbstractDeleteService;

@Service
public class DeleteUsersService extends AbstractDeleteService
{
    private User user;

    public DeleteUsersService(UserRepository repository)
    {
        super(repository);
    }

    public void user(User user)
    {
        this.user = user;
    }

    @Override
    protected void preDelete(long[] IDs) throws Exception
    {
        if (user == null) {
            return;
        }

        for (long ID : IDs) {
            if (ID == user.getId()) {
                throw new SelfDeletionException();
            }
        }
    }
}
