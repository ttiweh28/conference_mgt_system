package ea.conference_mgt_system.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;


import java.util.List;

@RestController
@RequestMapping("/api/users")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerCreationService customerCreationService;

    @GetMapping("/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminDashboard() {
        return "Welcome Admin!";
    }

    @GetMapping("/attendee/dashboard")
    @PreAuthorize("hasRole('ATTENDEE')")
    public String attendeeDashboard() {
        return "Welcome Attendee!";
    }

    @GetMapping("/speaker/dashboard")
    @PreAuthorize("hasRole('SPEAKER')")
    public String speakerDashboard() {
        return "Welcome Speaker!";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Customer>> getAllUsers() {
        List<Customer> customers = customerService.getAllUsers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createUser(customer);
        return ResponseEntity.ok(createdCustomer);
    }

}

