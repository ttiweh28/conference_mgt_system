package ea.conference_mgt_system.conference;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Integer>
{
    //My Dynamic Query to filter conferences by workshop and location
    @Query("SELECT c FROM Conference c JOIN c.workshops w WHERE w.id = :workshopId AND c.location = :location")
    List<Conference> findByWorkshops_idAndLocation(int workshopid, String location);
}
