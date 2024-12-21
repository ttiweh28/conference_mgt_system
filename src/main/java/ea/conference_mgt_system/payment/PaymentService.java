package ea.conference_mgt_system.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;


    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }


    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }


    public Optional<Payment> getPaymentById(int id) {
        return paymentRepository.findById(id);
    }


    public void deletePayment(int id) {
        paymentRepository.deleteById(id);
    }
}
