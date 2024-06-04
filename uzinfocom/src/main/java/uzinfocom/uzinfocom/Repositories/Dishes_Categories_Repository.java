package uzinfocom.uzinfocom.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uzinfocom.uzinfocom.Models.Dishes_Categories;

@Repository
public interface Dishes_Categories_Repository extends JpaRepository<Dishes_Categories, Long> {
}