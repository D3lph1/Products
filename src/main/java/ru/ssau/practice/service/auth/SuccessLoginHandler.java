package ru.ssau.practice.service.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import ru.ssau.practice.dto.UserDTO;
import ru.ssau.practice.service.http.ApiResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class SuccessLoginHandler extends SimpleUrlAuthenticationSuccessHandler
{
    private final ObjectMapper jackson;

    private final ModelMapper modelMapper;

    public SuccessLoginHandler(ObjectMapper jackson, ModelMapper modelMapper) {
        this.jackson = jackson;
        this.modelMapper = modelMapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException
    {
        clearAuthenticationAttributes(request);
        response.setContentType("application/json");
        jackson.writeValue(
                response.getWriter(),
                ApiResponse.success()
                        .add("user", modelMapper.map(AuthUtil.retrieveUser(authentication), UserDTO.class))
        );
    }
}
