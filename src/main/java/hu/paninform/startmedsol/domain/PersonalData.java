package hu.paninform.startmedsol.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import hu.paninform.startmedsol.domain.enumeration.Sex;

/**
 * A PersonalData.
 */
@Entity
@Table(name = "personal_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PersonalData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 16)
    @Column(name = "title", length = 16)
    private String title;

    @NotNull
    @Size(max = 32)
    @Column(name = "family_name", length = 32, nullable = false)
    private String familyName;

    @NotNull
    @Size(max = 16)
    @Column(name = "first_name", length = 16, nullable = false)
    private String firstName;

    @Size(max = 32)
    @Column(name = "other_names", length = 32)
    private String otherNames;

    @Size(max = 16)
    @Column(name = "preferred_name", length = 16)
    private String preferredName;

    @NotNull
    @Size(max = 32)
    @Column(name = "birth_name", length = 32, nullable = false)
    private String birthName;

    @NotNull
    @Size(max = 32)
    @Column(name = "birth_place", length = 32, nullable = false)
    private String birthPlace;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Size(max = 32)
    @Column(name = "mothers_maiden_name", length = 32)
    private String mothersMaidenName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    private Sex sex;

    @Size(max = 16)
    @Column(name = "sex_other", length = 16)
    private String sexOther;

    @NotNull
    @Size(max = 32)
    @Column(name = "nationality", length = 32, nullable = false)
    private String nationality;

    @NotNull
    @Size(max = 254)
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    @Column(name = "email_1", length = 254, nullable = false)
    private String email1;

    @Size(max = 254)
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    @Column(name = "email_2", length = 254)
    private String email2;

    @NotNull
    @Size(max = 16)
    @Pattern(regexp = "^\\++\\d+$")
    @Column(name = "phone", length = 16, nullable = false)
    private String phone;

    @Size(max = 64)
    @Column(name = "ice_contact_name", length = 64)
    private String iceContactName;

    @Size(max = 16)
    @Pattern(regexp = "^\\++\\d+$")
    @Column(name = "ice_contact_phone", length = 16)
    private String iceContactPhone;

    @OneToOne
    @JoinColumn(unique = true)
    private BillingInformation billingInformation;

    @OneToMany(mappedBy = "personalData")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Address> addresses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public PersonalData title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFamilyName() {
        return familyName;
    }

    public PersonalData familyName(String familyName) {
        this.familyName = familyName;
        return this;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public PersonalData firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getOtherNames() {
        return otherNames;
    }

    public PersonalData otherNames(String otherNames) {
        this.otherNames = otherNames;
        return this;
    }

    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public PersonalData preferredName(String preferredName) {
        this.preferredName = preferredName;
        return this;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public String getBirthName() {
        return birthName;
    }

    public PersonalData birthName(String birthName) {
        this.birthName = birthName;
        return this;
    }

    public void setBirthName(String birthName) {
        this.birthName = birthName;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public PersonalData birthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
        return this;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public PersonalData birthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getMothersMaidenName() {
        return mothersMaidenName;
    }

    public PersonalData mothersMaidenName(String mothersMaidenName) {
        this.mothersMaidenName = mothersMaidenName;
        return this;
    }

    public void setMothersMaidenName(String mothersMaidenName) {
        this.mothersMaidenName = mothersMaidenName;
    }

    public Sex getSex() {
        return sex;
    }

    public PersonalData sex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getSexOther() {
        return sexOther;
    }

    public PersonalData sexOther(String sexOther) {
        this.sexOther = sexOther;
        return this;
    }

    public void setSexOther(String sexOther) {
        this.sexOther = sexOther;
    }

    public String getNationality() {
        return nationality;
    }

    public PersonalData nationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getEmail1() {
        return email1;
    }

    public PersonalData email1(String email1) {
        this.email1 = email1;
        return this;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public PersonalData email2(String email2) {
        this.email2 = email2;
        return this;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getPhone() {
        return phone;
    }

    public PersonalData phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIceContactName() {
        return iceContactName;
    }

    public PersonalData iceContactName(String iceContactName) {
        this.iceContactName = iceContactName;
        return this;
    }

    public void setIceContactName(String iceContactName) {
        this.iceContactName = iceContactName;
    }

    public String getIceContactPhone() {
        return iceContactPhone;
    }

    public PersonalData iceContactPhone(String iceContactPhone) {
        this.iceContactPhone = iceContactPhone;
        return this;
    }

    public void setIceContactPhone(String iceContactPhone) {
        this.iceContactPhone = iceContactPhone;
    }

    public BillingInformation getBillingInformation() {
        return billingInformation;
    }

    public PersonalData billingInformation(BillingInformation billingInformation) {
        this.billingInformation = billingInformation;
        return this;
    }

    public void setBillingInformation(BillingInformation billingInformation) {
        this.billingInformation = billingInformation;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public PersonalData addresses(Set<Address> addresses) {
        this.addresses = addresses;
        return this;
    }

    public PersonalData addAddress(Address address) {
        this.addresses.add(address);
        address.setPersonalData(this);
        return this;
    }

    public PersonalData removeAddress(Address address) {
        this.addresses.remove(address);
        address.setPersonalData(null);
        return this;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonalData)) {
            return false;
        }
        return id != null && id.equals(((PersonalData) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PersonalData{" +
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
            "}";
    }
}
