package hu.paninform.startmedsol.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import hu.paninform.startmedsol.domain.enumeration.FeedBackMessageType;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.FeedBackMessage} entity.
 */
public class FeedBackMessageDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Instant createDate;

    private String author;

    @NotNull
    private FeedBackMessageType type;

    @NotNull
    @Size(max = 1024)
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public FeedBackMessageType getType() {
        return type;
    }

    public void setType(FeedBackMessageType type) {
        this.type = type;
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
        if (!(o instanceof FeedBackMessageDTO)) {
            return false;
        }

        return id != null && id.equals(((FeedBackMessageDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FeedBackMessageDTO{" +
            "id=" + getId() +
            ", createDate='" + getCreateDate() + "'" +
            ", author='" + getAuthor() + "'" +
            ", type='" + getType() + "'" +
            ", content='" + getContent() + "'" +
            "}";
    }
}
