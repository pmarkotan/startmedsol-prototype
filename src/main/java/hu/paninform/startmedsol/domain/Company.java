package hu.paninform.startmedsol.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Company.
 */
@Entity
@Table(name = "company")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "registration_number", length = 50, nullable = false)
    private String registrationNumber;

    @NotNull
    @Size(max = 50)
    @Column(name = "workplace_name", length = 50, nullable = false)
    private String workplaceName;

    @NotNull
    @Size(max = 20)
    @Column(name = "workplace_id", length = 20, nullable = false)
    private String workplaceId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Company registrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        return this;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getWorkplaceName() {
        return workplaceName;
    }

    public Company workplaceName(String workplaceName) {
        this.workplaceName = workplaceName;
        return this;
    }

    public void setWorkplaceName(String workplaceName) {
        this.workplaceName = workplaceName;
    }

    public String getWorkplaceId() {
        return workplaceId;
    }

    public Company workplaceId(String workplaceId) {
        this.workplaceId = workplaceId;
        return this;
    }

    public void setWorkplaceId(String workplaceId) {
        this.workplaceId = workplaceId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Company)) {
            return false;
        }
        return id != null && id.equals(((Company) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Company{" +
            "id=" + getId() +
            ", registrationNumber='" + getRegistrationNumber() + "'" +
            ", workplaceName='" + getWorkplaceName() + "'" +
            ", workplaceId='" + getWorkplaceId() + "'" +
            "}";
    }
}
