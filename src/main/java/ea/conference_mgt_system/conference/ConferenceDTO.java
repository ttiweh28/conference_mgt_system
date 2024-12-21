package ea.conference_mgt_system.conference;

import java.time.LocalDateTime;
import java.util.List;

public class ConferenceDTO {
    private String name;
    private String location;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Integer> workshopIds;  // A list of workshop IDs, or you could use a simpler representation of workshops.

    // Getters and Setters
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

    public List<Integer> getWorkshopIds() {
        return workshopIds;
    }

    public void setWorkshopIds(List<Integer> workshopIds) {
        this.workshopIds = workshopIds;
    }
}
