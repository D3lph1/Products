package ru.ssau.practice.service.user;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.ssau.practice.dto.UserDTO;
import ru.ssau.practice.service.auth.AuthUtil;

@Service
public class UserInfoService
{
    private final ModelMapper modelMapper;

    public UserInfoService(ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
    }

    public UserDTO get(Authentication auth)
    {
        return modelMapper.map(AuthUtil.retrieveUser(auth), UserDTO.class);
    }
}
