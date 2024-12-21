package ea.conference_mgt_system.aop;

import ea.conference_mgt_system.customer.Customer;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomerServiceAspect {
    @Before("execution(* ea.conference_mgt_system.customer.CustomerService.createUser(..)) && args(customer)")
    public void logBeforeCreateUser(Customer customer) {
        System.out.println("Creating a new user: " + customer.getUsername()+ " who is a/an "+ customer.getRole());
    }
}
