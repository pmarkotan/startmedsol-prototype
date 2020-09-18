package hu.paninform.startmedsol.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import hu.paninform.startmedsol.domain.enumeration.MedicalCaseStatus;

import hu.paninform.startmedsol.domain.enumeration.AttendanceType;

/**
 * A MedicalCase.
 */
@Entity
@Table(name = "medical_case")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MedicalCase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @NotNull
    @Column(name = "confidental", nullable = false)
    private Boolean confidental;

    @NotNull
    @Size(max = 12)
    @Column(name = "ambulent_number", length = 12, nullable = false)
    private String ambulentNumber;

    @NotNull
    @Column(name = "admission_date", nullable = false)
    private Instant admissionDate;

    @Column(name = "close_date")
    private Instant closeDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MedicalCaseStatus status;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "attendance_type", nullable = false)
    private AttendanceType attendanceType;

    @OneToMany(mappedBy = "medicalCase")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ExternalDocument> documents = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public MedicalCase deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean isConfidental() {
        return confidental;
    }

    public MedicalCase confidental(Boolean confidental) {
        this.confidental = confidental;
        return this;
    }

    public void setConfidental(Boolean confidental) {
        this.confidental = confidental;
    }

    public String getAmbulentNumber() {
        return ambulentNumber;
    }

    public MedicalCase ambulentNumber(String ambulentNumber) {
        this.ambulentNumber = ambulentNumber;
        return this;
    }

    public void setAmbulentNumber(String ambulentNumber) {
        this.ambulentNumber = ambulentNumber;
    }

    public Instant getAdmissionDate() {
        return admissionDate;
    }

    public MedicalCase admissionDate(Instant admissionDate) {
        this.admissionDate = admissionDate;
        return this;
    }

    public void setAdmissionDate(Instant admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Instant getCloseDate() {
        return closeDate;
    }

    public MedicalCase closeDate(Instant closeDate) {
        this.closeDate = closeDate;
        return this;
    }

    public void setCloseDate(Instant closeDate) {
        this.closeDate = closeDate;
    }

    public MedicalCaseStatus getStatus() {
        return status;
    }

    public MedicalCase status(MedicalCaseStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(MedicalCaseStatus status) {
        this.status = status;
    }

    public AttendanceType getAttendanceType() {
        return attendanceType;
    }

    public MedicalCase attendanceType(AttendanceType attendanceType) {
        this.attendanceType = attendanceType;
        return this;
    }

    public void setAttendanceType(AttendanceType attendanceType) {
        this.attendanceType = attendanceType;
    }

    public Set<ExternalDocument> getDocuments() {
        return documents;
    }

    public MedicalCase documents(Set<ExternalDocument> externalDocuments) {
        this.documents = externalDocuments;
        return this;
    }

    public MedicalCase addDocuments(ExternalDocument externalDocument) {
        this.documents.add(externalDocument);
        externalDocument.setMedicalCase(this);
        return this;
    }

    public MedicalCase removeDocuments(ExternalDocument externalDocument) {
        this.documents.remove(externalDocument);
        externalDocument.setMedicalCase(null);
        return this;
    }

    public void setDocuments(Set<ExternalDocument> externalDocuments) {
        this.documents = externalDocuments;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MedicalCase)) {
            return false;
        }
        return id != null && id.equals(((MedicalCase) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MedicalCase{" +
            "id=" + getId() +
            ", deleted='" + isDeleted() + "'" +
            ", confidental='" + isConfidental() + "'" +
            ", ambulentNumber='" + getAmbulentNumber() + "'" +
            ", admissionDate='" + getAdmissionDate() + "'" +
            ", closeDate='" + getCloseDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", attendanceType='" + getAttendanceType() + "'" +
            "}";
    }
}
