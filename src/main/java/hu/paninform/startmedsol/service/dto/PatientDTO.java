package hu.paninform.startmedsol.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.Patient} entity.
 */
public class PatientDTO implements Serializable {
    
    private Long id;

    @Size(max = 255)
    private String patientIdentifier;

    private Boolean privacyStatement;

    private Boolean anonymised;

    private Boolean classified;


    private Long personalDataId;
    private Set<HashTagDTO> tags = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientIdentifier() {
        return patientIdentifier;
    }

    public void setPatientIdentifier(String patientIdentifier) {
        this.patientIdentifier = patientIdentifier;
    }

    public Boolean isPrivacyStatement() {
        return privacyStatement;
    }

    public void setPrivacyStatement(Boolean privacyStatement) {
        this.privacyStatement = privacyStatement;
    }

    public Boolean isAnonymised() {
        return anonymised;
    }

    public void setAnonymised(Boolean anonymised) {
        this.anonymised = anonymised;
    }

    public Boolean isClassified() {
        return classified;
    }

    public void setClassified(Boolean classified) {
        this.classified = classified;
    }

    public Long getPersonalDataId() {
        return personalDataId;
    }

    public void setPersonalDataId(Long personalDataId) {
        this.personalDataId = personalDataId;
    }

    public Set<HashTagDTO> getTags() {
        return tags;
    }

    public void setTags(Set<HashTagDTO> hashTags) {
        this.tags = hashTags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientDTO)) {
            return false;
        }

        return id != null && id.equals(((PatientDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientDTO{" +
            "id=" + getId() +
            ", patientIdentifier='" + getPatientIdentifier() + "'" +
            ", privacyStatement='" + isPrivacyStatement() + "'" +
            ", anonymised='" + isAnonymised() + "'" +
            ", classified='" + isClassified() + "'" +
            ", personalDataId=" + getPersonalDataId() +
            ", tags='" + getTags() + "'" +
            "}";
    }
}
