package hu.paninform.startmedsol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import hu.paninform.startmedsol.domain.enumeration.PraxisStatus;

/**
 * A Praxis.
 */
@Entity
@Table(name = "praxis")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Praxis implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotNull
    @Size(max = 9)
    @Pattern(regexp = "^[A-Z0-9]+$")
    @Column(name = "department_id", length = 9, nullable = false)
    private String departmentId;

    @NotNull
    @Size(max = 4)
    @Pattern(regexp = "^[A-Z0-9]+$")
    @Column(name = "profession_code", length = 4, nullable = false)
    private String professionCode;

    @NotNull
    @Size(max = 50)
    @Column(name = "profession_name", length = 50, nullable = false)
    private String professionName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PraxisStatus status;

    @NotNull
    @Size(max = 16)
    @Pattern(regexp = "^\\++\\d+$")
    @Column(name = "appointment_phone", length = 16, nullable = false)
    private String appointmentPhone;

    @NotNull
    @Min(value = 1)
    @Max(value = 99999999)
    @Column(name = "treatment_logbook_number", nullable = false)
    private Integer treatmentLogbookNumber;

    @OneToOne
    @JoinColumn(unique = true)
    private Address officeAddress;

    @ManyToOne
    @JsonIgnoreProperties(value = "praxes", allowSetters = true)
    private Provider provider;

    @ManyToOne
    @JsonIgnoreProperties(value = "praxes", allowSetters = true)
    private Employee doctor;

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

    public Praxis name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public Praxis departmentId(String departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getProfessionCode() {
        return professionCode;
    }

    public Praxis professionCode(String professionCode) {
        this.professionCode = professionCode;
        return this;
    }

    public void setProfessionCode(String professionCode) {
        this.professionCode = professionCode;
    }

    public String getProfessionName() {
        return professionName;
    }

    public Praxis professionName(String professionName) {
        this.professionName = professionName;
        return this;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public PraxisStatus getStatus() {
        return status;
    }

    public Praxis status(PraxisStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(PraxisStatus status) {
        this.status = status;
    }

    public String getAppointmentPhone() {
        return appointmentPhone;
    }

    public Praxis appointmentPhone(String appointmentPhone) {
        this.appointmentPhone = appointmentPhone;
        return this;
    }

    public void setAppointmentPhone(String appointmentPhone) {
        this.appointmentPhone = appointmentPhone;
    }

    public Integer getTreatmentLogbookNumber() {
        return treatmentLogbookNumber;
    }

    public Praxis treatmentLogbookNumber(Integer treatmentLogbookNumber) {
        this.treatmentLogbookNumber = treatmentLogbookNumber;
        return this;
    }

    public void setTreatmentLogbookNumber(Integer treatmentLogbookNumber) {
        this.treatmentLogbookNumber = treatmentLogbookNumber;
    }

    public Address getOfficeAddress() {
        return officeAddress;
    }

    public Praxis officeAddress(Address address) {
        this.officeAddress = address;
        return this;
    }

    public void setOfficeAddress(Address address) {
        this.officeAddress = address;
    }

    public Provider getProvider() {
        return provider;
    }

    public Praxis provider(Provider provider) {
        this.provider = provider;
        return this;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Employee getDoctor() {
        return doctor;
    }

    public Praxis doctor(Employee employee) {
        this.doctor = employee;
        return this;
    }

    public void setDoctor(Employee employee) {
        this.doctor = employee;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Praxis)) {
            return false;
        }
        return id != null && id.equals(((Praxis) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Praxis{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", departmentId='" + getDepartmentId() + "'" +
            ", professionCode='" + getProfessionCode() + "'" +
            ", professionName='" + getProfessionName() + "'" +
            ", status='" + getStatus() + "'" +
            ", appointmentPhone='" + getAppointmentPhone() + "'" +
            ", treatmentLogbookNumber=" + getTreatmentLogbookNumber() +
            "}";
    }
}
