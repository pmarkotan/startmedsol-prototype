package hu.paninform.startmedsol.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Provider.
 */
@Entity
@Table(name = "provider")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Provider implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name_long", length = 100, nullable = false)
    private String nameLong;

    @Size(max = 30)
    @Column(name = "name_short", length = 30)
    private String nameShort;

    @NotNull
    @Size(max = 6)
    @Pattern(regexp = "^[A-Z0-9]+$")
    @Column(name = "institution_id", length = 6, nullable = false)
    private String institutionId;

    @NotNull
    @Size(max = 254)
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    @Column(name = "email", length = 254, nullable = false)
    private String email;

    @NotNull
    @Size(max = 16)
    @Pattern(regexp = "^\\++\\d+$")
    @Column(name = "phone", length = 16, nullable = false)
    private String phone;

    @NotNull
    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @OneToOne
    @JoinColumn(unique = true)
    private ContactPerson contactPerson;

    @OneToOne
    @JoinColumn(unique = true)
    private Company company;

    @OneToOne
    @JoinColumn(unique = true)
    private BillingInformation billingInformation;

    @OneToMany(mappedBy = "provider")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Praxis> praxes = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "provider_employee",
               joinColumns = @JoinColumn(name = "provider_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
    private Set<Employee> employees = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameLong() {
        return nameLong;
    }

    public Provider nameLong(String nameLong) {
        this.nameLong = nameLong;
        return this;
    }

    public void setNameLong(String nameLong) {
        this.nameLong = nameLong;
    }

    public String getNameShort() {
        return nameShort;
    }

    public Provider nameShort(String nameShort) {
        this.nameShort = nameShort;
        return this;
    }

    public void setNameShort(String nameShort) {
        this.nameShort = nameShort;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public Provider institutionId(String institutionId) {
        this.institutionId = institutionId;
        return this;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getEmail() {
        return email;
    }

    public Provider email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public Provider phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Provider accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public Provider contactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
        return this;
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Company getCompany() {
        return company;
    }

    public Provider company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public BillingInformation getBillingInformation() {
        return billingInformation;
    }

    public Provider billingInformation(BillingInformation billingInformation) {
        this.billingInformation = billingInformation;
        return this;
    }

    public void setBillingInformation(BillingInformation billingInformation) {
        this.billingInformation = billingInformation;
    }

    public Set<Praxis> getPraxes() {
        return praxes;
    }

    public Provider praxes(Set<Praxis> praxes) {
        this.praxes = praxes;
        return this;
    }

    public Provider addPraxis(Praxis praxis) {
        this.praxes.add(praxis);
        praxis.setProvider(this);
        return this;
    }

    public Provider removePraxis(Praxis praxis) {
        this.praxes.remove(praxis);
        praxis.setProvider(null);
        return this;
    }

    public void setPraxes(Set<Praxis> praxes) {
        this.praxes = praxes;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public Provider employees(Set<Employee> employees) {
        this.employees = employees;
        return this;
    }

    public Provider addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.getProviders().add(this);
        return this;
    }

    public Provider removeEmployee(Employee employee) {
        this.employees.remove(employee);
        employee.getProviders().remove(this);
        return this;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Provider)) {
            return false;
        }
        return id != null && id.equals(((Provider) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Provider{" +
            "id=" + getId() +
            ", nameLong='" + getNameLong() + "'" +
            ", nameShort='" + getNameShort() + "'" +
            ", institutionId='" + getInstitutionId() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", accountNumber='" + getAccountNumber() + "'" +
            "}";
    }
}
