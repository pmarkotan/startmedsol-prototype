package hu.paninform.startmedsol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import hu.paninform.startmedsol.domain.enumeration.MedicalInvoiceType;

/**
 * A MedicalInvoice.
 */
@Entity
@Table(name = "medical_invoice")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MedicalInvoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "order_number", nullable = false)
    private Integer orderNumber;

    @NotNull
    @Size(max = 100)
    @Column(name = "invoice_number", length = 100, nullable = false)
    private String invoiceNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private MedicalInvoiceType type;

    @NotNull
    @Column(name = "total", nullable = false)
    private Integer total;

    @NotNull
    @Size(max = 100)
    @Column(name = "creator_user", length = 100, nullable = false)
    private String creatorUser;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "medicalInvoices", allowSetters = true)
    private MedicalCase medicalCase;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public MedicalInvoice orderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public MedicalInvoice invoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public MedicalInvoiceType getType() {
        return type;
    }

    public MedicalInvoice type(MedicalInvoiceType type) {
        this.type = type;
        return this;
    }

    public void setType(MedicalInvoiceType type) {
        this.type = type;
    }

    public Integer getTotal() {
        return total;
    }

    public MedicalInvoice total(Integer total) {
        this.total = total;
        return this;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getCreatorUser() {
        return creatorUser;
    }

    public MedicalInvoice creatorUser(String creatorUser) {
        this.creatorUser = creatorUser;
        return this;
    }

    public void setCreatorUser(String creatorUser) {
        this.creatorUser = creatorUser;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public MedicalInvoice createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public MedicalCase getMedicalCase() {
        return medicalCase;
    }

    public MedicalInvoice medicalCase(MedicalCase medicalCase) {
        this.medicalCase = medicalCase;
        return this;
    }

    public void setMedicalCase(MedicalCase medicalCase) {
        this.medicalCase = medicalCase;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MedicalInvoice)) {
            return false;
        }
        return id != null && id.equals(((MedicalInvoice) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MedicalInvoice{" +
            "id=" + getId() +
            ", orderNumber=" + getOrderNumber() +
            ", invoiceNumber='" + getInvoiceNumber() + "'" +
            ", type='" + getType() + "'" +
            ", total=" + getTotal() +
            ", creatorUser='" + getCreatorUser() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            "}";
    }
}
