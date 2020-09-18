package hu.paninform.startmedsol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

import hu.paninform.startmedsol.domain.enumeration.IdDocType;

/**
 * A Card.
 */
@Entity
@Table(name = "card")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Card implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private IdDocType type;

    @NotNull
    @Size(max = 32)
    @Column(name = "identifier", length = 32, nullable = false)
    private String identifier;

    @Column(name = "valid_to")
    private LocalDate validTo;

    @Column(name = "jhi_primary")
    private Boolean primary;

    @ManyToOne
    @JsonIgnoreProperties(value = "cards", allowSetters = true)
    private Patient patient;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IdDocType getType() {
        return type;
    }

    public Card type(IdDocType type) {
        this.type = type;
        return this;
    }

    public void setType(IdDocType type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Card identifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public Card validTo(LocalDate validTo) {
        this.validTo = validTo;
        return this;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    public Boolean isPrimary() {
        return primary;
    }

    public Card primary(Boolean primary) {
        this.primary = primary;
        return this;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }

    public Patient getPatient() {
        return patient;
    }

    public Card patient(Patient patient) {
        this.patient = patient;
        return this;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Card)) {
            return false;
        }
        return id != null && id.equals(((Card) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Card{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", identifier='" + getIdentifier() + "'" +
            ", validTo='" + getValidTo() + "'" +
            ", primary='" + isPrimary() + "'" +
            "}";
    }
}
