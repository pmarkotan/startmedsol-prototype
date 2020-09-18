package hu.paninform.startmedsol.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import hu.paninform.startmedsol.domain.enumeration.EmployeeType;

import hu.paninform.startmedsol.domain.enumeration.EesztLoginType;

/**
 * A Employee.
 */
@Entity
@Table(name = "employee")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "emlpoyee_type", nullable = false)
    private EmployeeType emlpoyeeType;

    @NotNull
    @Size(max = 10)
    @Column(name = "identifier", length = 10, nullable = false)
    private String identifier;

    @Size(max = 10)
    @Column(name = "eeszt_identifier", length = 10)
    private String eesztIdentifier;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "eeszt_login_type", nullable = false)
    private EesztLoginType eesztLoginType;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private PersonalData personalData;

    @OneToMany(mappedBy = "doctor")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Praxis> praxes = new HashSet<>();

    @ManyToMany(mappedBy = "employees")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Provider> providers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeType getEmlpoyeeType() {
        return emlpoyeeType;
    }

    public Employee emlpoyeeType(EmployeeType emlpoyeeType) {
        this.emlpoyeeType = emlpoyeeType;
        return this;
    }

    public void setEmlpoyeeType(EmployeeType emlpoyeeType) {
        this.emlpoyeeType = emlpoyeeType;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Employee identifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getEesztIdentifier() {
        return eesztIdentifier;
    }

    public Employee eesztIdentifier(String eesztIdentifier) {
        this.eesztIdentifier = eesztIdentifier;
        return this;
    }

    public void setEesztIdentifier(String eesztIdentifier) {
        this.eesztIdentifier = eesztIdentifier;
    }

    public EesztLoginType getEesztLoginType() {
        return eesztLoginType;
    }

    public Employee eesztLoginType(EesztLoginType eesztLoginType) {
        this.eesztLoginType = eesztLoginType;
        return this;
    }

    public void setEesztLoginType(EesztLoginType eesztLoginType) {
        this.eesztLoginType = eesztLoginType;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public Employee personalData(PersonalData personalData) {
        this.personalData = personalData;
        return this;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public Set<Praxis> getPraxes() {
        return praxes;
    }

    public Employee praxes(Set<Praxis> praxes) {
        this.praxes = praxes;
        return this;
    }

    public Employee addPraxis(Praxis praxis) {
        this.praxes.add(praxis);
        praxis.setDoctor(this);
        return this;
    }

    public Employee removePraxis(Praxis praxis) {
        this.praxes.remove(praxis);
        praxis.setDoctor(null);
        return this;
    }

    public void setPraxes(Set<Praxis> praxes) {
        this.praxes = praxes;
    }

    public Set<Provider> getProviders() {
        return providers;
    }

    public Employee providers(Set<Provider> providers) {
        this.providers = providers;
        return this;
    }

    public Employee addProvider(Provider provider) {
        this.providers.add(provider);
        provider.getEmployees().add(this);
        return this;
    }

    public Employee removeProvider(Provider provider) {
        this.providers.remove(provider);
        provider.getEmployees().remove(this);
        return this;
    }

    public void setProviders(Set<Provider> providers) {
        this.providers = providers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        return id != null && id.equals(((Employee) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Employee{" +
            "id=" + getId() +
            ", emlpoyeeType='" + getEmlpoyeeType() + "'" +
            ", identifier='" + getIdentifier() + "'" +
            ", eesztIdentifier='" + getEesztIdentifier() + "'" +
            ", eesztLoginType='" + getEesztLoginType() + "'" +
            "}";
    }
}
