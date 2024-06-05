package uzinfocom.uzinfocom.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import uzinfocom.uzinfocom.DTOs.Requests.Authentication.LoginRequest;
import uzinfocom.uzinfocom.DTOs.Requests.Authentication.SignupRequest;
import uzinfocom.uzinfocom.Models.Users;
import uzinfocom.uzinfocom.Repositories.UsersRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.Objects;

import static uzinfocom.uzinfocom.JWT.AuthorizationMethods.getSecretKey;
import static uzinfocom.uzinfocom.Utils.UtilsService.isValidPhone;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsersRepository usersRepository;

    public Users signUp(SignupRequest request) {

        try {

            if (!isValidPhone(request.getPhone())) {

                throw new IllegalArgumentException("Invalid phone number!");
            }

            Users user = Users.builder()
                    .name(request.getName())
                    .phone(request.getPhone())
                    .password(request.getPassword())
                    .address(request.getAddress())
                    .role("USER")
                    .build();

            return usersRepository.save(user);
        }
        catch (DataIntegrityViolationException e) {

            throw new DataIntegrityViolationException(e.getMessage());
        }
    }

    public String login(LoginRequest request) {

        Users user = usersRepository.findByPhone(request.getPhone())
                .orElseThrow(() -> new IllegalArgumentException("Phone Not Found"));

        if (Objects.equals(user.getPassword(), request.getPassword())) {

            Claims claims = Jwts.claims();

            claims.put("id", user.getId());
            claims.put("role", user.getRole());

            // Expires in a week
            Date expiration = new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000);

            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(expiration)
                    .signWith(getSecretKey())
                    .compact();
        }
        else {

            throw new IllegalArgumentException("Password Didn't Match!");
        }
    }
}
