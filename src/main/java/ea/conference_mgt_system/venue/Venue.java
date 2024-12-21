package ea.conference_mgt_system.venue;
import ea.conference_mgt_system.workshop.Workshop;
import jakarta.persistence.*;


import java.util.List;

@Entity
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String location;
    private String capacity;
    private String amenities;

    @Version
    private int version;

    @OneToMany(mappedBy = "venue")
    private List<Workshop> workshops;

    protected Venue() {}

    public Venue(String location, String capacity, String amenities, List<Workshop> workshops) {
        this.location = location;
        this.capacity = capacity;
        this.amenities = amenities;
        this.workshops = workshops;
    }

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public List<Workshop> getWorkshops() {
        return workshops;
    }

    public void setWorkshops(List<Workshop> workshops) {
        this.workshops = workshops;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", capacity='" + capacity + '\'' +
                ", amenities='" + amenities + '\'' +
                '}';
    }
}
