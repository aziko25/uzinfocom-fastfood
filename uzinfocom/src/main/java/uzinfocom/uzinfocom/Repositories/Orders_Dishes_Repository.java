package uzinfocom.uzinfocom.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uzinfocom.uzinfocom.Models.Orders_Dishes;
import uzinfocom.uzinfocom.Models.Orders_Dishes_Ids;

@Repository
public interface Orders_Dishes_Repository extends JpaRepository<Orders_Dishes, Orders_Dishes_Ids> {
}