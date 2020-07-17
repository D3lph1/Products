package ru.ssau.practice.service.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;
import ru.ssau.practice.service.http.ApiError;
import ru.ssau.practice.service.http.ApiResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class SuccessLogoutHandler implements LogoutSuccessHandler
{
    private final ObjectMapper jackson;

    public SuccessLogoutHandler(ObjectMapper jackson) {
        this.jackson = jackson;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException
    {
        response.setContentType("application/json");
        jackson.writeValue(response.getWriter(), ApiResponse.success().addError(ApiError.info("Bye. Come back soon...")));
    }
}
