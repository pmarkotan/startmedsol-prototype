package hu.paninform.startmedsol.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import hu.paninform.startmedsol.domain.enumeration.StatisticsType;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.Statistics} entity.
 */
public class StatisticsDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Instant created;

    @NotNull
    private StatisticsType tpye;

    @Size(max = 1024)
    private String content;

    @Size(max = 1024)
    private String description;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public StatisticsType getTpye() {
        return tpye;
    }

    public void setTpye(StatisticsType tpye) {
        this.tpye = tpye;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StatisticsDTO)) {
            return false;
        }

        return id != null && id.equals(((StatisticsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StatisticsDTO{" +
            "id=" + getId() +
            ", created='" + getCreated() + "'" +
            ", tpye='" + getTpye() + "'" +
            ", content='" + getContent() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
