package ea.conference_mgt_system.customer;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    //using my named query
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Customer> findByUsername(String username);
}
