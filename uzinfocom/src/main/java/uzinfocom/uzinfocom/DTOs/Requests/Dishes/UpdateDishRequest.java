package uzinfocom.uzinfocom.DTOs.Requests.Dishes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDishRequest {

    private String name;
    private Double price;
    private Long categoryId;
}