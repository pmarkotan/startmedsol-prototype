package hu.paninform.startmedsol.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.ExternalDocument} entity.
 */
public class ExternalDocumentDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String documentId;

    @NotNull
    private String name;

    private Instant created;

    private Instant uploaded;


    private Long medicalCaseId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getUploaded() {
        return uploaded;
    }

    public void setUploaded(Instant uploaded) {
        this.uploaded = uploaded;
    }

    public Long getMedicalCaseId() {
        return medicalCaseId;
    }

    public void setMedicalCaseId(Long medicalCaseId) {
        this.medicalCaseId = medicalCaseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExternalDocumentDTO)) {
            return false;
        }

        return id != null && id.equals(((ExternalDocumentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ExternalDocumentDTO{" +
            "id=" + getId() +
            ", documentId='" + getDocumentId() + "'" +
            ", name='" + getName() + "'" +
            ", created='" + getCreated() + "'" +
            ", uploaded='" + getUploaded() + "'" +
            ", medicalCaseId=" + getMedicalCaseId() +
            "}";
    }
}
