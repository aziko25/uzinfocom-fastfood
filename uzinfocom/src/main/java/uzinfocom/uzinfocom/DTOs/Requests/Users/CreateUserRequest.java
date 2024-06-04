package uzinfocom.uzinfocom.DTOs.Requests.Users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {

    private String name;
    private String phone;
    private String password;
    private String role;
}