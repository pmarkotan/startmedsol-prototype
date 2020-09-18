package hu.paninform.startmedsol.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import hu.paninform.startmedsol.domain.enumeration.Sex;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.PersonalData} entity.
 */
public class PersonalDataDTO implements Serializable {
    
    private Long id;

    @Size(max = 16)
    private String title;

    @NotNull
    @Size(max = 32)
    private String familyName;

    @NotNull
    @Size(max = 16)
    private String firstName;

    @Size(max = 32)
    private String otherNames;

    @Size(max = 16)
    private String preferredName;

    @NotNull
    @Size(max = 32)
    private String birthName;

    @NotNull
    @Size(max = 32)
    private String birthPlace;

    @NotNull
    private LocalDate birthDate;

    @Size(max = 32)
    private String mothersMaidenName;

    @NotNull
    private Sex sex;

    @Size(max = 16)
    private String sexOther;

    @NotNull
    @Size(max = 32)
    private String nationality;

    @NotNull
    @Size(max = 254)
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    private String email1;

    @Size(max = 254)
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    private String email2;

    @NotNull
    @Size(max = 16)
    @Pattern(regexp = "^\\++\\d+$")
    private String phone;

    @Size(max = 64)
    private String iceContactName;

    @Size(max = 16)
    @Pattern(regexp = "^\\++\\d+$")
    private String iceContactPhone;


    private Long billingInformationId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public String getBirthName() {
        return birthName;
    }

    public void setBirthName(String birthName) {
        this.birthName = birthName;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getMothersMaidenName() {
        return mothersMaidenName;
    }

    public void setMothersMaidenName(String mothersMaidenName) {
        this.mothersMaidenName = mothersMaidenName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getSexOther() {
        return sexOther;
    }

    public void setSexOther(String sexOther) {
        this.sexOther = sexOther;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIceContactName() {
        return iceContactName;
    }

    public void setIceContactName(String iceContactName) {
        this.iceContactName = iceContactName;
    }

    public String getIceContactPhone() {
        return iceContactPhone;
    }

    public void setIceContactPhone(String iceContactPhone) {
        this.iceContactPhone = iceContactPhone;
    }

    public Long getBillingInformationId() {
        return billingInformationId;
    }

    public void setBillingInformationId(Long billingInformationId) {
        this.billingInformationId = billingInformationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonalDataDTO)) {
            return false;
        }

        return id != null && id.equals(((PersonalDataDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PersonalDataDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", familyName='" + getFamilyName() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", otherNames='" + getOtherNames() + "'" +
            ", preferredName='" + getPreferredName() + "'" +
            ", birthName='" + getBirthName() + "'" +
            ", birthPlace='" + getBirthPlace() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", mothersMaidenName='" + getMothersMaidenName() + "'" +
            ", sex='" + getSex() + "'" +
            ", sexOther='" + getSexOther() + "'" +
            ", nationality='" + getNationality() + "'" +
            ", email1='" + getEmail1() + "'" +
            ", email2='" + getEmail2() + "'" +
            ", phone='" + getPhone() + "'" +
            ", iceContactName='" + getIceContactName() + "'" +
            ", iceContactPhone='" + getIceContactPhone() + "'" +
            ", billingInformationId=" + getBillingInformationId() +
            "}";
    }
}
