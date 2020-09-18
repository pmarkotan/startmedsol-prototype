package hu.paninform.startmedsol.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import hu.paninform.startmedsol.domain.enumeration.MedicalInvoiceType;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.MedicalInvoice} entity.
 */
public class MedicalInvoiceDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer orderNumber;

    @NotNull
    @Size(max = 100)
    private String invoiceNumber;

    @NotNull
    private MedicalInvoiceType type;

    @NotNull
    private Integer total;

    @NotNull
    @Size(max = 100)
    private String creatorUser;

    @NotNull
    private Instant createdAt;


    private Long medicalCaseId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public MedicalInvoiceType getType() {
        return type;
    }

    public void setType(MedicalInvoiceType type) {
        this.type = type;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(String creatorUser) {
        this.creatorUser = creatorUser;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Long getMedicalCaseId() {
        return medicalCaseId;
    }

    public void setMedicalCaseId(Long medicalCaseId) {
        this.medicalCaseId = medicalCaseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MedicalInvoiceDTO)) {
            return false;
        }

        return id != null && id.equals(((MedicalInvoiceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MedicalInvoiceDTO{" +
            "id=" + getId() +
            ", orderNumber=" + getOrderNumber() +
            ", invoiceNumber='" + getInvoiceNumber() + "'" +
            ", type='" + getType() + "'" +
            ", total=" + getTotal() +
            ", creatorUser='" + getCreatorUser() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", medicalCaseId=" + getMedicalCaseId() +
            "}";
    }
}
