package ru.ssau.practice.service.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.ssau.practice.entity.User;
import ru.ssau.practice.repository.user.UserRepository;

import java.util.Optional;

public class RepositoryUserDetailsService implements UserDetailsService
{
    private final UserRepository repository;

    public RepositoryUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        // In our case username is email
        Optional<User> mbUser = repository.findByEmail(username);
        if (!mbUser.isPresent()) {
            throw new UsernameNotFoundException(String.format("User with email \"%s\" not found", username));
        }

        return new UserContainer(mbUser.get());
    }
}
