package hu.paninform.startmedsol.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import hu.paninform.startmedsol.domain.enumeration.FeedBackMessageType;

/**
 * A FeedBackMessage.
 */
@Entity
@Table(name = "feed_back_message")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FeedBackMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @Column(name = "author")
    private String author;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private FeedBackMessageType type;

    @NotNull
    @Size(max = 1024)
    @Column(name = "content", length = 1024, nullable = false)
    private String content;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public FeedBackMessage createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getAuthor() {
        return author;
    }

    public FeedBackMessage author(String author) {
        this.author = author;
        return this;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public FeedBackMessageType getType() {
        return type;
    }

    public FeedBackMessage type(FeedBackMessageType type) {
        this.type = type;
        return this;
    }

    public void setType(FeedBackMessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public FeedBackMessage content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FeedBackMessage)) {
            return false;
        }
        return id != null && id.equals(((FeedBackMessage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FeedBackMessage{" +
            "id=" + getId() +
            ", createDate='" + getCreateDate() + "'" +
            ", author='" + getAuthor() + "'" +
            ", type='" + getType() + "'" +
            ", content='" + getContent() + "'" +
            "}";
    }
}
