package hu.paninform.startmedsol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A PphQualificationDoctorAll.
 */
@Entity
@Table(name = "pph_qualification_doctor_all")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PphQualificationDoctorAll implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 16)
    @Column(name = "registration_no", length = 16)
    private String registrationNo;

    @NotNull
    @Column(name = "active_pupha_data", nullable = false)
    private Boolean activePuphaData;

    @ManyToOne
    @JsonIgnoreProperties(value = "pphQualificationDoctorAlls", allowSetters = true)
    private PphQualification qualification;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public PphQualificationDoctorAll registrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
        return this;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public Boolean isActivePuphaData() {
        return activePuphaData;
    }

    public PphQualificationDoctorAll activePuphaData(Boolean activePuphaData) {
        this.activePuphaData = activePuphaData;
        return this;
    }

    public void setActivePuphaData(Boolean activePuphaData) {
        this.activePuphaData = activePuphaData;
    }

    public PphQualification getQualification() {
        return qualification;
    }

    public PphQualificationDoctorAll qualification(PphQualification pphQualification) {
        this.qualification = pphQualification;
        return this;
    }

    public void setQualification(PphQualification pphQualification) {
        this.qualification = pphQualification;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PphQualificationDoctorAll)) {
            return false;
        }
        return id != null && id.equals(((PphQualificationDoctorAll) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PphQualificationDoctorAll{" +
            "id=" + getId() +
            ", registrationNo='" + getRegistrationNo() + "'" +
            ", activePuphaData='" + isActivePuphaData() + "'" +
            "}";
    }
}
