package uzinfocom.uzinfocom.Repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uzinfocom.uzinfocom.Models.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByPhone(String phone);

    List<Users> findAllByRole(String role, Sort sort);
}