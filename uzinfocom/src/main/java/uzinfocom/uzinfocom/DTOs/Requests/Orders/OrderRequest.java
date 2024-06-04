package uzinfocom.uzinfocom.DTOs.Requests.Orders;

import lombok.Getter;
import lombok.Setter;
import uzinfocom.uzinfocom.DTOs.BasketDTO;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private List<BasketDTO> dishes;
    private String address;
    private Double distance;
}