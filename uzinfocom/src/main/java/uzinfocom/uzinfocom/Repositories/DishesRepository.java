package uzinfocom.uzinfocom.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uzinfocom.uzinfocom.Models.Dishes;
import uzinfocom.uzinfocom.Models.Dishes_Categories;

import java.util.List;

@Repository
public interface DishesRepository extends JpaRepository<Dishes, Long> {

    List<Dishes> findAllByCategory(Dishes_Categories category);
}