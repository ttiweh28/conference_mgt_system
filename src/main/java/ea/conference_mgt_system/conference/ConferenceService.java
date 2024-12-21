package ea.conference_mgt_system.conference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;

@Service
public class ConferenceService {

    @Autowired
    private ConferenceRepository conferenceRepository;


    public Conference createConference(Conference conference) {
        return conferenceRepository.save(conference);
    }
//    public List<Conference> getAllConferences() {
//        return conferenceRepository.findAll();
//    }

    public List<Conference> getAllConferences() {
        List<Conference> conferences = conferenceRepository.findAll();
        conferences.forEach(conference -> {
            conference.setWorkshops(null);
            conference.setRegistrations(null);// Inspect each conference and its workshops
        });
        return conferences;
    }


    public Conference getConference(int id) {
        Optional<Conference> conference = conferenceRepository.findById(id);
        if (conference.isPresent()) {
            return conference.get();
        }
        throw new RuntimeException("Conference not found");
    }

    //find conferences by workshop and location
    public List<Conference> getConferencesByWorkshopIdAndLocation(int workshopId, String location) {
        return conferenceRepository.findByWorkshops_idAndLocation(workshopId, location);
    }


    public Conference updateConference(int id, Conference updatedConference) {
        Optional<Conference> existingConference = conferenceRepository.findById(id);
        if (existingConference.isPresent()) {
            Conference conference = existingConference.get();
            conference.setName(updatedConference.getName());
            conference.setLocation(updatedConference.getLocation());
            conference.setStartDate(updatedConference.getStartDate());
            conference.setEndDate(updatedConference.getEndDate());
            return conferenceRepository.save(conference);
        }
         throw new RuntimeException("Conference not found");
    }



    public boolean deleteConference(int id) {
        Optional<Conference> conference = conferenceRepository.findById(id);
        if (conference.isPresent()) {
            conferenceRepository.delete(conference.get());
            return true;
        }
        return false;
    }


}
