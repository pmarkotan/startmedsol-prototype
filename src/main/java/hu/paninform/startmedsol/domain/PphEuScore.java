package hu.paninform.startmedsol.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import hu.paninform.startmedsol.domain.enumeration.SubsidyCategory;

import hu.paninform.startmedsol.domain.enumeration.IndicationType;

/**
 * A PphEuScore.
 */
@Entity
@Table(name = "pph_eu_score")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PphEuScore implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "external_id")
    private Integer externalId;

    @Enumerated(EnumType.STRING)
    @Column(name = "subsidy_category")
    private SubsidyCategory subsidyCategory;

    @Column(name = "indication_code_1")
    private Integer indicationCode1;

    @Size(max = 32)
    @Column(name = "indication_code_2", length = 32)
    private String indicationCode2;

    @Enumerated(EnumType.STRING)
    @Column(name = "indication_type")
    private IndicationType indicationType;

    @Size(max = 4000)
    @Column(name = "prescr_comment", length = 4000)
    private String prescrComment;

    @Size(max = 4000)
    @Column(name = "comment", length = 4000)
    private String comment;

    @NotNull
    @Column(name = "active_pupha_data", nullable = false)
    private Boolean activePuphaData;

    @OneToMany(mappedBy = "euScore")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PphEuIndication> euIndications = new HashSet<>();

    @OneToMany(mappedBy = "euScore")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PphQualificEuScoreLink> qualificEuScoreLinks = new HashSet<>();

    @ManyToMany(mappedBy = "euScores")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<PphMedicine> medicines = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getExternalId() {
        return externalId;
    }

    public PphEuScore externalId(Integer externalId) {
        this.externalId = externalId;
        return this;
    }

    public void setExternalId(Integer externalId) {
        this.externalId = externalId;
    }

    public SubsidyCategory getSubsidyCategory() {
        return subsidyCategory;
    }

    public PphEuScore subsidyCategory(SubsidyCategory subsidyCategory) {
        this.subsidyCategory = subsidyCategory;
        return this;
    }

    public void setSubsidyCategory(SubsidyCategory subsidyCategory) {
        this.subsidyCategory = subsidyCategory;
    }

    public Integer getIndicationCode1() {
        return indicationCode1;
    }

    public PphEuScore indicationCode1(Integer indicationCode1) {
        this.indicationCode1 = indicationCode1;
        return this;
    }

    public void setIndicationCode1(Integer indicationCode1) {
        this.indicationCode1 = indicationCode1;
    }

    public String getIndicationCode2() {
        return indicationCode2;
    }

    public PphEuScore indicationCode2(String indicationCode2) {
        this.indicationCode2 = indicationCode2;
        return this;
    }

    public void setIndicationCode2(String indicationCode2) {
        this.indicationCode2 = indicationCode2;
    }

    public IndicationType getIndicationType() {
        return indicationType;
    }

    public PphEuScore indicationType(IndicationType indicationType) {
        this.indicationType = indicationType;
        return this;
    }

    public void setIndicationType(IndicationType indicationType) {
        this.indicationType = indicationType;
    }

    public String getPrescrComment() {
        return prescrComment;
    }

    public PphEuScore prescrComment(String prescrComment) {
        this.prescrComment = prescrComment;
        return this;
    }

    public void setPrescrComment(String prescrComment) {
        this.prescrComment = prescrComment;
    }

    public String getComment() {
        return comment;
    }

    public PphEuScore comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean isActivePuphaData() {
        return activePuphaData;
    }

    public PphEuScore activePuphaData(Boolean activePuphaData) {
        this.activePuphaData = activePuphaData;
        return this;
    }

    public void setActivePuphaData(Boolean activePuphaData) {
        this.activePuphaData = activePuphaData;
    }

    public Set<PphEuIndication> getEuIndications() {
        return euIndications;
    }

    public PphEuScore euIndications(Set<PphEuIndication> pphEuIndications) {
        this.euIndications = pphEuIndications;
        return this;
    }

    public PphEuScore addEuIndication(PphEuIndication pphEuIndication) {
        this.euIndications.add(pphEuIndication);
        pphEuIndication.setEuScore(this);
        return this;
    }

    public PphEuScore removeEuIndication(PphEuIndication pphEuIndication) {
        this.euIndications.remove(pphEuIndication);
        pphEuIndication.setEuScore(null);
        return this;
    }

    public void setEuIndications(Set<PphEuIndication> pphEuIndications) {
        this.euIndications = pphEuIndications;
    }

    public Set<PphQualificEuScoreLink> getQualificEuScoreLinks() {
        return qualificEuScoreLinks;
    }

    public PphEuScore qualificEuScoreLinks(Set<PphQualificEuScoreLink> pphQualificEuScoreLinks) {
        this.qualificEuScoreLinks = pphQualificEuScoreLinks;
        return this;
    }

    public PphEuScore addQualificEuScoreLink(PphQualificEuScoreLink pphQualificEuScoreLink) {
        this.qualificEuScoreLinks.add(pphQualificEuScoreLink);
        pphQualificEuScoreLink.setEuScore(this);
        return this;
    }

    public PphEuScore removeQualificEuScoreLink(PphQualificEuScoreLink pphQualificEuScoreLink) {
        this.qualificEuScoreLinks.remove(pphQualificEuScoreLink);
        pphQualificEuScoreLink.setEuScore(null);
        return this;
    }

    public void setQualificEuScoreLinks(Set<PphQualificEuScoreLink> pphQualificEuScoreLinks) {
        this.qualificEuScoreLinks = pphQualificEuScoreLinks;
    }

    public Set<PphMedicine> getMedicines() {
        return medicines;
    }

    public PphEuScore medicines(Set<PphMedicine> pphMedicines) {
        this.medicines = pphMedicines;
        return this;
    }

    public PphEuScore addMedicine(PphMedicine pphMedicine) {
        this.medicines.add(pphMedicine);
        pphMedicine.getEuScores().add(this);
        return this;
    }

    public PphEuScore removeMedicine(PphMedicine pphMedicine) {
        this.medicines.remove(pphMedicine);
        pphMedicine.getEuScores().remove(this);
        return this;
    }

    public void setMedicines(Set<PphMedicine> pphMedicines) {
        this.medicines = pphMedicines;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PphEuScore)) {
            return false;
        }
        return id != null && id.equals(((PphEuScore) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PphEuScore{" +
            "id=" + getId() +
            ", externalId=" + getExternalId() +
            ", subsidyCategory='" + getSubsidyCategory() + "'" +
            ", indicationCode1=" + getIndicationCode1() +
            ", indicationCode2='" + getIndicationCode2() + "'" +
            ", indicationType='" + getIndicationType() + "'" +
            ", prescrComment='" + getPrescrComment() + "'" +
            ", comment='" + getComment() + "'" +
            ", activePuphaData='" + isActivePuphaData() + "'" +
            "}";
    }
}
