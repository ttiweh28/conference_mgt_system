package ea.conference_mgt_system.registration;


import ea.conference_mgt_system.customer.Customer;
import ea.conference_mgt_system.conference.Conference;
import ea.conference_mgt_system.payment.Payment;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date registrationDate;
    private String paymentStatus;
    private Double paymentAmount;

    @Version
    private int version;

   @OneToOne
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Conference conference;

    @OneToOne
    private Payment payment;

    protected Registration() {}

    public Registration(Customer customer, Conference conference) {
        this.customer = customer;
        this.conference = conference;
    }

    public Registration(Conference conference, Customer customer, Payment payment) {
        this.conference = conference;
        this.customer = customer;
        this.payment = payment;
    }

    public Registration(Date registrationDate, String paymentStatus, Double paymentAmount, Customer customer, Conference conference, Payment payment) {
        this.registrationDate = registrationDate;
        this.paymentStatus = paymentStatus;
        this.paymentAmount = paymentAmount;
        this.customer = customer;
        this.conference = conference;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }


    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Customer getUser() {
        return customer;
    }

    public void setUser(Customer customer) {
        this.customer = customer;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "id=" + id +
                ", registrationDate=" + registrationDate +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", paymentAmount=" + paymentAmount +
                '}';
    }
}
