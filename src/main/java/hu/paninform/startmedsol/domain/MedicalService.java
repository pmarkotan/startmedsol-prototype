package hu.paninform.startmedsol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import hu.paninform.startmedsol.domain.enumeration.MedicalServiceUnit;

import hu.paninform.startmedsol.domain.enumeration.TaxRate;

/**
 * A MedicalService.
 */
@Entity
@Table(name = "medical_service")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MedicalService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "code", length = 20, nullable = false)
    private String code;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "gross_price")
    private Integer grossPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit")
    private MedicalServiceUnit unit;

    @Size(max = 20)
    @Column(name = "statistical_code", length = 20)
    private String statisticalCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "tax_rate")
    private TaxRate taxRate;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "medicalServices", allowSetters = true)
    private Praxis praxis;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "medicalServices", allowSetters = true)
    private Provider provider;

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

    public MedicalService code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public MedicalService name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrossPrice() {
        return grossPrice;
    }

    public MedicalService grossPrice(Integer grossPrice) {
        this.grossPrice = grossPrice;
        return this;
    }

    public void setGrossPrice(Integer grossPrice) {
        this.grossPrice = grossPrice;
    }

    public MedicalServiceUnit getUnit() {
        return unit;
    }

    public MedicalService unit(MedicalServiceUnit unit) {
        this.unit = unit;
        return this;
    }

    public void setUnit(MedicalServiceUnit unit) {
        this.unit = unit;
    }

    public String getStatisticalCode() {
        return statisticalCode;
    }

    public MedicalService statisticalCode(String statisticalCode) {
        this.statisticalCode = statisticalCode;
        return this;
    }

    public void setStatisticalCode(String statisticalCode) {
        this.statisticalCode = statisticalCode;
    }

    public TaxRate getTaxRate() {
        return taxRate;
    }

    public MedicalService taxRate(TaxRate taxRate) {
        this.taxRate = taxRate;
        return this;
    }

    public void setTaxRate(TaxRate taxRate) {
        this.taxRate = taxRate;
    }

    public Praxis getPraxis() {
        return praxis;
    }

    public MedicalService praxis(Praxis praxis) {
        this.praxis = praxis;
        return this;
    }

    public void setPraxis(Praxis praxis) {
        this.praxis = praxis;
    }

    public Provider getProvider() {
        return provider;
    }

    public MedicalService provider(Provider provider) {
        this.provider = provider;
        return this;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MedicalService)) {
            return false;
        }
        return id != null && id.equals(((MedicalService) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MedicalService{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", grossPrice=" + getGrossPrice() +
            ", unit='" + getUnit() + "'" +
            ", statisticalCode='" + getStatisticalCode() + "'" +
            ", taxRate='" + getTaxRate() + "'" +
            "}";
    }
}
