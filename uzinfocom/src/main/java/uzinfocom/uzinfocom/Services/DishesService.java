package uzinfocom.uzinfocom.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uzinfocom.uzinfocom.DTOs.Filters.DishesFilter;
import uzinfocom.uzinfocom.DTOs.Requests.Dishes.CreateDishRequest;
import uzinfocom.uzinfocom.DTOs.Requests.Dishes.UpdateDishRequest;
import uzinfocom.uzinfocom.Models.Dishes;
import uzinfocom.uzinfocom.Models.Dishes_Categories;
import uzinfocom.uzinfocom.Repositories.DishesRepository;
import uzinfocom.uzinfocom.Repositories.Dishes_Categories_Repository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishesService {

    private final DishesRepository dishesRepository;
    private final Dishes_Categories_Repository dishesCategoriesRepository;

    public Dishes createDish(CreateDishRequest request) {

        try {

            Dishes dish = Dishes.builder()
                    .name(request.getName())
                    .price(request.getPrice())
                    .build();

            if (request.getCategoryId() != null) {

                Dishes_Categories dishesCategory = dishesCategoriesRepository.findById(request.getCategoryId())
                        .orElseThrow(() -> new IllegalArgumentException("Dish Category Not Found!"));

                dish.setCategory(dishesCategory);
            }

            dishesRepository.save(dish);

            return dish;
        }
        catch (DataIntegrityViolationException e) {

            throw new DataIntegrityViolationException("Dish Already Exists!");
        }
    }

    public List<Dishes> getAllDishes(DishesFilter filter) {

        if (filter != null && filter.getCategoryId() != null) {

            Dishes_Categories dishesCategory = dishesCategoriesRepository.findById(filter.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Dish Category Not Found!"));

            return dishesRepository.findAllByCategory(dishesCategory);
        }

        return dishesRepository.findAll(Sort.by("id"));
    }

    public Dishes updateDish(Long id, UpdateDishRequest request) {

        Dishes dish = dishesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Dish Not Found!"));

        if (request.getName() != null) {

            dish.setName(request.getName());
        }

        if (request.getPrice() != null) {

            dish.setPrice(request.getPrice());
        }

        if (request.getCategoryId() != null) {

            Dishes_Categories dishesCategory = dishesCategoriesRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Dish Category Not Found!"));

            dish.setCategory(dishesCategory);
        }

        return dishesRepository.save(dish);
    }

    public String deleteDish(Long id) {

        Dishes dish = dishesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Dish Not Found!"));

        dishesRepository.delete(dish);

        return "Dish Deleted Successfully!";
    }
}