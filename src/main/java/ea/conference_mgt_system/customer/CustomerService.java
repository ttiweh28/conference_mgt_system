package ea.conference_mgt_system.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepository customerRepository;


    public Customer createUser(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

//    public void createAllUser(List<? extends Customer> customer) {
//
//        customer.forEach(c ->createUser(c));
//    }
//public void createAllUser(List<? extends Customer> customers) {
//    customers.forEach(c -> self.createUser(c));  // Call via proxy
//}


    public List<Customer> getAllUsers() {
        return customerRepository.findAll();
    }


    public Customer updateUser(int id, Customer customer) {
        return customerRepository.save(customer);
    }

}
