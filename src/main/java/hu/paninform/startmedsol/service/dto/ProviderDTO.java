package hu.paninform.startmedsol.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.Provider} entity.
 */
public class ProviderDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 100)
    private String nameLong;

    @Size(max = 30)
    private String nameShort;

    @NotNull
    @Size(max = 6)
    @Pattern(regexp = "^[A-Z0-9]+$")
    private String institutionId;

    @NotNull
    @Size(max = 254)
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    private String email;

    @NotNull
    @Size(max = 16)
    @Pattern(regexp = "^\\++\\d+$")
    private String phone;

    @NotNull
    private String accountNumber;


    private Long contactPersonId;

    private Long companyId;

    private Long billingInformationId;
    private Set<EmployeeDTO> employees = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameLong() {
        return nameLong;
    }

    public void setNameLong(String nameLong) {
        this.nameLong = nameLong;
    }

    public String getNameShort() {
        return nameShort;
    }

    public void setNameShort(String nameShort) {
        this.nameShort = nameShort;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getContactPersonId() {
        return contactPersonId;
    }

    public void setContactPersonId(Long contactPersonId) {
        this.contactPersonId = contactPersonId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getBillingInformationId() {
        return billingInformationId;
    }

    public void setBillingInformationId(Long billingInformationId) {
        this.billingInformationId = billingInformationId;
    }

    public Set<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeDTO> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProviderDTO)) {
            return false;
        }

        return id != null && id.equals(((ProviderDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProviderDTO{" +
            "id=" + getId() +
            ", nameLong='" + getNameLong() + "'" +
            ", nameShort='" + getNameShort() + "'" +
            ", institutionId='" + getInstitutionId() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", contactPersonId=" + getContactPersonId() +
            ", companyId=" + getCompanyId() +
            ", billingInformationId=" + getBillingInformationId() +
            ", employees='" + getEmployees() + "'" +
            "}";
    }
}
