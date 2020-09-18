package hu.paninform.startmedsol.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import hu.paninform.startmedsol.domain.enumeration.MedicalCaseStatus;
import hu.paninform.startmedsol.domain.enumeration.AttendanceType;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.MedicalCase} entity.
 */
public class MedicalCaseDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Boolean deleted;

    @NotNull
    private Boolean confidental;

    @NotNull
    @Size(max = 12)
    private String ambulentNumber;

    @NotNull
    private Instant admissionDate;

    private Instant closeDate;

    @NotNull
    private MedicalCaseStatus status;

    @NotNull
    private AttendanceType attendanceType;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean isConfidental() {
        return confidental;
    }

    public void setConfidental(Boolean confidental) {
        this.confidental = confidental;
    }

    public String getAmbulentNumber() {
        return ambulentNumber;
    }

    public void setAmbulentNumber(String ambulentNumber) {
        this.ambulentNumber = ambulentNumber;
    }

    public Instant getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Instant admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Instant getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Instant closeDate) {
        this.closeDate = closeDate;
    }

    public MedicalCaseStatus getStatus() {
        return status;
    }

    public void setStatus(MedicalCaseStatus status) {
        this.status = status;
    }

    public AttendanceType getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(AttendanceType attendanceType) {
        this.attendanceType = attendanceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MedicalCaseDTO)) {
            return false;
        }

        return id != null && id.equals(((MedicalCaseDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MedicalCaseDTO{" +
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
