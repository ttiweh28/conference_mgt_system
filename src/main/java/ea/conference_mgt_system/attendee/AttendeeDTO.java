package ea.conference_mgt_system.attendee;



import ea.conference_mgt_system.customer.Customer.Role;

import java.util.List;


public class AttendeeDTO {

    private int id;
    private String username;
    private String email;
    private Role role;
    private List<Integer> paymentIds; // Optional: If you need only payment IDs, not the full payment details

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Integer> getPaymentIds() {
        return paymentIds;
    }

    public void setPaymentIds(List<Integer> paymentIds) {
        this.paymentIds = paymentIds;
    }
}
