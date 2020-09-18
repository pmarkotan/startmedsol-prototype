package hu.paninform.startmedsol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A DynamicForm.
 */
@Entity
@Table(name = "dynamic_form")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DynamicForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "code", length = 255, nullable = false)
    private String code;

    @NotNull
    @Size(max = 255)
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @NotNull
    @Size(max = 4)
    @Column(name = "profession_code", length = 4, nullable = false)
    private String professionCode;

    @NotNull
    @Size(max = 50)
    @Column(name = "profession_name", length = 50, nullable = false)
    private String professionName;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "form_template")
    private String formTemplate;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "report_template")
    private String reportTemplate;

    @Column(name = "separately_print")
    private Boolean separatelyPrint;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "dynamicForms", allowSetters = true)
    private Provider provider;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public DynamicForm code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public DynamicForm name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfessionCode() {
        return professionCode;
    }

    public DynamicForm professionCode(String professionCode) {
        this.professionCode = professionCode;
        return this;
    }

    public void setProfessionCode(String professionCode) {
        this.professionCode = professionCode;
    }

    public String getProfessionName() {
        return professionName;
    }

    public DynamicForm professionName(String professionName) {
        this.professionName = professionName;
        return this;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public String getFormTemplate() {
        return formTemplate;
    }

    public DynamicForm formTemplate(String formTemplate) {
        this.formTemplate = formTemplate;
        return this;
    }

    public void setFormTemplate(String formTemplate) {
        this.formTemplate = formTemplate;
    }

    public String getReportTemplate() {
        return reportTemplate;
    }

    public DynamicForm reportTemplate(String reportTemplate) {
        this.reportTemplate = reportTemplate;
        return this;
    }

    public void setReportTemplate(String reportTemplate) {
        this.reportTemplate = reportTemplate;
    }

    public Boolean isSeparatelyPrint() {
        return separatelyPrint;
    }

    public DynamicForm separatelyPrint(Boolean separatelyPrint) {
        this.separatelyPrint = separatelyPrint;
        return this;
    }

    public void setSeparatelyPrint(Boolean separatelyPrint) {
        this.separatelyPrint = separatelyPrint;
    }

    public Provider getProvider() {
        return provider;
    }

    public DynamicForm provider(Provider provider) {
        this.provider = provider;
        return this;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DynamicForm)) {
            return false;
        }
        return id != null && id.equals(((DynamicForm) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DynamicForm{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", professionCode='" + getProfessionCode() + "'" +
            ", professionName='" + getProfessionName() + "'" +
            ", formTemplate='" + getFormTemplate() + "'" +
            ", reportTemplate='" + getReportTemplate() + "'" +
            ", separatelyPrint='" + isSeparatelyPrint() + "'" +
            "}";
    }
}
