package hu.paninform.startmedsol.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A BillingInformation.
 */
@Entity
@Table(name = "billing_information")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BillingInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Size(max = 13)
    @Pattern(regexp = "^\\d{8}-\\d-\\d{2}$")
    @Column(name = "taxnumber", length = 13)
    private String taxnumber;

    @OneToOne
    @JoinColumn(unique = true)
    private Address billingAddress;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public BillingInformation name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxnumber() {
        return taxnumber;
    }

    public BillingInformation taxnumber(String taxnumber) {
        this.taxnumber = taxnumber;
        return this;
    }

    public void setTaxnumber(String taxnumber) {
        this.taxnumber = taxnumber;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public BillingInformation billingAddress(Address address) {
        this.billingAddress = address;
        return this;
    }

    public void setBillingAddress(Address address) {
        this.billingAddress = address;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BillingInformation)) {
            return false;
        }
        return id != null && id.equals(((BillingInformation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BillingInformation{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", taxnumber='" + getTaxnumber() + "'" +
            "}";
    }
}
