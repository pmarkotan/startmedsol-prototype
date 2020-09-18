package hu.paninform.startmedsol.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import hu.paninform.startmedsol.domain.enumeration.DgSex;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.CsDiagnosis} entity.
 */
public class CsDiagnosisDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 10)
    private String code;

    @NotNull
    private Boolean report;

    @NotNull
    private String descr;

    @NotNull
    private DgSex sex;

    @Min(value = 0)
    @Max(value = 99)
    private Integer ageMin;

    @Min(value = 0)
    @Max(value = 99)
    private Integer ageMax;


    private Long validityId;
    
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

    public Boolean isReport() {
        return report;
    }

    public void setReport(Boolean report) {
        this.report = report;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public DgSex getSex() {
        return sex;
    }

    public void setSex(DgSex sex) {
        this.sex = sex;
    }

    public Integer getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }

    public Integer getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(Integer ageMax) {
        this.ageMax = ageMax;
    }

    public Long getValidityId() {
        return validityId;
    }

    public void setValidityId(Long validityId) {
        this.validityId = validityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CsDiagnosisDTO)) {
            return false;
        }

        return id != null && id.equals(((CsDiagnosisDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CsDiagnosisDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", report='" + isReport() + "'" +
            ", descr='" + getDescr() + "'" +
            ", sex='" + getSex() + "'" +
            ", ageMin=" + getAgeMin() +
            ", ageMax=" + getAgeMax() +
            ", validityId=" + getValidityId() +
            "}";
    }
}
