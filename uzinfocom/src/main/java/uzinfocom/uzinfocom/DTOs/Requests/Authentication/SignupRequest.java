package uzinfocom.uzinfocom.DTOs.Requests.Authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    private String name;
    private String phone;
    private String password;
    private String address;
}