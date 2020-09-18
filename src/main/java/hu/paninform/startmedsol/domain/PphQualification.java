package hu.paninform.startmedsol.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A PphQualification.
 */
@Entity
@Table(name = "pph_qualification")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PphQualification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "external_id")
    private Integer externalId;

    @Column(name = "code")
    private Integer code;

    @Size(max = 250)
    @Column(name = "name", length = 250)
    private String name;

    @Column(name = "new_code")
    private Integer newCode;

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

    public PphQualification externalId(Integer externalId) {
        this.externalId = externalId;
        return this;
    }

    public void setExternalId(Integer externalId) {
        this.externalId = externalId;
    }

    public Integer getCode() {
        return code;
    }

    public PphQualification code(Integer code) {
        this.code = code;
        return this;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public PphQualification name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNewCode() {
        return newCode;
    }

    public PphQualification newCode(Integer newCode) {
        this.newCode = newCode;
        return this;
    }

    public void setNewCode(Integer newCode) {
        this.newCode = newCode;
    }

    public Boolean isActivePuphaData() {
        return activePuphaData;
    }

    public PphQualification activePuphaData(Boolean activePuphaData) {
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
        if (!(o instanceof PphQualification)) {
            return false;
        }
        return id != null && id.equals(((PphQualification) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PphQualification{" +
            "id=" + getId() +
            ", externalId=" + getExternalId() +
            ", code=" + getCode() +
            ", name='" + getName() + "'" +
            ", newCode=" + getNewCode() +
            ", activePuphaData='" + isActivePuphaData() + "'" +
            "}";
    }
}
