package ea.conference_mgt_system.workshop;

import com.fasterxml.jackson.annotation.*;
import ea.conference_mgt_system.conference.Conference;
import ea.conference_mgt_system.speaker.Speaker;
import ea.conference_mgt_system.venue.Venue;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties({"conference"})
public class Workshop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Version
    private int version;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "conference_id", nullable = true)
    @JsonBackReference
    private Conference conference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "speaker_id")
    @JsonManagedReference
    private Speaker speaker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id")
    private Venue venue;

    protected Workshop() {}
    public Workshop(String title, String description, LocalDateTime startTime, LocalDateTime endTime, Conference conference, Speaker speaker, Venue venue) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.conference = conference;
        this.speaker = speaker;
        this.venue = venue;
    }

    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    @Override
    public String toString() {
        return "Workshop{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
