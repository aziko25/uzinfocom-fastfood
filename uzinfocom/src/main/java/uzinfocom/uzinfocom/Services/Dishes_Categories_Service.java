package uzinfocom.uzinfocom.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uzinfocom.uzinfocom.Models.Dishes_Categories;
import uzinfocom.uzinfocom.Repositories.Dishes_Categories_Repository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Dishes_Categories_Service {

    private final Dishes_Categories_Repository dishesCategoriesRepository;

    public Dishes_Categories create(String name) {

        try {

            Dishes_Categories category = Dishes_Categories.builder().name(name).build();

            return dishesCategoriesRepository.save(category);
        }
        catch (DataIntegrityViolationException e) {

            throw new DataIntegrityViolationException(e.getMessage());
        }
    }

    public List<Dishes_Categories> findAll() {

        return dishesCategoriesRepository.findAll(Sort.by("name").ascending());
    }

    public Dishes_Categories update(Long id, String name) {

        Dishes_Categories category = dishesCategoriesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Dishes categories not found"));

        try {

            if (name != null) {

                category.setName(name);
            }

            return dishesCategoriesRepository.save(category);
        }
        catch (DataIntegrityViolationException e) {

            throw new DataIntegrityViolationException(e.getMessage());
        }
    }

    public String delete(Long id) {

        Dishes_Categories category = dishesCategoriesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Dishes categories not found"));

        dishesCategoriesRepository.delete(category);

        return "Dishes categories deleted";
    }
}