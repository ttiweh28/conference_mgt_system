package ea.conference_mgt_system.registration;

import ea.conference_mgt_system.conference.Conference;
import ea.conference_mgt_system.customer.Customer;
import ea.conference_mgt_system.jms.MessageSender;
import ea.conference_mgt_system.speaker.Speaker;
import ea.conference_mgt_system.workshop.Workshop;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private MessageSender messageSender;

    @PersistenceContext
    private EntityManager entityManager;


    public Registration saveRegistration(Registration registration) {

        Registration newregistration1 = registrationRepository.save(registration);

        messageSender.sendMessage(newregistration1.getUser().getId(),newregistration1.getUser().getUsername(), newregistration1.getConference().getId());

        return newregistration1;


    }


    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }


    public Optional<Registration> getRegistrationById(int id) {
        return registrationRepository.findById(id);
    }


    public void deleteRegistration(int id) {
        registrationRepository.deleteById(id);
    }

    //criteria query
   public List<Registration> findRegistrationsByCriteria(String paymentStatus, Date startDate, Date endDate, Customer customer, Conference conference) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Registration> query = cb.createQuery(Registration.class);


        Root<Registration> registration = query.from(Registration.class);
        List<Predicate> predicates = new ArrayList<>();


        if (paymentStatus != null) {
            predicates.add(cb.equal(registration.get("paymentStatus"), paymentStatus));
        }


        if (startDate != null && endDate != null) {
            predicates.add(cb.between(registration.get("registrationDate"), startDate, endDate));
        }


        if (customer != null) {
            predicates.add(cb.equal(registration.get("customer"), customer));
        }


        if (conference != null) {
            predicates.add(cb.equal(registration.get("conference"), conference));
        }


        query.where(cb.and(predicates.toArray(new Predicate[0])));


        TypedQuery<Registration> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    //query involving three entities to retrieve registrations with associated conference and speaker
    public List<Registration> getRegistrationsWithConferenceAndSpeakers(String conferenceName, String speakerName) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Registration> query = cb.createQuery(Registration.class);
        Root<Registration> registrationRoot = query.from(Registration.class);


        Join<Registration, Conference> conferenceJoin = registrationRoot.join("conference", JoinType.INNER);


        Join<Conference, Workshop> workshopJoin = conferenceJoin.join("workshops", JoinType.INNER);
        Join<Workshop, Speaker> speakerJoin = workshopJoin.join("speaker", JoinType.INNER);


        List<Predicate> predicates = new ArrayList<>();


        if (conferenceName != null) {
            predicates.add(cb.equal(conferenceJoin.get("name"), conferenceName));
        }

        if (speakerName != null) {
            predicates.add(cb.like(speakerJoin.get("name"), "%" + speakerName + "%"));
        }


        query.where(cb.and(predicates.toArray(new Predicate[0])));


        TypedQuery<Registration> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();  // Return the list of registrations
    }
}
