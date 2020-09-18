package hu.paninform.startmedsol.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import hu.paninform.startmedsol.domain.enumeration.StatisticsType;

/**
 * A Statistics.
 */
@Entity
@Table(name = "statistics")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Statistics implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "created", nullable = false)
    private Instant created;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tpye", nullable = false)
    private StatisticsType tpye;

    @Size(max = 1024)
    @Column(name = "content", length = 1024)
    private String content;

    @Size(max = 1024)
    @Column(name = "description", length = 1024)
    private String description;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreated() {
        return created;
    }

    public Statistics created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public StatisticsType getTpye() {
        return tpye;
    }

    public Statistics tpye(StatisticsType tpye) {
        this.tpye = tpye;
        return this;
    }

    public void setTpye(StatisticsType tpye) {
        this.tpye = tpye;
    }

    public String getContent() {
        return content;
    }

    public Statistics content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public Statistics description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Statistics)) {
            return false;
        }
        return id != null && id.equals(((Statistics) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Statistics{" +
            "id=" + getId() +
            ", created='" + getCreated() + "'" +
            ", tpye='" + getTpye() + "'" +
            ", content='" + getContent() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
