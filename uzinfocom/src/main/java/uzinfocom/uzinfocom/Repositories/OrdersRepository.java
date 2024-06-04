package uzinfocom.uzinfocom.Repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uzinfocom.uzinfocom.Models.Enums.OrdersStatuses;
import uzinfocom.uzinfocom.Models.Orders;
import uzinfocom.uzinfocom.Models.Users;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findAllByClient(Users client, Sort sort);

    List<Orders> findAllByStatus(OrdersStatuses status);

    List<Orders> findAllByStatusIn(List<OrdersStatuses> statuses, Sort sort);
}