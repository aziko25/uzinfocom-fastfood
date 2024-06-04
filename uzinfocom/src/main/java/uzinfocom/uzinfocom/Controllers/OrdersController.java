package uzinfocom.uzinfocom.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uzinfocom.uzinfocom.DTOs.Requests.Orders.OrderRequest;
import uzinfocom.uzinfocom.DTOs.Requests.Orders.OrdersStatusesRequest;
import uzinfocom.uzinfocom.DTOs.Requests.Orders.UpdateOrderRequest;
import uzinfocom.uzinfocom.JWT.Authorization;
import uzinfocom.uzinfocom.Services.OrdersService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
@CrossOrigin(maxAge = 3600)
public class OrdersController {

    private final OrdersService ordersService;

    @Authorization(requiredRoles = {"ADMIN", "USER"})
    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest request) {

        return ResponseEntity.ok(ordersService.placeOrder(request));
    }

    @Authorization(requiredRoles = {"ADMIN", "WAITER"})
    @GetMapping("/all")
    public ResponseEntity<?> getAllOrders() {

        return ResponseEntity.ok(ordersService.getAllOrders());
    }

    @Authorization(requiredRoles = {"ADMIN", "WAITER"})
    @PostMapping("/allByStatuses")
    public ResponseEntity<?> getAllOrdersByStatus(@RequestBody OrdersStatusesRequest ordersStatuses) {

        return ResponseEntity.ok(ordersService.getAllOrdersByStatuses(ordersStatuses.getOrdersStatuses()));
    }

    @Authorization(requiredRoles = {"ADMIN", "USER"})
    @GetMapping("/allMy")
    public ResponseEntity<?> getAllMyOrders() {

        return ResponseEntity.ok(ordersService.getAllMyOrders());
    }

    @Authorization(requiredRoles = {"ADMIN", "WAITER"})
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody UpdateOrderRequest request) {

        return ResponseEntity.ok(ordersService.updateOrder(id, request));
    }

    @Authorization(requiredRoles = {"ADMIN", "WAITER"})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {

        return ResponseEntity.ok(ordersService.deleteOrder(id));
    }
}