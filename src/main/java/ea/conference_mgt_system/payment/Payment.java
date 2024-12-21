package ea.conference_mgt_system.payment;


import ea.conference_mgt_system.attendee.Attendee;
import ea.conference_mgt_system.registration.Registration;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Double amount;
    private LocalDateTime paymentDate;
    private PaymentStatus paymentStatus;

    @Version
    private int version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="attendee_id")
    private Attendee attendee;

    protected Payment() {}

    public Payment(Double amount, LocalDateTime paymentDate, PaymentStatus paymentStatus) {
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
    }

    public int getId() {
        return id;
    }
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }


    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}
