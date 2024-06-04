package uzinfocom.uzinfocom.DTOs.Requests.Dishes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDishRequest {

    private String name;
    private Double price;
    private Long categoryId;
}