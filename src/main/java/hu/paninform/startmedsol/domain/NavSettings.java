package hu.paninform.startmedsol.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A NavSettings.
 */
@Entity
@Table(name = "nav_settings")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NavSettings implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "technical_user_name", length = 100, nullable = false)
    private String technicalUserName;

    @NotNull
    @Size(max = 100)
    @Column(name = "technical_password", length = 100, nullable = false)
    private String technicalPassword;

    @NotNull
    @Size(max = 100)
    @Column(name = "signing_key", length = 100, nullable = false)
    private String signingKey;

    @NotNull
    @Size(max = 100)
    @Column(name = "replacement_key", length = 100, nullable = false)
    private String replacementKey;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTechnicalUserName() {
        return technicalUserName;
    }

    public NavSettings technicalUserName(String technicalUserName) {
        this.technicalUserName = technicalUserName;
        return this;
    }

    public void setTechnicalUserName(String technicalUserName) {
        this.technicalUserName = technicalUserName;
    }

    public String getTechnicalPassword() {
        return technicalPassword;
    }

    public NavSettings technicalPassword(String technicalPassword) {
        this.technicalPassword = technicalPassword;
        return this;
    }

    public void setTechnicalPassword(String technicalPassword) {
        this.technicalPassword = technicalPassword;
    }

    public String getSigningKey() {
        return signingKey;
    }

    public NavSettings signingKey(String signingKey) {
        this.signingKey = signingKey;
        return this;
    }

    public void setSigningKey(String signingKey) {
        this.signingKey = signingKey;
    }

    public String getReplacementKey() {
        return replacementKey;
    }

    public NavSettings replacementKey(String replacementKey) {
        this.replacementKey = replacementKey;
        return this;
    }

    public void setReplacementKey(String replacementKey) {
        this.replacementKey = replacementKey;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NavSettings)) {
            return false;
        }
        return id != null && id.equals(((NavSettings) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NavSettings{" +
            "id=" + getId() +
            ", technicalUserName='" + getTechnicalUserName() + "'" +
            ", technicalPassword='" + getTechnicalPassword() + "'" +
            ", signingKey='" + getSigningKey() + "'" +
            ", replacementKey='" + getReplacementKey() + "'" +
            "}";
    }
}
