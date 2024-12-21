package ea.conference_mgt_system.attendee;

import ea.conference_mgt_system.conference.Conference;
import ea.conference_mgt_system.conference.ConferenceRepository;
import ea.conference_mgt_system.payment.Payment;
import ea.conference_mgt_system.payment.PaymentRepository;
import ea.conference_mgt_system.payment.PaymentStatus;
import ea.conference_mgt_system.registration.Registration;
import ea.conference_mgt_system.registration.RegistrationRepository;
import ea.conference_mgt_system.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendeeService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AttendeeRepository attendeeRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ConferenceRepository conferenceRepository;



    public Registration registerForConference(int conferenceId, int attendeeId) {

        Conference conference = conferenceRepository.findById(conferenceId)
                .orElseThrow(() -> new IllegalArgumentException("Conference not found"));


        Attendee attendee = (Attendee) customerRepository.findById(attendeeId)
                .orElseThrow(() -> new IllegalArgumentException("Attendee not found"));

        Registration registration = new Registration(attendee, conference);

        registration = registrationRepository.save(registration);

        return registration;
    }


    public List<Conference> getRegisteredConferences(int attendeeId) {

        List<Registration> registrations = registrationRepository.findByAttendeeId(attendeeId);

        return registrations.stream()
                .map(Registration::getConference)
                .toList();
    }
}
