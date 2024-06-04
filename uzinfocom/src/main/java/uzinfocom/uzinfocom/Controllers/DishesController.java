package uzinfocom.uzinfocom.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uzinfocom.uzinfocom.DTOs.Filters.DishesFilter;
import uzinfocom.uzinfocom.DTOs.Requests.Dishes.CreateDishRequest;
import uzinfocom.uzinfocom.DTOs.Requests.Dishes.UpdateDishRequest;
import uzinfocom.uzinfocom.JWT.Authorization;
import uzinfocom.uzinfocom.Services.DishesService;

@RestController
@RequestMapping("/api/dishes")
@CrossOrigin(maxAge = 3600)
@RequiredArgsConstructor
public class DishesController {

    private final DishesService dishesService;

    @Authorization(requiredRoles = {"ADMIN", "WAITER"})
    @PostMapping("/create")
    public ResponseEntity<?> createDish(@RequestBody CreateDishRequest request) {

        return ResponseEntity.ok(dishesService.createDish(request));
    }

    @PostMapping("/all")
    public ResponseEntity<?> allDishes(@RequestBody(required = false) DishesFilter filter) {

        return ResponseEntity.ok(dishesService.getAllDishes(filter));
    }

    @Authorization(requiredRoles = {"ADMIN", "WAITER"})
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDish(@PathVariable Long id, @RequestBody UpdateDishRequest request) {

        return ResponseEntity.ok(dishesService.updateDish(id, request));
    }

    @Authorization(requiredRoles = {"ADMIN", "WAITER"})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDish(@PathVariable Long id) {

        return ResponseEntity.ok(dishesService.deleteDish(id));
    }
}