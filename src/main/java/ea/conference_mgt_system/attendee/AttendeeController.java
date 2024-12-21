package ea.conference_mgt_system.attendee;

import ea.conference_mgt_system.conference.Conference;
import ea.conference_mgt_system.registration.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendees")
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeService;


    @PreAuthorize("hasRole('ATTENDEE')")
    @PostMapping("/register-conference/{conferenceId}")
    public ResponseEntity<Registration> registerForConference(@PathVariable int conferenceId, @RequestParam int attendeeId) {
        Registration savedRegistration = attendeeService.registerForConference(conferenceId, attendeeId);
        return new ResponseEntity<>(savedRegistration, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ATTENDEE')")
    @GetMapping("/my-conferences/{attendeeId}")
    public ResponseEntity<List<Conference>> getRegisteredConferences(@PathVariable int attendeeId) {
        List<Conference> conferences = attendeeService.getRegisteredConferences(attendeeId);
        return new ResponseEntity<>(conferences, HttpStatus.OK);
    }


}

