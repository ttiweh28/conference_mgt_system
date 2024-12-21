package ea.conference_mgt_system.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/venues")
public class VenueController {

    @Autowired
    private VenueService venueService;


    @GetMapping
    public ResponseEntity<List<Venue>> getAllVenues() {
        List<Venue> venues = venueService.getAllVenues();
        return new ResponseEntity<>(venues, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Venue> getVenueById(@PathVariable int id) {
        Optional<Venue> venue = venueService.getVenueById(id);
        return venue.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @PostMapping
    public ResponseEntity<Venue> createVenue(@RequestBody Venue venue) {
        Venue savedVenue = venueService.saveVenue(venue);
        return new ResponseEntity<>(savedVenue, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenue(@PathVariable int id) {
        venueService.deleteVenue(id);
        return ResponseEntity.noContent().build();
    }
}
