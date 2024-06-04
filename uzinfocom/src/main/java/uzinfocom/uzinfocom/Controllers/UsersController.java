package uzinfocom.uzinfocom.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uzinfocom.uzinfocom.DTOs.Filters.UsersFilter;
import uzinfocom.uzinfocom.DTOs.Requests.Users.CreateUserRequest;
import uzinfocom.uzinfocom.JWT.Authorization;
import uzinfocom.uzinfocom.Services.UsersService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(maxAge = 3600)
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @Authorization(requiredRoles = {"ADMIN"})
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest user) {

        return ResponseEntity.ok(usersService.createUser(user));
    }

    @Authorization(requiredRoles = {"ADMIN"})
    @PostMapping("/all")
    public ResponseEntity<?> getAllUsers(@RequestBody(required = false) UsersFilter filter) {

        return ResponseEntity.ok(usersService.getAllUsers(filter));
    }
}