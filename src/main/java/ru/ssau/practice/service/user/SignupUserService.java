package ru.ssau.practice.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ssau.practice.dto.NewUserDTO;
import ru.ssau.practice.entity.User;
import ru.ssau.practice.repository.user.UserRepository;

@Service
public class SignupUserService
{
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SignupUserService(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void signup(NewUserDTO dto) throws UserAlreadyExistsException
    {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw UserAlreadyExistsException.withEmail(dto.getEmail());
        }

        User user = new User(dto.getEmail(), passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
    }
}
