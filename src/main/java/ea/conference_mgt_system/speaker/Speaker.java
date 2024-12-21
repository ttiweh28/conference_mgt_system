package ea.conference_mgt_system.speaker;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ea.conference_mgt_system.customer.Customer;
import ea.conference_mgt_system.registration.Registration;
import ea.conference_mgt_system.workshop.Workshop;
import jakarta.persistence.*;


import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Speaker extends Customer {

    private String name;
    private String bio;



    @OneToMany(mappedBy = "speaker",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Workshop> workshops;

    protected Speaker() {}

    public Speaker(String username, String password, String email, Role role, List<Registration> registrations,String name, String bio, List<Workshop> workshops) {
        super(username, password, email, role);
        this.name = name;
        this.bio = bio;
        this.workshops = workshops;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }


public List<Workshop> getWorkshops() {
        return workshops;
    }

    public void setWorkshops(List<Workshop> workshops) {
        this.workshops = workshops;
    }

    @Override
    public String toString() {
        return "Speaker{" +
                "name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}