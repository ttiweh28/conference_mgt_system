package ea.conference_mgt_system.workshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/workshops")
public class WorkshopController {

    @Autowired
    private WorkshopService workshopService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('SPEAKER')")
    @GetMapping
    public ResponseEntity<List<Workshop>> getAllWorkshops() {
        List<Workshop> workshops = workshopService.getAllWorkshops();
        return new ResponseEntity<>(workshops,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SPEAKER')")
    @GetMapping("/conferences/{conferenceId}")
    public ResponseEntity<List<Workshop>> getWorkshopsByConference(@PathVariable int conferenceId) {
       List<Workshop> workshops = workshopService.getWorkshopsForConference(conferenceId);
       return new ResponseEntity<>(workshops,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SPEAKER')")
    @GetMapping("/speakers/{speakerId}")
    public ResponseEntity<List<Workshop>> getWorkshopBySpeaker(@PathVariable int speakerId) {
        List<Workshop> workshops = workshopService.getWorkshopsForSpeaker(speakerId);
        return new ResponseEntity<>(workshops,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SPEAKER')")
    @PostMapping("/create-workshop")
    public ResponseEntity<Workshop> createWorkshop(@RequestBody Workshop workshop) {
        return new ResponseEntity<>(workshopService.createWorkshop(workshop), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SPEAKER')")
    @PostMapping("/update-workshop/{workshopId}")
    public ResponseEntity<Workshop> updateWorkshop(@PathVariable int workshopId, @RequestBody Workshop workshop) {
        Workshop workshopUpdated = workshopService.updateWorkshop(workshopId, workshop);
        return new ResponseEntity<>(workshopUpdated, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SPEAKER')")
    @DeleteMapping("/delete-cworkshop/{workshopId}")
    public ResponseEntity<Workshop> deleteWorkshop(@PathVariable int workshopId) {
        workshopService.deleteWorkshop(workshopId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
