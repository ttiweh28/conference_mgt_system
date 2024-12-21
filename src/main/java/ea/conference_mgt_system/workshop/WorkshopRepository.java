package ea.conference_mgt_system.workshop;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Integer> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT w FROM Workshop w WHERE w.conference=?1")
    List<Workshop> findByConferenceId(int conferenceId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT w FROM Workshop w WHERE w.speaker=?1")
    List<Workshop> findBySpeakerId(int speakerId);
}
