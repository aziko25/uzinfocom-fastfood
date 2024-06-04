package uzinfocom.uzinfocom.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uzinfocom.uzinfocom.JWT.Authorization;
import uzinfocom.uzinfocom.Services.Dishes_Categories_Service;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dish-categories")
@CrossOrigin(maxAge = 3600)
public class Dishes_Categories_Controller {

    private final Dishes_Categories_Service service;

    @Authorization(requiredRoles = {"ADMIN", "WAITER"})
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestParam String name) {

        return ResponseEntity.ok(service.create(name));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {

        return ResponseEntity.ok(service.findAll());
    }

    @Authorization(requiredRoles = {"ADMIN", "WAITER"})
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestParam String name) {

        return ResponseEntity.ok(service.update(id, name));
    }

    @Authorization(requiredRoles = {"ADMIN", "WAITER"})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        return ResponseEntity.ok(service.delete(id));
    }
}