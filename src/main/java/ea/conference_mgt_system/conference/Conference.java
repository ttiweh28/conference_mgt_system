package ea.conference_mgt_system.conference;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import ea.conference_mgt_system.registration.Registration;
import ea.conference_mgt_system.workshop.Workshop;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String location;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Version
    private int version;


    @OneToMany(mappedBy = "conference",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Workshop> workshops;

    @OneToMany(mappedBy = "conference",fetch = FetchType.LAZY)
    private List<Registration> registrations;


    protected Conference() {}

    public Conference(String name, String location, String description, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public List<Workshop> getWorkshops() {
        return workshops;
    }

    public void setWorkshops(List<Workshop> workshops) {
        this.workshops = workshops;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    @Override
    public String toString() {
        return "Conference{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
