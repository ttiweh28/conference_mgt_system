package ea.conference_mgt_system.security;

import ea.conference_mgt_system.attendee.Attendee;
import ea.conference_mgt_system.conference.*;
import ea.conference_mgt_system.payment.*;
import ea.conference_mgt_system.registration.*;
import ea.conference_mgt_system.speaker.Speaker;
import ea.conference_mgt_system.speaker.SpeakerRepository;
import ea.conference_mgt_system.customer.*;
import ea.conference_mgt_system.workshop.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DataPopulator {

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Autowired
    private WorkshopRepository workshopRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerCreationService customerCreationService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    RegistrationService registrationService;

    @Bean
    public Object run() throws Exception {

        Customer admin =  new Customer("admin", "24", "admin@conference.com", Customer.Role.ADMIN);
        Attendee attendee1 = new Attendee("attendee1", "123", "attendee1@conference.com", Customer.Role.ATTENDEE, List.of(),List.of());
        Attendee attendee2 = new Attendee("attendee2", "111", "attendee2@conference.com", Customer.Role.ATTENDEE, List.of(),List.of());
        Speaker speaker = new Speaker("speaker1", "78hjk", "speaker1@conference.com",Customer.Role.SPEAKER,List.of(),"Maria z","Strong willed engineer",Arrays.asList());
        Speaker speaker2 = new Speaker("speaker2", "opio7", "speaker2@conference.com",Customer.Role.SPEAKER,List.of(),"Joseph w","Enthusiastic leader",Arrays.asList());

        customerCreationService.createAllUsers( Arrays.asList(admin, attendee1, attendee2,speaker,speaker2));

        Conference conference1 = new Conference(
                    "Tech Conference 2024",
                    "New York","Tech at its best",
                    LocalDateTime.of(2024, 5, 10, 9, 0),
                    LocalDateTime.of(2024, 5, 12, 18, 0)
            );
        Conference conference2 = new Conference(
                    "Business Summit 2024",
                    "Los Angeles","Business at its best",
                    LocalDateTime.of(2024, 6, 15, 9, 0),
                    LocalDateTime.of(2024, 6, 17, 18, 0)
            );
        Conference conference3 = new Conference(
                "AI for Business 2024",
                "New York", "Exploring AI tools for modern businesses",
                LocalDateTime.of(2024, 7, 5, 9, 0),
                LocalDateTime.of(2024, 7, 7, 18, 0)
        );


        Conference conference4 = new Conference(
                "Future of Tech 2024",
                "San Francisco", "Innovations in Technology and AI",
                LocalDateTime.of(2024, 8, 10, 9, 0),
                LocalDateTime.of(2024, 8, 12, 18, 0)
        );

        Conference conference5 = new Conference(
                "Digital Marketing Conference 2024",
                "Los Angeles", "The Future of Digital Marketing and SEO",
                LocalDateTime.of(2024, 9, 1, 9, 0),
                LocalDateTime.of(2024, 9, 3, 18, 0)
        );


        Conference conference6 = new Conference(
                "Innovative Business Strategies 2024",
                "Austin", "New business strategies for modern entrepreneurs",
                LocalDateTime.of(2024, 10, 5, 9, 0),
                LocalDateTime.of(2024, 10, 7, 18, 0)
        );


        Conference conference7 = new Conference(
                "Global Tech Conference 2024",
                "London", "Exploring technology across the globe",
                LocalDateTime.of(2024, 11, 10, 9, 0),
                LocalDateTime.of(2024, 11, 12, 18, 0)
        );


        Conference conference8 = new Conference(
                "International Business Summit 2024",
                "Berlin", "A deep dive into international business strategies",
                LocalDateTime.of(2024, 12, 1, 9, 0),
                LocalDateTime.of(2024, 12, 3, 18, 0)
        );

            //conferenceRepository.saveAll(Arrays.asList(conference1,conference2));

        conferenceRepository.saveAll(Arrays.asList(conference1, conference2,
                conference3, conference4, conference5, conference6, conference7, conference8
        ));


        Workshop session1 = new Workshop(
                    "AI in Business",
                    "Exploring AI tools and strategies for business",
                    LocalDateTime.of(2024, 5, 10, 10, 0),
                    LocalDateTime.of(2024, 5, 10, 12, 0),conference1, speaker, null);

        Workshop session2 = new Workshop(
                    "Data Privacy in 2024",
                    "Understanding data protection laws and best practices",
                    LocalDateTime.of(2024, 5, 11, 14, 0),
                    LocalDateTime.of(2024, 5, 11, 16, 0),
                    conference1,speaker,null
            );

            Workshop session3 = new Workshop(
                    "Digital Marketing Trends",
                    "The future of online marketing and SEO",
                    LocalDateTime.of(2024, 6, 15, 10, 0),
                    LocalDateTime.of(2024, 6, 15, 12, 0),
                    conference2,speaker2,
                    null
            );
        Workshop session4 = new Workshop(
                "LLMS and Policy",
                "Understanding the impact of Large Language Models on Policy",
                LocalDateTime.of(2024, 6, 15, 10, 0),
                LocalDateTime.of(2024, 6, 15, 12, 0),
                conference2,speaker2,
                null
        );



            workshopRepository.saveAll(Arrays.asList(session1, session2, session3,session4));

            Payment payment1 = new Payment(100.0, LocalDateTime.of(2024, 5, 1, 10, 0),PaymentStatus.PENDING );
            Payment payment2 = new Payment(120.0, LocalDateTime.of(2024, 6, 1, 14, 0), PaymentStatus.COMPLETED);

            paymentRepository.saveAll(Arrays.asList(payment1, payment2));

            Registration registration1 = new Registration(conference1,attendee2,payment1);

            registrationService.saveRegistration(registration1);


            System.out.println("Database populated with sample data!");

            return null;
    }

}
