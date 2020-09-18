package hu.paninform.startmedsol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A CsSenderOrganization.
 */
@Entity
@Table(name = "cs_sender_organization")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CsSenderOrganization implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "county")
    private String county;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "name")
    private String name;

    @NotNull
    @Size(max = 9)
    @Column(name = "financing_number", length = 9, nullable = false)
    private String financingNumber;

    @Column(name = "financing_name")
    private String financingName;

    @NotNull
    @Column(name = "type", nullable = false)
    private String type;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "csSenderOrganizations", allowSetters = true)
    private Validity validity;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCounty() {
        return county;
    }

    public CsSenderOrganization county(String county) {
        this.county = county;
        return this;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getShortName() {
        return shortName;
    }

    public CsSenderOrganization shortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public CsSenderOrganization name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFinancingNumber() {
        return financingNumber;
    }

    public CsSenderOrganization financingNumber(String financingNumber) {
        this.financingNumber = financingNumber;
        return this;
    }

    public void setFinancingNumber(String financingNumber) {
        this.financingNumber = financingNumber;
    }

    public String getFinancingName() {
        return financingName;
    }

    public CsSenderOrganization financingName(String financingName) {
        this.financingName = financingName;
        return this;
    }

    public void setFinancingName(String financingName) {
        this.financingName = financingName;
    }

    public String getType() {
        return type;
    }

    public CsSenderOrganization type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Validity getValidity() {
        return validity;
    }

    public CsSenderOrganization validity(Validity validity) {
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
        if (!(o instanceof CsSenderOrganization)) {
            return false;
        }
        return id != null && id.equals(((CsSenderOrganization) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CsSenderOrganization{" +
            "id=" + getId() +
            ", county='" + getCounty() + "'" +
            ", shortName='" + getShortName() + "'" +
            ", name='" + getName() + "'" +
            ", financingNumber='" + getFinancingNumber() + "'" +
            ", financingName='" + getFinancingName() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
