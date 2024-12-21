package ea.conference_mgt_system.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VenueService {

    @Autowired
    private VenueRepository venueRepository;

    // Save a new venue or update existing
    public Venue saveVenue(Venue venue) {
        return venueRepository.save(venue);
    }

    // Get all venues
    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    // Get venue by id
    public Optional<Venue> getVenueById(int id) {
        return venueRepository.findById(id);
    }

    // Delete venue by id
    public void deleteVenue(int id) {
        venueRepository.deleteById(id);
    }
}
