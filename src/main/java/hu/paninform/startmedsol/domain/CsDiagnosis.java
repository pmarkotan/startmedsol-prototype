package hu.paninform.startmedsol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import hu.paninform.startmedsol.domain.enumeration.DgSex;

/**
 * A CsDiagnosis.
 */
@Entity
@Table(name = "cs_diagnosis")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CsDiagnosis implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 10)
    @Column(name = "code", length = 10, nullable = false)
    private String code;

    @NotNull
    @Column(name = "report", nullable = false)
    private Boolean report;

    @NotNull
    @Column(name = "descr", nullable = false)
    private String descr;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    private DgSex sex;

    @Min(value = 0)
    @Max(value = 99)
    @Column(name = "age_min")
    private Integer ageMin;

    @Min(value = 0)
    @Max(value = 99)
    @Column(name = "age_max")
    private Integer ageMax;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "csDiagnoses", allowSetters = true)
    private Validity validity;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public CsDiagnosis code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean isReport() {
        return report;
    }

    public CsDiagnosis report(Boolean report) {
        this.report = report;
        return this;
    }

    public void setReport(Boolean report) {
        this.report = report;
    }

    public String getDescr() {
        return descr;
    }

    public CsDiagnosis descr(String descr) {
        this.descr = descr;
        return this;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public DgSex getSex() {
        return sex;
    }

    public CsDiagnosis sex(DgSex sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(DgSex sex) {
        this.sex = sex;
    }

    public Integer getAgeMin() {
        return ageMin;
    }

    public CsDiagnosis ageMin(Integer ageMin) {
        this.ageMin = ageMin;
        return this;
    }

    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }

    public Integer getAgeMax() {
        return ageMax;
    }

    public CsDiagnosis ageMax(Integer ageMax) {
        this.ageMax = ageMax;
        return this;
    }

    public void setAgeMax(Integer ageMax) {
        this.ageMax = ageMax;
    }

    public Validity getValidity() {
        return validity;
    }

    public CsDiagnosis validity(Validity validity) {
        this.validity = validity;
        return this;
    }

    public void setValidity(Validity validity) {
        this.validity = validity;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CsDiagnosis)) {
            return false;
        }
        return id != null && id.equals(((CsDiagnosis) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CsDiagnosis{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", report='" + isReport() + "'" +
            ", descr='" + getDescr() + "'" +
            ", sex='" + getSex() + "'" +
            ", ageMin=" + getAgeMin() +
            ", ageMax=" + getAgeMax() +
            "}";
    }
}
