package hu.paninform.startmedsol.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import hu.paninform.startmedsol.domain.enumeration.AnnouncementLocation;
import hu.paninform.startmedsol.domain.enumeration.AnnouncementType;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.Announcement} entity.
 */
public class AnnouncementDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Instant publishingDate;

    @NotNull
    private Instant expireDate;

    @NotNull
    private AnnouncementLocation location;

    @NotNull
    private AnnouncementType type;

    @NotNull
    @Size(max = 1024)
    private String content;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(Instant publishingDate) {
        this.publishingDate = publishingDate;
    }

    public Instant getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Instant expireDate) {
        this.expireDate = expireDate;
    }

    public AnnouncementLocation getLocation() {
        return location;
    }

    public void setLocation(AnnouncementLocation location) {
        this.location = location;
    }

    public AnnouncementType getType() {
        return type;
    }

    public void setType(AnnouncementType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnnouncementDTO)) {
            return false;
        }

        return id != null && id.equals(((AnnouncementDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnnouncementDTO{" +
            "id=" + getId() +
            ", publishingDate='" + getPublishingDate() + "'" +
            ", expireDate='" + getExpireDate() + "'" +
            ", location='" + getLocation() + "'" +
            ", type='" + getType() + "'" +
            ", content='" + getContent() + "'" +
            "}";
    }
}
