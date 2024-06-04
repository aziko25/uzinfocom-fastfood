package uzinfocom.uzinfocom.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uzinfocom.uzinfocom.DTOs.Requests.Authentication.LoginRequest;
import uzinfocom.uzinfocom.DTOs.Requests.Authentication.SignupRequest;
import uzinfocom.uzinfocom.Services.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(maxAge = 3600)
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest request) {

        return ResponseEntity.ok(authenticationService.signUp(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        return ResponseEntity.ok(authenticationService.login(request));
    }
}