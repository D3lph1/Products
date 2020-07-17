package ru.ssau.practice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ssau.practice.entity.User;
import ru.ssau.practice.ex.NotFoundException;
import ru.ssau.practice.repository.user.UserRepositoryCustomImpl;
import ru.ssau.practice.repository.user.UserRepository;
import ru.ssau.practice.service.auth.UserContainer;
import ru.ssau.practice.service.brand.BrandsListService;
import ru.ssau.practice.service.brand.DeleteBrandsService;
import ru.ssau.practice.service.db.pagination.PaginationRequest;
import ru.ssau.practice.service.db.pagination.PaginationResult;
import ru.ssau.practice.service.http.ApiError;
import ru.ssau.practice.service.http.ApiResponse;
import ru.ssau.practice.service.user.DeleteUsersService;
import ru.ssau.practice.service.user.SelfDeletionException;
import ru.ssau.practice.service.user.UsersListService;

import javax.validation.Valid;

@RestController
public class UserController
{
    private final UsersListService usersListService;

    private final DeleteUsersService deleteUsersService;

    public UserController(UsersListService usersListService, DeleteUsersService deleteUsersService)
    {
        this.usersListService = usersListService;
        this.deleteUsersService = deleteUsersService;
    }

    @GetMapping("/api/users")
    public ResponseEntity<ApiResponse> list(@Valid PaginationRequest request)
    {
        return new ResponseEntity<>(
                ApiResponse.success().add("users", usersListService.list(request)),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/api/users")
    public ResponseEntity<ApiResponse> delete(@RequestParam("ids") long[] IDs, @AuthenticationPrincipal UserContainer userContainer) throws Exception
    {
        try {
            deleteUsersService.user(userContainer.getUser());
            deleteUsersService.delete(IDs);

            return new ResponseEntity<>(
                    ApiResponse.success().addError(ApiError.success("User(s) successfully deleted")),
                    HttpStatus.OK
            );
        } catch (NotFoundException e) {
            return new ResponseEntity<>(
                    ApiResponse.fail("user_not_found")
                            .addError(ApiError.danger("One or more users not found"))
                            .add("users", e.getReason()),
                    HttpStatus.NOT_FOUND
            );
        } catch (SelfDeletionException e) {
            return new ResponseEntity<>(
                    ApiResponse.fail("self_deletion").addError(ApiError.danger("You cannot delete yourself")),
                    HttpStatus.CONFLICT
            );
        }
    }
}
