package hu.paninform.startmedsol.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A EhrDocument.
 */
@Entity
@Table(name = "ehr_document")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EhrDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "eeszt_id")
    private String eesztId;

    @Column(name = "document_id")
    private String documentId;

    @Column(name = "created")
    private Instant created;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name = "institution_name")
    private String institutionName;

    @Column(name = "praxis_name")
    private String praxisName;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEesztId() {
        return eesztId;
    }

    public EhrDocument eesztId(String eesztId) {
        this.eesztId = eesztId;
        return this;
    }

    public void setEesztId(String eesztId) {
        this.eesztId = eesztId;
    }

    public String getDocumentId() {
        return documentId;
    }

    public EhrDocument documentId(String documentId) {
        this.documentId = documentId;
        return this;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Instant getCreated() {
        return created;
    }

    public EhrDocument created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public String getDocumentType() {
        return documentType;
    }

    public EhrDocument documentType(String documentType) {
        this.documentType = documentType;
        return this;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public EhrDocument doctorName(String doctorName) {
        this.doctorName = doctorName;
        return this;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public EhrDocument institutionName(String institutionName) {
        this.institutionName = institutionName;
        return this;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getPraxisName() {
        return praxisName;
    }

    public EhrDocument praxisName(String praxisName) {
        this.praxisName = praxisName;
        return this;
    }

    public void setPraxisName(String praxisName) {
        this.praxisName = praxisName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EhrDocument)) {
            return false;
        }
        return id != null && id.equals(((EhrDocument) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EhrDocument{" +
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
