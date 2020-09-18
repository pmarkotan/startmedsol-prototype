package hu.paninform.startmedsol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import hu.paninform.startmedsol.domain.enumeration.SubsidyCategory;

import hu.paninform.startmedsol.domain.enumeration.SubsidyType;

/**
 * A PphSubsidy.
 */
@Entity
@Table(name = "pph_subsidy")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PphSubsidy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "percent")
    private Double percent;

    @Column(name = "reference_daily_cost")
    private Double referenceDailyCost;

    @Column(name = "net_subsidy")
    private Double netSubsidy;

    @Column(name = "gross_subsidy")
    private Double grossSubsidy;

    @Column(name = "consumer_price")
    private Double consumerPrice;

    @Column(name = "daily_cost_on_cons_price")
    private Double dailyCostOnConsPrice;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "quantity_allowed")
    private Integer quantityAllowed;

    @Size(max = 128)
    @Column(name = "euem_pontok", length = 128)
    private String euemPontok;

    @Enumerated(EnumType.STRING)
    @Column(name = "subsidy_category")
    private SubsidyCategory subsidyCategory;

    @Enumerated(EnumType.STRING)
    @Column(name = "subsidy_type")
    private SubsidyType subsidyType;

    @NotNull
    @Column(name = "active_pupha_data", nullable = false)
    private Boolean activePuphaData;

    @ManyToOne
    @JsonIgnoreProperties(value = "subSidies", allowSetters = true)
    private PphMedicine medicine;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPercent() {
        return percent;
    }

    public PphSubsidy percent(Double percent) {
        this.percent = percent;
        return this;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Double getReferenceDailyCost() {
        return referenceDailyCost;
    }

    public PphSubsidy referenceDailyCost(Double referenceDailyCost) {
        this.referenceDailyCost = referenceDailyCost;
        return this;
    }

    public void setReferenceDailyCost(Double referenceDailyCost) {
        this.referenceDailyCost = referenceDailyCost;
    }

    public Double getNetSubsidy() {
        return netSubsidy;
    }

    public PphSubsidy netSubsidy(Double netSubsidy) {
        this.netSubsidy = netSubsidy;
        return this;
    }

    public void setNetSubsidy(Double netSubsidy) {
        this.netSubsidy = netSubsidy;
    }

    public Double getGrossSubsidy() {
        return grossSubsidy;
    }

    public PphSubsidy grossSubsidy(Double grossSubsidy) {
        this.grossSubsidy = grossSubsidy;
        return this;
    }

    public void setGrossSubsidy(Double grossSubsidy) {
        this.grossSubsidy = grossSubsidy;
    }

    public Double getConsumerPrice() {
        return consumerPrice;
    }

    public PphSubsidy consumerPrice(Double consumerPrice) {
        this.consumerPrice = consumerPrice;
        return this;
    }

    public void setConsumerPrice(Double consumerPrice) {
        this.consumerPrice = consumerPrice;
    }

    public Double getDailyCostOnConsPrice() {
        return dailyCostOnConsPrice;
    }

    public PphSubsidy dailyCostOnConsPrice(Double dailyCostOnConsPrice) {
        this.dailyCostOnConsPrice = dailyCostOnConsPrice;
        return this;
    }

    public void setDailyCostOnConsPrice(Double dailyCostOnConsPrice) {
        this.dailyCostOnConsPrice = dailyCostOnConsPrice;
    }

    public Integer getDuration() {
        return duration;
    }

    public PphSubsidy duration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getQuantityAllowed() {
        return quantityAllowed;
    }

    public PphSubsidy quantityAllowed(Integer quantityAllowed) {
        this.quantityAllowed = quantityAllowed;
        return this;
    }

    public void setQuantityAllowed(Integer quantityAllowed) {
        this.quantityAllowed = quantityAllowed;
    }

    public String getEuemPontok() {
        return euemPontok;
    }

    public PphSubsidy euemPontok(String euemPontok) {
        this.euemPontok = euemPontok;
        return this;
    }

    public void setEuemPontok(String euemPontok) {
        this.euemPontok = euemPontok;
    }

    public SubsidyCategory getSubsidyCategory() {
        return subsidyCategory;
    }

    public PphSubsidy subsidyCategory(SubsidyCategory subsidyCategory) {
        this.subsidyCategory = subsidyCategory;
        return this;
    }

    public void setSubsidyCategory(SubsidyCategory subsidyCategory) {
        this.subsidyCategory = subsidyCategory;
    }

    public SubsidyType getSubsidyType() {
        return subsidyType;
    }

    public PphSubsidy subsidyType(SubsidyType subsidyType) {
        this.subsidyType = subsidyType;
        return this;
    }

    public void setSubsidyType(SubsidyType subsidyType) {
        this.subsidyType = subsidyType;
    }

    public Boolean isActivePuphaData() {
        return activePuphaData;
    }

    public PphSubsidy activePuphaData(Boolean activePuphaData) {
        this.activePuphaData = activePuphaData;
        return this;
    }

    public void setActivePuphaData(Boolean activePuphaData) {
        this.activePuphaData = activePuphaData;
    }

    public PphMedicine getMedicine() {
        return medicine;
    }

    public PphSubsidy medicine(PphMedicine pphMedicine) {
        this.medicine = pphMedicine;
        return this;
    }

    public void setMedicine(PphMedicine pphMedicine) {
        this.medicine = pphMedicine;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PphSubsidy)) {
            return false;
        }
        return id != null && id.equals(((PphSubsidy) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PphSubsidy{" +
            "id=" + getId() +
            ", percent=" + getPercent() +
            ", referenceDailyCost=" + getReferenceDailyCost() +
            ", netSubsidy=" + getNetSubsidy() +
            ", grossSubsidy=" + getGrossSubsidy() +
            ", consumerPrice=" + getConsumerPrice() +
            ", dailyCostOnConsPrice=" + getDailyCostOnConsPrice() +
            ", duration=" + getDuration() +
            ", quantityAllowed=" + getQuantityAllowed() +
            ", euemPontok='" + getEuemPontok() + "'" +
            ", subsidyCategory='" + getSubsidyCategory() + "'" +
            ", subsidyType='" + getSubsidyType() + "'" +
            ", activePuphaData='" + isActivePuphaData() + "'" +
            "}";
    }
}
