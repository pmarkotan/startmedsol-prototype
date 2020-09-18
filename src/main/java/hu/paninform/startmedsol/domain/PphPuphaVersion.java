package hu.paninform.startmedsol.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import hu.paninform.startmedsol.domain.enumeration.PuphaDataType;

import hu.paninform.startmedsol.domain.enumeration.PuphaDataStatus;

/**
 * A PphPuphaVersion.
 */
@Entity
@Table(name = "pph_pupha_version")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PphPuphaVersion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "external_id")
    private Integer externalId;

    @Column(name = "valid_from")
    private Instant validFrom;

    @Column(name = "load_date")
    private Instant loadDate;

    @Column(name = "file_version")
    private Integer fileVersion;

    @Column(name = "modification_date")
    private Instant modificationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "pupha_data_type")
    private PuphaDataType puphaDataType;

    @Enumerated(EnumType.STRING)
    @Column(name = "pupha_data_status")
    private PuphaDataStatus puphaDataStatus;

    @NotNull
    @Column(name = "active_pupha_data", nullable = false)
    private Boolean activePuphaData;

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

    public PphPuphaVersion externalId(Integer externalId) {
        this.externalId = externalId;
        return this;
    }

    public void setExternalId(Integer externalId) {
        this.externalId = externalId;
    }

    public Instant getValidFrom() {
        return validFrom;
    }

    public PphPuphaVersion validFrom(Instant validFrom) {
        this.validFrom = validFrom;
        return this;
    }

    public void setValidFrom(Instant validFrom) {
        this.validFrom = validFrom;
    }

    public Instant getLoadDate() {
        return loadDate;
    }

    public PphPuphaVersion loadDate(Instant loadDate) {
        this.loadDate = loadDate;
        return this;
    }

    public void setLoadDate(Instant loadDate) {
        this.loadDate = loadDate;
    }

    public Integer getFileVersion() {
        return fileVersion;
    }

    public PphPuphaVersion fileVersion(Integer fileVersion) {
        this.fileVersion = fileVersion;
        return this;
    }

    public void setFileVersion(Integer fileVersion) {
        this.fileVersion = fileVersion;
    }

    public Instant getModificationDate() {
        return modificationDate;
    }

    public PphPuphaVersion modificationDate(Instant modificationDate) {
        this.modificationDate = modificationDate;
        return this;
    }

    public void setModificationDate(Instant modificationDate) {
        this.modificationDate = modificationDate;
    }

    public PuphaDataType getPuphaDataType() {
        return puphaDataType;
    }

    public PphPuphaVersion puphaDataType(PuphaDataType puphaDataType) {
        this.puphaDataType = puphaDataType;
        return this;
    }

    public void setPuphaDataType(PuphaDataType puphaDataType) {
        this.puphaDataType = puphaDataType;
    }

    public PuphaDataStatus getPuphaDataStatus() {
        return puphaDataStatus;
    }

    public PphPuphaVersion puphaDataStatus(PuphaDataStatus puphaDataStatus) {
        this.puphaDataStatus = puphaDataStatus;
        return this;
    }

    public void setPuphaDataStatus(PuphaDataStatus puphaDataStatus) {
        this.puphaDataStatus = puphaDataStatus;
    }

    public Boolean isActivePuphaData() {
        return activePuphaData;
    }

    public PphPuphaVersion activePuphaData(Boolean activePuphaData) {
        this.activePuphaData = activePuphaData;
        return this;
    }

    public void setActivePuphaData(Boolean activePuphaData) {
        this.activePuphaData = activePuphaData;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PphPuphaVersion)) {
            return false;
        }
        return id != null && id.equals(((PphPuphaVersion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PphPuphaVersion{" +
            "id=" + getId() +
            ", externalId=" + getExternalId() +
            ", validFrom='" + getValidFrom() + "'" +
            ", loadDate='" + getLoadDate() + "'" +
            ", fileVersion=" + getFileVersion() +
            ", modificationDate='" + getModificationDate() + "'" +
            ", puphaDataType='" + getPuphaDataType() + "'" +
            ", puphaDataStatus='" + getPuphaDataStatus() + "'" +
            ", activePuphaData='" + isActivePuphaData() + "'" +
            "}";
    }
}
