package ea.conference_mgt_system.attendee;

import ea.conference_mgt_system.customer.Customer;
import ea.conference_mgt_system.payment.Payment;
import ea.conference_mgt_system.registration.Registration;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class Attendee extends Customer {

    @OneToMany(mappedBy = "attendee")
    private List<Payment> payments;

    protected Attendee() {}

    public Attendee(String username, String password, String email, Role role, List<Registration> registrations, List<Payment> payments) {
        super(username, password, email, role);
        this.payments = payments;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

}

