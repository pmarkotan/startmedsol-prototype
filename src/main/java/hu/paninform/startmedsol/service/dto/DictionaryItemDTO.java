package hu.paninform.startmedsol.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.DictionaryItem} entity.
 */
public class DictionaryItemDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 32)
    private String dictionaryItemType;

    @NotNull
    @Size(max = 64)
    private String code;

    private Integer orderNumber;

    @Size(max = 255)
    private String description;

    private Set<DictionaryItemDTO> parents = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDictionaryItemType() {
        return dictionaryItemType;
    }

    public void setDictionaryItemType(String dictionaryItemType) {
        this.dictionaryItemType = dictionaryItemType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<DictionaryItemDTO> getParents() {
        return parents;
    }

    public void setParents(Set<DictionaryItemDTO> dictionaryItems) {
        this.parents = dictionaryItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DictionaryItemDTO)) {
            return false;
        }

        return id != null && id.equals(((DictionaryItemDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DictionaryItemDTO{" +
            "id=" + getId() +
            ", dictionaryItemType='" + getDictionaryItemType() + "'" +
            ", code='" + getCode() + "'" +
            ", orderNumber=" + getOrderNumber() +
            ", description='" + getDescription() + "'" +
            ", parents='" + getParents() + "'" +
            "}";
    }
}
