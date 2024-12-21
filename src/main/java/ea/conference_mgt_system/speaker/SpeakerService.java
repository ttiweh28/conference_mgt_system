package ea.conference_mgt_system.speaker;

import ea.conference_mgt_system.workshop.Workshop;
import ea.conference_mgt_system.workshop.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpeakerService {
    @Autowired
    private SpeakerRepository speakerRepository;

    @Autowired
    private WorkshopRepository workshopRepository;

    public List<Workshop> getSpeakerSessions(int speakerId) {
        return workshopRepository.findBySpeakerId(speakerId);
    }

    @Transactional
    public Speaker getSpeakerByUsername(String username) {
        return speakerRepository.findByUsername(username);
    }

}
