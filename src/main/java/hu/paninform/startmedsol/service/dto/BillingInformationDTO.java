package hu.paninform.startmedsol.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.BillingInformation} entity.
 */
public class BillingInformationDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 30)
    private String name;

    @Size(max = 13)
    @Pattern(regexp = "^\\d{8}-\\d-\\d{2}$")
    private String taxnumber;


    private Long billingAddressId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxnumber() {
        return taxnumber;
    }

    public void setTaxnumber(String taxnumber) {
        this.taxnumber = taxnumber;
    }

    public Long getBillingAddressId() {
        return billingAddressId;
    }

    public void setBillingAddressId(Long addressId) {
        this.billingAddressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BillingInformationDTO)) {
            return false;
        }

        return id != null && id.equals(((BillingInformationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BillingInformationDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", taxnumber='" + getTaxnumber() + "'" +
            ", billingAddressId=" + getBillingAddressId() +
            "}";
    }
}
