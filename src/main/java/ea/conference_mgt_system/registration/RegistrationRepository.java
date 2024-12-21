package ea.conference_mgt_system.registration;

import ea.conference_mgt_system.conference.Conference;
import ea.conference_mgt_system.customer.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

    //find conferences by attendeeid
    @Query("SELECT r FROM Registration r WHERE r.customer=?1")
    List<Registration> findByAttendeeId(int attendeeId);

}
