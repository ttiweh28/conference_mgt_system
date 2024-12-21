package ea.conference_mgt_system.workshop;

import ea.conference_mgt_system.conference.Conference;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkshopService {

    @Autowired
    private WorkshopRepository workshopRepository;


        public Workshop createWorkshop(Workshop workshop) {
            return workshopRepository.save(workshop);
        }

        public List<Workshop> getAllWorkshops() {
            return workshopRepository.findAll();
        }

        @Transactional
        public List<Workshop> getWorkshopsForConference(int conferenceId) {
            return workshopRepository.findByConferenceId(conferenceId);
        }

        @Transactional
        public List<Workshop> getWorkshopsForSpeaker(int speakerId) {
            return workshopRepository.findBySpeakerId(speakerId);
        }

        public Workshop updateWorkshop(int id, Workshop updatedSession) {
            Optional<Workshop> existingSession = workshopRepository.findById(id);
            if (existingSession.isPresent()) {
                Workshop session = existingSession.get();
                session.setTitle(updatedSession.getTitle());
                session.setDescription(updatedSession.getDescription());
                session.setStartTime(updatedSession.getStartTime());
                session.setEndTime(updatedSession.getEndTime());
                return workshopRepository.save(session);
            }
            return null;
        }





        public boolean deleteWorkshop(int id) {
            Optional<Workshop> session = workshopRepository.findById(id);
            if (session.isPresent()) {
                workshopRepository.delete(session.get());
                return true;
            }
            return false;
        }
    }


