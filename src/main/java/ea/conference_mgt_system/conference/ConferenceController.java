package ea.conference_mgt_system.conference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conferences")
public class ConferenceController {

@Autowired
private ConferenceService conferenceService;


@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public ResponseEntity<List<Conference>> getAllConferences() {
    List<Conference> conferences = conferenceService.getAllConferences();
    return new ResponseEntity<>(conferences, HttpStatus.OK);
}
@GetMapping("/{conferenceId}")
public ResponseEntity<Conference> getConference(@PathVariable int conferenceId) {
    Conference conference = conferenceService.getConference(conferenceId);
    return new ResponseEntity<>(conference, HttpStatus.OK);
}

@GetMapping("/{workshopId}/{location}")
public ResponseEntity<List<Conference>> getConferenceByWorkshop(@PathVariable int workshopId,@PathVariable String location){
    List<Conference> conferences = conferenceService.getConferencesByWorkshopIdAndLocation(workshopId, location);
    return new ResponseEntity<>(conferences, HttpStatus.OK);
}

@PreAuthorize("hasRole('ADMIN')")
@PostMapping(path = "/create-conference",consumes = "application/json", produces = "application/json")
    public ResponseEntity<ConferenceDTO> createConference(@RequestBody ConferenceDTO conferenceDTO) {
        Conference conference = new Conference();
        conference.setName(conferenceDTO.getName());
        conference.setLocation(conferenceDTO.getLocation());
        conference.setDescription(conferenceDTO.getDescription());
        conference.setStartDate(conferenceDTO.getStartDate());
        conference.setEndDate(conferenceDTO.getEndDate());

        Conference savedConference = conferenceService.createConference(conference);

        ConferenceDTO savedConferenceDTO = new ConferenceDTO();
        savedConferenceDTO.setName(savedConference.getName());
        savedConferenceDTO.setLocation(savedConference.getLocation());
        savedConferenceDTO.setDescription(savedConference.getDescription());
        savedConferenceDTO.setStartDate(savedConference.getStartDate());
        savedConferenceDTO.setEndDate(savedConference.getEndDate());


        return new ResponseEntity<>(savedConferenceDTO, HttpStatus.CREATED);
    }


@PreAuthorize("hasRole('ADMIN')")
@PostMapping("/update-conference/{conferenceId}")
public ResponseEntity<Conference> updateConference(@PathVariable int conferenceId, @RequestBody Conference conference) {
    Conference updatedConference = conferenceService.updateConference(conferenceId, conference);
    return new ResponseEntity<>(updatedConference, HttpStatus.OK);
}

@PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/delete-conference/{conferenceId}")
public ResponseEntity<Conference> deleteConference(@PathVariable int conferenceId) {
    conferenceService.deleteConference(conferenceId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
}
