package ea.conference_mgt_system.customer;

import ea.conference_mgt_system.registration.Registration;
import jakarta.persistence.*;

import java.util.List;


@Entity
@NamedQuery(name="Customer.findByUsername", query="SELECT c FROM Customer c WHERE c.username = :username")
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String username;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Version
    private int version;

    protected Customer() {}

    public Customer(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public enum Role {
        ADMIN, ATTENDEE, SPEAKER
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
