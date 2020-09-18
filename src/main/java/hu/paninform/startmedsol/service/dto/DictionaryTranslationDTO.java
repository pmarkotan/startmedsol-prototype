package hu.paninform.startmedsol.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import hu.paninform.startmedsol.domain.enumeration.Language;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.DictionaryTranslation} entity.
 */
public class DictionaryTranslationDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 32)
    private String label;

    @NotNull
    private Language language;


    private Long dictionaryItemId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Long getDictionaryItemId() {
        return dictionaryItemId;
    }

    public void setDictionaryItemId(Long dictionaryItemId) {
        this.dictionaryItemId = dictionaryItemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DictionaryTranslationDTO)) {
            return false;
        }

        return id != null && id.equals(((DictionaryTranslationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DictionaryTranslationDTO{" +
            "id=" + getId() +
            ", label='" + getLabel() + "'" +
            ", language='" + getLanguage() + "'" +
            ", dictionaryItemId=" + getDictionaryItemId() +
            "}";
    }
}
