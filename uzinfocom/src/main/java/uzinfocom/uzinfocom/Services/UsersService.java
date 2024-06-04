package uzinfocom.uzinfocom.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uzinfocom.uzinfocom.DTOs.Filters.UsersFilter;
import uzinfocom.uzinfocom.DTOs.Requests.Users.CreateUserRequest;
import uzinfocom.uzinfocom.Models.Users;
import uzinfocom.uzinfocom.Repositories.UsersRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    public Users createUser(CreateUserRequest request) {

        if (!request.getRole().equalsIgnoreCase("WAITER") &&
                !request.getRole().equalsIgnoreCase("ADMIN") &&
                !request.getRole().equalsIgnoreCase("USER")) {

            throw new IllegalArgumentException("Role Must Be WAITER Or ADMIN Or USER!");
        }

        try {
            Users user = Users.builder()
                    .name(request.getName())
                    .phone(request.getPhone())
                    .password(request.getPassword())
                    .role(request.getRole().toUpperCase())
                    .build();

            usersRepository.save(user);

            return user;
        }
        catch (DataIntegrityViolationException e) {

            throw new DataIntegrityViolationException("Phone Already Exists!");
        }
    }

    public List<Users> getAllUsers(UsersFilter filter) {

        if (filter != null && filter.getRole() != null) {

            return usersRepository.findAllByRole(filter.getRole().toUpperCase(), Sort.by("id").ascending());
        }

        return usersRepository.findAll(Sort.by("id").ascending());
    }
}