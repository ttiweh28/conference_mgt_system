package ea.conference_mgt_system.customer;


import ea.conference_mgt_system.customer.Customer.Role;

public class CustomerDTO {

    private int id;
    private String username;
    private String email;
    private Role role;

    // Default constructor
    public CustomerDTO() {}

    // Constructor for initializing DTO from Customer entity
    public CustomerDTO(int id, String username, String email, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    // Getter and Setter methods
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

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }

    // Static method to convert Customer entity to CustomerDTO
    public static CustomerDTO fromEntity(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getUsername(),
                customer.getEmail(),
                customer.getRole()
        );
    }
}
