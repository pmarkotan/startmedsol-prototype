package hu.paninform.startmedsol.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.PuphaLoader} entity.
 */
public class PuphaLoaderDTO implements Serializable {
    
    private Long id;

    private String event;

    private String log;

    private Instant time;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PuphaLoaderDTO)) {
            return false;
        }

        return id != null && id.equals(((PuphaLoaderDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PuphaLoaderDTO{" +
            "id=" + getId() +
            ", event='" + getEvent() + "'" +
            ", log='" + getLog() + "'" +
            ", time='" + getTime() + "'" +
            "}";
    }
}
