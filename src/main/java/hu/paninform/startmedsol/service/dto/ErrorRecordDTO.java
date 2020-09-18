package hu.paninform.startmedsol.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.ErrorRecord} entity.
 */
public class ErrorRecordDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Instant createDate;

    
    @Lob
    private String content;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ErrorRecordDTO)) {
            return false;
        }

        return id != null && id.equals(((ErrorRecordDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ErrorRecordDTO{" +
            "id=" + getId() +
            ", createDate='" + getCreateDate() + "'" +
            ", content='" + getContent() + "'" +
            "}";
    }
}
