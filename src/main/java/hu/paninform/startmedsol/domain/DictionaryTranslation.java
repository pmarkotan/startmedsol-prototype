package hu.paninform.startmedsol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import hu.paninform.startmedsol.domain.enumeration.Language;

/**
 * A DictionaryTranslation.
 */
@Entity
@Table(name = "dictionary_translation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DictionaryTranslation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 32)
    @Column(name = "label", length = 32, nullable = false)
    private String label;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "language", nullable = false)
    private Language language;

    @ManyToOne
    @JsonIgnoreProperties(value = "dictionaryTranslations", allowSetters = true)
    private DictionaryItem dictionaryItem;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public DictionaryTranslation label(String label) {
        this.label = label;
        return this;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Language getLanguage() {
        return language;
    }

    public DictionaryTranslation language(Language language) {
        this.language = language;
        return this;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public DictionaryItem getDictionaryItem() {
        return dictionaryItem;
    }

    public DictionaryTranslation dictionaryItem(DictionaryItem dictionaryItem) {
        this.dictionaryItem = dictionaryItem;
        return this;
    }

    public void setDictionaryItem(DictionaryItem dictionaryItem) {
        this.dictionaryItem = dictionaryItem;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DictionaryTranslation)) {
            return false;
        }
        return id != null && id.equals(((DictionaryTranslation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DictionaryTranslation{" +
            "id=" + getId() +
            ", label='" + getLabel() + "'" +
            ", language='" + getLanguage() + "'" +
            "}";
    }
}
