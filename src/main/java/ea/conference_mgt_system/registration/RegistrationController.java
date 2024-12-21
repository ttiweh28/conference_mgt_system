package ea.conference_mgt_system.registration;

import ea.conference_mgt_system.conference.Conference;
import ea.conference_mgt_system.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Registration>> getAllRegistrations() {
        List<Registration> registrations = registrationService.getAllRegistrations();
        return new ResponseEntity<>(registrations, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{registrationId}")
    public ResponseEntity<Registration> getRegistrationById(@PathVariable int registrationId) {
        Optional<Registration> registration = registrationService.getRegistrationById(registrationId);
        return registration.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/usesearchcriteria")
    public ResponseEntity<List<Registration>> getRegistrationsByCriteria(
            @RequestParam(required = false) String paymentStatus,
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate,
            @RequestParam(required = false) Customer customer,
            @RequestParam(required = false) Conference conference) {

        List<Registration> registrations = registrationService.findRegistrationsByCriteria(paymentStatus, startDate, endDate, customer, conference);

        if (registrations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(registrations, HttpStatus.OK);
        }
    }
    // Endpoint to fetch registrations with conference and speaker details
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/searchbyconference")
    public ResponseEntity<List<Registration>> getRegistrationsWithConferenceAndSpeakers(
            @RequestParam(required = false) String conferenceName,
            @RequestParam(required = false) String speakerName) {

        List<Registration> registrations = registrationService.getRegistrationsWithConferenceAndSpeakers(conferenceName, speakerName);

        if (registrations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(registrations);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Registration> createRegistration(@RequestBody Registration registration) {
        Registration savedRegistration = registrationService.saveRegistration(registration);
        return new ResponseEntity<>(savedRegistration, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{registrationId}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable int registrationId) {
        registrationService.deleteRegistration(registrationId);
        return ResponseEntity.noContent().build();
    }
}

