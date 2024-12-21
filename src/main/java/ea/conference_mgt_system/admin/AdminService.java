package ea.conference_mgt_system.admin;

import ea.conference_mgt_system.conference.Conference;
import ea.conference_mgt_system.conference.ConferenceRepository;
import ea.conference_mgt_system.customer.Customer;
import ea.conference_mgt_system.customer.CustomerRepository;
import ea.conference_mgt_system.workshop.Workshop;
import ea.conference_mgt_system.workshop.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private WorkshopRepository workshopRepository;


    public Conference createConference(Conference conference) {
        return conferenceRepository.save(conference);
    }


    public void deleteConference(int id) {
        conferenceRepository.deleteById(id);
    }


    public List<Customer> listUsers() {
        return customerRepository.findAll();
    }


    public Workshop createWorkshopSession(Workshop session) {
        return workshopRepository.save(session);
    }

}

