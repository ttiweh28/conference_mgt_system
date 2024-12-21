package ea.conference_mgt_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
@EnableAspectJAutoProxy
public class ConferenceMgtSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferenceMgtSystemApplication.class, args);
    }

}
