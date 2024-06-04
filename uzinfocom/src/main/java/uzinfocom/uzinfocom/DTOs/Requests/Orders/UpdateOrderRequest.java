package uzinfocom.uzinfocom.DTOs.Requests.Orders;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderRequest {

    private String address;
    private Integer statusCode;
}