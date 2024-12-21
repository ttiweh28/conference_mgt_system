package ea.conference_mgt_system.admin;

import ea.conference_mgt_system.customer.Customer;
import jakarta.persistence.*;

@Entity
public class Admin extends Customer {

    @Id@GeneratedValue
    private int id;

    private String Name;

    protected Admin() {}
    public Admin(String name) {
        this.Name = name;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

}
