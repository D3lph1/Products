package ru.ssau.practice.service.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;
import ru.ssau.practice.service.http.ApiError;
import ru.ssau.practice.service.http.ApiResponse;
import ru.ssau.practice.service.util.MessageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class FailureLoginHandler implements AuthenticationFailureHandler
{
    private final ObjectMapper jackson;

    private final MessageSource messageSource;

    public FailureLoginHandler(ObjectMapper jackson, MessageSource messageSource)
    {
        this.jackson = jackson;
        this.messageSource = messageSource;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException
    {
        response.setContentType("application/json");
        String message = MessageUtil.retrieveFromSource("auth.login.invalid_credentials", messageSource);
        jackson.writeValue(
                response.getWriter(),
                ApiResponse.fail("invalid_credentials").addError(ApiError.danger(message))
        );
        response.setStatus(HttpStatus.NOT_FOUND.value());
    }
}
