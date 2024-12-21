package ea.conference_mgt_system.speaker;

import ea.conference_mgt_system.workshop.Workshop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/speakers")
public class SpeakerController {

    @Autowired
    private SpeakerService speakerService;

    // Endpoint for speakers to view their sessions
    @PreAuthorize("hasRole('SPEAKER')")
    @GetMapping("/my-sessions/{speakerId}")
    public ResponseEntity<List<Workshop>> getSpeakerSessions(@PathVariable int speakerId, Authentication authentication) {
        String currentUsername = authentication.getName();


        Speaker authenticatedSpeaker = speakerService.getSpeakerByUsername(currentUsername);
        if (authenticatedSpeaker == null || authenticatedSpeaker.getId() != speakerId) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);  // Forbidden if IDs don't match
        }

        List<Workshop> sessions = speakerService.getSpeakerSessions(speakerId);
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }

}

