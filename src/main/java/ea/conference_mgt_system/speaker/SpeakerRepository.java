package ea.conference_mgt_system.speaker;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeakerRepository extends JpaRepository<Speaker, Integer> {

    // Find Speaker by username
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Speaker s WHERE s.username=?1")
    Speaker findByUsername(String username);
}
