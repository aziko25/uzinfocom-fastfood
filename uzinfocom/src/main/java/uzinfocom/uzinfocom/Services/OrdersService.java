package uzinfocom.uzinfocom.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uzinfocom.uzinfocom.DTOs.OrdersDTO;
import uzinfocom.uzinfocom.DTOs.Requests.Orders.OrderRequest;
import uzinfocom.uzinfocom.DTOs.Requests.Orders.UpdateOrderRequest;
import uzinfocom.uzinfocom.Models.Dishes;
import uzinfocom.uzinfocom.Models.Enums.OrdersStatuses;
import uzinfocom.uzinfocom.Models.Orders;
import uzinfocom.uzinfocom.Models.Orders_Dishes;
import uzinfocom.uzinfocom.Models.Users;
import uzinfocom.uzinfocom.Repositories.DishesRepository;
import uzinfocom.uzinfocom.Repositories.OrdersRepository;
import uzinfocom.uzinfocom.Repositories.Orders_Dishes_Repository;
import uzinfocom.uzinfocom.Repositories.UsersRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static uzinfocom.uzinfocom.JWT.AuthorizationMethods.USER_ID;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final UsersRepository usersRepository;

    private final OrdersRepository ordersRepository;
    private final Orders_Dishes_Repository ordersDishesRepository;

    private final DishesRepository dishesRepository;

    public Orders placeOrder(OrderRequest request) {

        Users user = usersRepository.findById(USER_ID).orElseThrow(() -> new IllegalArgumentException("User Not Found!"));

        // Creating order, so we can add ordered dishes under it
        Orders order = Orders.builder()
                .createdAt(LocalDateTime.now())
                .client(user)
                .clientAddress(request.getAddress())
                .status(OrdersStatuses.ORDER_CREATED)
                .distance(request.getDistance())
                .build();

        ordersRepository.save(order);

        List<Orders_Dishes> ordersDishesList = new ArrayList<>();

        // will calculate orders total price
        AtomicReference<Double> totalPrice = new AtomicReference<>((double) 0);

        request.getDishes().forEach(dish -> {

            Dishes dishFound = dishesRepository.findById(dish.getDishId())
                    .orElseThrow(() -> new IllegalArgumentException("Dish Not Found!"));

            Orders_Dishes ordersDishes = Orders_Dishes.builder()
                    .dishId(dishFound)
                    .orderId(order)
                    .quantity(dish.getQuantity())
                    .build();

            totalPrice.updateAndGet(v -> (v + (dishFound.getPrice() * dish.getQuantity())));

            ordersDishesList.add(ordersDishes);
        });

        ordersDishesRepository.saveAll(ordersDishesList);

        order.setOrderedDishes(ordersDishesList);
        order.setTotalPrice(totalPrice.get());
        order.setOrderDeliveryExpectedTime(approximateOrderReadyTime(order));

        return ordersRepository.save(order);
    }

    // Logic: Fetching all not cooked dishes yet and calculating new orders approximate order ready time
    // So I fetch all the orders that are not yet cooked, multiplying quantity of dishes from that all the orders to 1.25
    // and just adding 3 * per km
    public Double approximateOrderReadyTime(Orders currentOrder) {

        List<OrdersStatuses> ordersStatusesList = new ArrayList<>();

        ordersStatusesList.add(OrdersStatuses.ORDER_CREATED);
        ordersStatusesList.add(OrdersStatuses.ORDER_IN_PROGRESS);

        List<Orders> allOrders = ordersRepository.findAllByStatusIn(ordersStatusesList, Sort.by("id").ascending());

        // Calculate the total dish count (including the current order)
        int totalDishCount = allOrders.stream()
                .mapToInt(order -> order.getOrderedDishes().stream()
                        .mapToInt(Orders_Dishes::getQuantity)
                        .sum()).sum();

        // Kitchen's cooking rate (dishes per minute) (5 dishes / 4 minutes)
        double cookingRate = 1.25;

        // I just added the order delivery time as it was in tech task (3 minutes for each kilometer)
        return (totalDishCount / cookingRate) + 3 * currentOrder.getDistance();
    }

    public List<OrdersDTO> getAllOrders() {

        return ordersRepository.findAll(Sort.by("id").ascending()).stream().map(OrdersDTO::new).toList();
    }

    public List<OrdersDTO> getAllMyOrders() {

        Users user = usersRepository.findById(USER_ID).orElseThrow(() -> new IllegalArgumentException("User Not Found!"));

        return ordersRepository.findAllByClient(user, Sort.by("id").ascending()).stream().map(OrdersDTO::new).toList();
    }

    public List<OrdersDTO> getAllOrdersByStatuses(List<OrdersStatuses> statuses) {

        return ordersRepository.findAllByStatusIn(statuses, Sort.by("id").ascending()).stream().map(OrdersDTO::new).toList();
    }

    public OrdersDTO updateOrder(Long orderId, UpdateOrderRequest request) {

        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order Not Found!"));

        if (request.getAddress() != null) {

            order.setClientAddress(request.getAddress());
        }

        if (request.getStatusCode() != null) {

            order.setStatus(OrdersStatuses.getStatusFromCode(request.getStatusCode()));
        }

        ordersRepository.save(order);

        return new OrdersDTO(order);
    }

    public String deleteOrder(Long orderId) {

        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order Not Found!"));

        ordersRepository.delete(order);

        return "Order Deleted Successfully!";
    }
}