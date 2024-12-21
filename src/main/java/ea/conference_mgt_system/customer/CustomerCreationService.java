package ea.conference_mgt_system.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerCreationService {

    @Autowired
    private CustomerService customerService;

    public void createAllUsers(List<? extends Customer> customers) {
        customers.forEach(customerService::createUser);  // External call to createUser
    }
}
