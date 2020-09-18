package hu.paninform.startmedsol.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.HashTag} entity.
 */
public class HashTagDTO implements Serializable {
    
    private Long id;

    @Size(max = 256)
    private String name;


    private Long providerId;

    private String providerNameLong;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public String getProviderNameLong() {
        return providerNameLong;
    }

    public void setProviderNameLong(String providerNameLong) {
        this.providerNameLong = providerNameLong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HashTagDTO)) {
            return false;
        }

        return id != null && id.equals(((HashTagDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HashTagDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", providerId=" + getProviderId() +
            ", providerNameLong='" + getProviderNameLong() + "'" +
            "}";
    }
}
