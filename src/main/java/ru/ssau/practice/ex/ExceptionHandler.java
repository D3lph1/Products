package ru.ssau.practice.ex;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.ssau.practice.service.http.ApiFormError;
import ru.ssau.practice.service.http.ApiResponse;
import ru.ssau.practice.service.localization.LocalizationKeyResolver;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler
{
    private final LocalizationKeyResolver localizationKeyResolver;

    public ExceptionHandler(LocalizationKeyResolver localizationKeyResolver)
    {
        this.localizationKeyResolver = localizationKeyResolver;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        return handleExceptionInternal(ex, createResponse(ex.getBindingResult()), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        MultiValueMap<String, String> newHeaders = new HttpHeaders();
        newHeaders.add("Content-Type", "application/json; charset=utf-8");

        return new ResponseEntity<>(createResponse(ex.getBindingResult()), newHeaders, HttpStatus.BAD_REQUEST);
    }

    private ApiResponse createResponse(BindingResult bindingResult)
    {
        ApiResponse response = ApiResponse.fail("validation_error");

        for (final FieldError error : bindingResult.getFieldErrors()) {
            response.addError(ApiFormError.danger(error.getField(), localizationKeyResolver.resolve(error)));
        }

        return response;
    }
}
