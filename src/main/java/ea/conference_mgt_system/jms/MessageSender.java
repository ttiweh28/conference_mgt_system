package ea.conference_mgt_system.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Value(value = "${springjms.queueName}")
    private String queueName;

    //registeredForConference
    public void sendMessage(int attendeeId, String username,int conferenceId) {

        String message = String.format("Username:%s with Attendee ID: %d has registered for Conference ID: %d", username,attendeeId, conferenceId);

        jmsTemplate.convertAndSend(queueName, message);

        System.out.println("Message sent to conference.registration queue: " + message);

    }
}
