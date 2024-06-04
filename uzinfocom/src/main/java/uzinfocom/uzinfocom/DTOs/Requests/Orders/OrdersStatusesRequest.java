package uzinfocom.uzinfocom.DTOs.Requests.Orders;

import lombok.Getter;
import lombok.Setter;
import uzinfocom.uzinfocom.Models.Enums.OrdersStatuses;

import java.util.List;

@Getter
@Setter
public class OrdersStatusesRequest {

    private List<OrdersStatuses> ordersStatuses;
}