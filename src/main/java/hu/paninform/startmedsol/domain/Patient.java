package hu.paninform.startmedsol.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Patient.
 */
@Entity
@Table(name = "patient")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 255)
    @Column(name = "patient_identifier", length = 255, unique = true)
    private String patientIdentifier;

    @Column(name = "privacy_statement")
    private Boolean privacyStatement;

    @Column(name = "anonymised")
    private Boolean anonymised;

    @Column(name = "classified")
    private Boolean classified;

    @OneToOne
    @JoinColumn(unique = true)
    private PersonalData personalData;

    @OneToMany(mappedBy = "patient")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Card> cards = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "patient_tags",
               joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "tags_id", referencedColumnName = "id"))
    private Set<HashTag> tags = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientIdentifier() {
        return patientIdentifier;
    }

    public Patient patientIdentifier(String patientIdentifier) {
        this.patientIdentifier = patientIdentifier;
        return this;
    }

    public void setPatientIdentifier(String patientIdentifier) {
        this.patientIdentifier = patientIdentifier;
    }

    public Boolean isPrivacyStatement() {
        return privacyStatement;
    }

    public Patient privacyStatement(Boolean privacyStatement) {
        this.privacyStatement = privacyStatement;
        return this;
    }

    public void setPrivacyStatement(Boolean privacyStatement) {
        this.privacyStatement = privacyStatement;
    }

    public Boolean isAnonymised() {
        return anonymised;
    }

    public Patient anonymised(Boolean anonymised) {
        this.anonymised = anonymised;
        return this;
    }

    public void setAnonymised(Boolean anonymised) {
        this.anonymised = anonymised;
    }

    public Boolean isClassified() {
        return classified;
    }

    public Patient classified(Boolean classified) {
        this.classified = classified;
        return this;
    }

    public void setClassified(Boolean classified) {
        this.classified = classified;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public Patient personalData(PersonalData personalData) {
        this.personalData = personalData;
        return this;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public Patient cards(Set<Card> cards) {
        this.cards = cards;
        return this;
    }

    public Patient addCard(Card card) {
        this.cards.add(card);
        card.setPatient(this);
        return this;
    }

    public Patient removeCard(Card card) {
        this.cards.remove(card);
        card.setPatient(null);
        return this;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public Set<HashTag> getTags() {
        return tags;
    }

    public Patient tags(Set<HashTag> hashTags) {
        this.tags = hashTags;
        return this;
    }

    public Patient addTags(HashTag hashTag) {
        this.tags.add(hashTag);
        hashTag.getPatients().add(this);
        return this;
    }

    public Patient removeTags(HashTag hashTag) {
        this.tags.remove(hashTag);
        hashTag.getPatients().remove(this);
        return this;
    }

    public void setTags(Set<HashTag> hashTags) {
        this.tags = hashTags;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Patient)) {
            return false;
        }
        return id != null && id.equals(((Patient) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Patient{" +
            "id=" + getId() +
            ", patientIdentifier='" + getPatientIdentifier() + "'" +
            ", privacyStatement='" + isPrivacyStatement() + "'" +
            ", anonymised='" + isAnonymised() + "'" +
            ", classified='" + isClassified() + "'" +
            "}";
    }
}
