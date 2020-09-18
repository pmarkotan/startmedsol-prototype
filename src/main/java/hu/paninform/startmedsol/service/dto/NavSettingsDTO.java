package hu.paninform.startmedsol.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.NavSettings} entity.
 */
public class NavSettingsDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 100)
    private String technicalUserName;

    @NotNull
    @Size(max = 100)
    private String technicalPassword;

    @NotNull
    @Size(max = 100)
    private String signingKey;

    @NotNull
    @Size(max = 100)
    private String replacementKey;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTechnicalUserName() {
        return technicalUserName;
    }

    public void setTechnicalUserName(String technicalUserName) {
        this.technicalUserName = technicalUserName;
    }

    public String getTechnicalPassword() {
        return technicalPassword;
    }

    public void setTechnicalPassword(String technicalPassword) {
        this.technicalPassword = technicalPassword;
    }

    public String getSigningKey() {
        return signingKey;
    }

    public void setSigningKey(String signingKey) {
        this.signingKey = signingKey;
    }

    public String getReplacementKey() {
        return replacementKey;
    }

    public void setReplacementKey(String replacementKey) {
        this.replacementKey = replacementKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NavSettingsDTO)) {
            return false;
        }

        return id != null && id.equals(((NavSettingsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NavSettingsDTO{" +
            "id=" + getId() +
            ", technicalUserName='" + getTechnicalUserName() + "'" +
            ", technicalPassword='" + getTechnicalPassword() + "'" +
            ", signingKey='" + getSigningKey() + "'" +
            ", replacementKey='" + getReplacementKey() + "'" +
            "}";
    }
}
