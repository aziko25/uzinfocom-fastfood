package uzinfocom.uzinfocom.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uzinfocom.uzinfocom.Models.Orders;
import uzinfocom.uzinfocom.Models.Orders_Dishes;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO {

    private Long id;
    private LocalDateTime createdAt;
    private Long clientId;
    private String clientName;
    private String clientAddress;
    private String clientPhone;
    private Double totalPrice;
    private String status;
    private Double orderDeliveryExpectedTime;
    private Double distance;
    private List<Map<String, Object>> orderedDishes;

    public OrdersDTO(Orders orders) {

        this.id = orders.getId();
        this.createdAt = orders.getCreatedAt();
        this.clientId = orders.getClient().getId();
        this.clientName = orders.getClient().getName();
        this.clientAddress = orders.getClientAddress();
        this.clientPhone = orders.getClient().getPhone();
        this.totalPrice = orders.getTotalPrice();
        this.status = orders.getStatus().name();
        this.orderDeliveryExpectedTime = orders.getOrderDeliveryExpectedTime();
        this.distance = orders.getDistance();

        this.orderedDishes = new ArrayList<>();

        for (Orders_Dishes dish : orders.getOrderedDishes()) {

            Map<String, Object> map = new LinkedHashMap<>();

            map.put("orderId", dish.getOrderId().getId());
            map.put("dishId", dish.getDishId().getId());
            map.put("dishName", dish.getDishId().getName());
            map.put("dishPrice", dish.getDishId().getPrice());

            if (dish.getDishId().getCategory() != null) {

                map.put("dishCategory", dish.getDishId().getCategory().getName());
            }

            map.put("quantity", dish.getQuantity());

            this.orderedDishes.add(map);
        }
    }
}