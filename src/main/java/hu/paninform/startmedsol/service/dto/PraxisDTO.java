package hu.paninform.startmedsol.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import hu.paninform.startmedsol.domain.enumeration.PraxisStatus;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.Praxis} entity.
 */
public class PraxisDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Size(max = 9)
    @Pattern(regexp = "^[A-Z0-9]+$")
    private String departmentId;

    @NotNull
    @Size(max = 4)
    @Pattern(regexp = "^[A-Z0-9]+$")
    private String professionCode;

    @NotNull
    @Size(max = 50)
    private String professionName;

    @NotNull
    private PraxisStatus status;

    @NotNull
    @Size(max = 16)
    @Pattern(regexp = "^\\++\\d+$")
    private String appointmentPhone;

    @NotNull
    @Min(value = 1)
    @Max(value = 99999999)
    private Integer treatmentLogbookNumber;


    private Long officeAddressId;

    private Long providerId;

    private Long doctorId;
    
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

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getProfessionCode() {
        return professionCode;
    }

    public void setProfessionCode(String professionCode) {
        this.professionCode = professionCode;
    }

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public PraxisStatus getStatus() {
        return status;
    }

    public void setStatus(PraxisStatus status) {
        this.status = status;
    }

    public String getAppointmentPhone() {
        return appointmentPhone;
    }

    public void setAppointmentPhone(String appointmentPhone) {
        this.appointmentPhone = appointmentPhone;
    }

    public Integer getTreatmentLogbookNumber() {
        return treatmentLogbookNumber;
    }

    public void setTreatmentLogbookNumber(Integer treatmentLogbookNumber) {
        this.treatmentLogbookNumber = treatmentLogbookNumber;
    }

    public Long getOfficeAddressId() {
        return officeAddressId;
    }

    public void setOfficeAddressId(Long addressId) {
        this.officeAddressId = addressId;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long employeeId) {
        this.doctorId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PraxisDTO)) {
            return false;
        }

        return id != null && id.equals(((PraxisDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PraxisDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", departmentId='" + getDepartmentId() + "'" +
            ", professionCode='" + getProfessionCode() + "'" +
            ", professionName='" + getProfessionName() + "'" +
            ", status='" + getStatus() + "'" +
            ", appointmentPhone='" + getAppointmentPhone() + "'" +
            ", treatmentLogbookNumber=" + getTreatmentLogbookNumber() +
            ", officeAddressId=" + getOfficeAddressId() +
            ", providerId=" + getProviderId() +
            ", doctorId=" + getDoctorId() +
            "}";
    }
}
