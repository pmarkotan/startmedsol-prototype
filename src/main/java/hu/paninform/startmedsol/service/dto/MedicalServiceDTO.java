package hu.paninform.startmedsol.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import hu.paninform.startmedsol.domain.enumeration.MedicalServiceUnit;
import hu.paninform.startmedsol.domain.enumeration.TaxRate;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.MedicalService} entity.
 */
public class MedicalServiceDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 20)
    private String code;

    @NotNull
    @Size(max = 100)
    private String name;

    private Integer grossPrice;

    private MedicalServiceUnit unit;

    @Size(max = 20)
    private String statisticalCode;

    private TaxRate taxRate;


    private Long praxisId;

    private Long providerId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(Integer grossPrice) {
        this.grossPrice = grossPrice;
    }

    public MedicalServiceUnit getUnit() {
        return unit;
    }

    public void setUnit(MedicalServiceUnit unit) {
        this.unit = unit;
    }

    public String getStatisticalCode() {
        return statisticalCode;
    }

    public void setStatisticalCode(String statisticalCode) {
        this.statisticalCode = statisticalCode;
    }

    public TaxRate getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(TaxRate taxRate) {
        this.taxRate = taxRate;
    }

    public Long getPraxisId() {
        return praxisId;
    }

    public void setPraxisId(Long praxisId) {
        this.praxisId = praxisId;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MedicalServiceDTO)) {
            return false;
        }

        return id != null && id.equals(((MedicalServiceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MedicalServiceDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", grossPrice=" + getGrossPrice() +
            ", unit='" + getUnit() + "'" +
            ", statisticalCode='" + getStatisticalCode() + "'" +
            ", taxRate='" + getTaxRate() + "'" +
            ", praxisId=" + getPraxisId() +
            ", providerId=" + getProviderId() +
            "}";
    }
}
