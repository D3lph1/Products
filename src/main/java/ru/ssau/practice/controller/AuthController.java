package ru.ssau.practice.controller;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.ssau.practice.dto.NewUserDTO;
import ru.ssau.practice.service.http.ApiError;
import ru.ssau.practice.service.http.ApiFormError;
import ru.ssau.practice.service.http.ApiResponse;
import ru.ssau.practice.service.user.SignupUserService;
import ru.ssau.practice.service.user.UserAlreadyExistsException;
import ru.ssau.practice.service.user.UserInfoService;
import ru.ssau.practice.service.util.MessageUtil;

import javax.validation.Valid;

@RestController
public class AuthController
{
    private final SignupUserService signupUserService;

    private final UserInfoService userInfoService;

    private final MessageSource messageSource;

    public AuthController(SignupUserService signupUserService, UserInfoService userInfoService, MessageSource messageSource)
    {
        this.signupUserService = signupUserService;
        this.userInfoService = userInfoService;
        this.messageSource = messageSource;
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<ApiResponse> signup(@RequestBody @Valid NewUserDTO userDTO)
    {
        try {
            signupUserService.signup(userDTO);

            return new ResponseEntity<>(ApiResponse.success(), HttpStatus.OK);
        } catch (UserAlreadyExistsException e) {
            ApiError error = ApiFormError.danger("email", MessageUtil.retrieveFromSource("auth.signup.user_already_exists", messageSource));

            return new ResponseEntity<>(ApiResponse.fail("user_already_exists").addError(error), HttpStatus.CONFLICT);
        }
    }

    @GetMapping(path = "/api/user")
    public ApiResponse user(Authentication auth)
    {
        return ApiResponse.success().add("user", userInfoService.get(auth));
    }
}
