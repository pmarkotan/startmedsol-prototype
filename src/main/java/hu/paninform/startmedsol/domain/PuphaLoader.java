package hu.paninform.startmedsol.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A PuphaLoader.
 */
@Entity
@Table(name = "pupha_loader")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PuphaLoader implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "event")
    private String event;

    @Column(name = "log")
    private String log;

    @Column(name = "time")
    private Instant time;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public PuphaLoader event(String event) {
        this.event = event;
        return this;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getLog() {
        return log;
    }

    public PuphaLoader log(String log) {
        this.log = log;
        return this;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Instant getTime() {
        return time;
    }

    public PuphaLoader time(Instant time) {
        this.time = time;
        return this;
    }

    public void setTime(Instant time) {
        this.time = time;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PuphaLoader)) {
            return false;
        }
        return id != null && id.equals(((PuphaLoader) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PuphaLoader{" +
            "id=" + getId() +
            ", event='" + getEvent() + "'" +
            ", log='" + getLog() + "'" +
            ", time='" + getTime() + "'" +
            "}";
    }
}
