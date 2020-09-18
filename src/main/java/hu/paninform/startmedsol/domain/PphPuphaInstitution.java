package hu.paninform.startmedsol.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import hu.paninform.startmedsol.domain.enumeration.InstitutionType;

/**
 * A PphPuphaInstitution.
 */
@Entity
@Table(name = "pph_pupha_institution")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PphPuphaInstitution implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "external_id")
    private Integer externalId;

    @Size(max = 100)
    @Column(name = "county", length = 100)
    private String county;

    @Size(max = 20)
    @Column(name = "code", length = 20)
    private String code;

    @Size(max = 250)
    @Column(name = "name", length = 250)
    private String name;

    @Size(max = 20)
    @Column(name = "gyfkod", length = 20)
    private String gyfkod;

    @Size(max = 250)
    @Column(name = "unit_name", length = 250)
    private String unitName;

    @Size(max = 128)
    @Column(name = "doctor_name", length = 128)
    private String doctorName;

    @Size(max = 6)
    @Column(name = "registration_no", length = 6)
    private String registrationNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "institution_type")
    private InstitutionType institutionType;

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

    public PphPuphaInstitution externalId(Integer externalId) {
        this.externalId = externalId;
        return this;
    }

    public void setExternalId(Integer externalId) {
        this.externalId = externalId;
    }

    public String getCounty() {
        return county;
    }

    public PphPuphaInstitution county(String county) {
        this.county = county;
        return this;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCode() {
        return code;
    }

    public PphPuphaInstitution code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public PphPuphaInstitution name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGyfkod() {
        return gyfkod;
    }

    public PphPuphaInstitution gyfkod(String gyfkod) {
        this.gyfkod = gyfkod;
        return this;
    }

    public void setGyfkod(String gyfkod) {
        this.gyfkod = gyfkod;
    }

    public String getUnitName() {
        return unitName;
    }

    public PphPuphaInstitution unitName(String unitName) {
        this.unitName = unitName;
        return this;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public PphPuphaInstitution doctorName(String doctorName) {
        this.doctorName = doctorName;
        return this;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public PphPuphaInstitution registrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
        return this;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public InstitutionType getInstitutionType() {
        return institutionType;
    }

    public PphPuphaInstitution institutionType(InstitutionType institutionType) {
        this.institutionType = institutionType;
        return this;
    }

    public void setInstitutionType(InstitutionType institutionType) {
        this.institutionType = institutionType;
    }

    public Boolean isActivePuphaData() {
        return activePuphaData;
    }

    public PphPuphaInstitution activePuphaData(Boolean activePuphaData) {
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
        if (!(o instanceof PphPuphaInstitution)) {
            return false;
        }
        return id != null && id.equals(((PphPuphaInstitution) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PphPuphaInstitution{" +
            "id=" + getId() +
            ", externalId=" + getExternalId() +
            ", county='" + getCounty() + "'" +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", gyfkod='" + getGyfkod() + "'" +
            ", unitName='" + getUnitName() + "'" +
            ", doctorName='" + getDoctorName() + "'" +
            ", registrationNo='" + getRegistrationNo() + "'" +
            ", institutionType='" + getInstitutionType() + "'" +
            ", activePuphaData='" + isActivePuphaData() + "'" +
            "}";
    }
}
