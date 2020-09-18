package hu.paninform.startmedsol.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import hu.paninform.startmedsol.domain.enumeration.AnnouncementLocation;

import hu.paninform.startmedsol.domain.enumeration.AnnouncementType;

/**
 * A Announcement.
 */
@Entity
@Table(name = "announcement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Announcement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "publishing_date", nullable = false)
    private Instant publishingDate;

    @NotNull
    @Column(name = "expire_date", nullable = false)
    private Instant expireDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "location", nullable = false)
    private AnnouncementLocation location;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private AnnouncementType type;

    @NotNull
    @Size(max = 1024)
    @Column(name = "content", length = 1024, nullable = false)
    private String content;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getPublishingDate() {
        return publishingDate;
    }

    public Announcement publishingDate(Instant publishingDate) {
        this.publishingDate = publishingDate;
        return this;
    }

    public void setPublishingDate(Instant publishingDate) {
        this.publishingDate = publishingDate;
    }

    public Instant getExpireDate() {
        return expireDate;
    }

    public Announcement expireDate(Instant expireDate) {
        this.expireDate = expireDate;
        return this;
    }

    public void setExpireDate(Instant expireDate) {
        this.expireDate = expireDate;
    }

    public AnnouncementLocation getLocation() {
        return location;
    }

    public Announcement location(AnnouncementLocation location) {
        this.location = location;
        return this;
    }

    public void setLocation(AnnouncementLocation location) {
        this.location = location;
    }

    public AnnouncementType getType() {
        return type;
    }

    public Announcement type(AnnouncementType type) {
        this.type = type;
        return this;
    }

    public void setType(AnnouncementType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public Announcement content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Announcement)) {
            return false;
        }
        return id != null && id.equals(((Announcement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Announcement{" +
            "id=" + getId() +
            ", publishingDate='" + getPublishingDate() + "'" +
            ", expireDate='" + getExpireDate() + "'" +
            ", location='" + getLocation() + "'" +
            ", type='" + getType() + "'" +
            ", content='" + getContent() + "'" +
            "}";
    }
}
