package hu.paninform.startmedsol.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

import hu.paninform.startmedsol.domain.enumeration.CodeSetType;

import hu.paninform.startmedsol.domain.enumeration.CodeSetStatus;

/**
 * A CodeSetLoad.
 */
@Entity
@Table(name = "code_set_load")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CodeSetLoad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CodeSetType type;

    @Column(name = "latest_version")
    private String latestVersion;

    @Column(name = "url")
    private String url;

    @Column(name = "log")
    private String log;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CodeSetStatus status;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CodeSetType getType() {
        return type;
    }

    public CodeSetLoad type(CodeSetType type) {
        this.type = type;
        return this;
    }

    public void setType(CodeSetType type) {
        this.type = type;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public CodeSetLoad latestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
        return this;
    }

    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }

    public String getUrl() {
        return url;
    }

    public CodeSetLoad url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLog() {
        return log;
    }

    public CodeSetLoad log(String log) {
        this.log = log;
        return this;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public CodeSetStatus getStatus() {
        return status;
    }

    public CodeSetLoad status(CodeSetStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(CodeSetStatus status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CodeSetLoad)) {
            return false;
        }
        return id != null && id.equals(((CodeSetLoad) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CodeSetLoad{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", latestVersion='" + getLatestVersion() + "'" +
            ", url='" + getUrl() + "'" +
            ", log='" + getLog() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
