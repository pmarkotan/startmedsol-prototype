package hu.paninform.startmedsol.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.DynamicForm} entity.
 */
public class DynamicFormDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 255)
    private String code;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Size(max = 4)
    private String professionCode;

    @NotNull
    @Size(max = 50)
    private String professionName;

    @Lob
    private String formTemplate;

    @Lob
    private String reportTemplate;

    private Boolean separatelyPrint;


    private Long providerId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getFormTemplate() {
        return formTemplate;
    }

    public void setFormTemplate(String formTemplate) {
        this.formTemplate = formTemplate;
    }

    public String getReportTemplate() {
        return reportTemplate;
    }

    public void setReportTemplate(String reportTemplate) {
        this.reportTemplate = reportTemplate;
    }

    public Boolean isSeparatelyPrint() {
        return separatelyPrint;
    }

    public void setSeparatelyPrint(Boolean separatelyPrint) {
        this.separatelyPrint = separatelyPrint;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DynamicFormDTO)) {
            return false;
        }

        return id != null && id.equals(((DynamicFormDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DynamicFormDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", professionCode='" + getProfessionCode() + "'" +
            ", professionName='" + getProfessionName() + "'" +
            ", formTemplate='" + getFormTemplate() + "'" +
            ", reportTemplate='" + getReportTemplate() + "'" +
            ", separatelyPrint='" + isSeparatelyPrint() + "'" +
            ", providerId=" + getProviderId() +
            "}";
    }
}
