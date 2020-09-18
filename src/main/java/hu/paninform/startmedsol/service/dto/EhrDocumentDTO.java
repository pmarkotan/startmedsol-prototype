package hu.paninform.startmedsol.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.EhrDocument} entity.
 */
public class EhrDocumentDTO implements Serializable {
    
    private Long id;

    private String eesztId;

    private String documentId;

    private Instant created;

    private String documentType;

    private String doctorName;

    private String institutionName;

    private String praxisName;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEesztId() {
        return eesztId;
    }

    public void setEesztId(String eesztId) {
        this.eesztId = eesztId;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getPraxisName() {
        return praxisName;
    }

    public void setPraxisName(String praxisName) {
        this.praxisName = praxisName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EhrDocumentDTO)) {
            return false;
        }

        return id != null && id.equals(((EhrDocumentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EhrDocumentDTO{" +
            "id=" + getId() +
            ", eesztId='" + getEesztId() + "'" +
            ", documentId='" + getDocumentId() + "'" +
            ", created='" + getCreated() + "'" +
            ", documentType='" + getDocumentType() + "'" +
            ", doctorName='" + getDoctorName() + "'" +
            ", institutionName='" + getInstitutionName() + "'" +
            ", praxisName='" + getPraxisName() + "'" +
            "}";
    }
}
