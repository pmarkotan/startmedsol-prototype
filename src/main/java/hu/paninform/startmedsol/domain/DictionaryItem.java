package hu.paninform.startmedsol.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DictionaryItem.
 */
@Entity
@Table(name = "dictionary_item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DictionaryItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 32)
    @Column(name = "dictionary_item_type", length = 32, nullable = false)
    private String dictionaryItemType;

    @NotNull
    @Size(max = 64)
    @Column(name = "code", length = 64, nullable = false)
    private String code;

    @Column(name = "order_number")
    private Integer orderNumber;

    @Size(max = 255)
    @Column(name = "description", length = 255)
    private String description;

    @OneToMany(mappedBy = "dictionaryItem")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<DictionaryTranslation> dictionaryTranslations = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "dictionary_item_parent",
               joinColumns = @JoinColumn(name = "dictionary_item_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "parent_id", referencedColumnName = "id"))
    private Set<DictionaryItem> parents = new HashSet<>();

    @ManyToMany(mappedBy = "parents")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<DictionaryItem> children = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDictionaryItemType() {
        return dictionaryItemType;
    }

    public DictionaryItem dictionaryItemType(String dictionaryItemType) {
        this.dictionaryItemType = dictionaryItemType;
        return this;
    }

    public void setDictionaryItemType(String dictionaryItemType) {
        this.dictionaryItemType = dictionaryItemType;
    }

    public String getCode() {
        return code;
    }

    public DictionaryItem code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public DictionaryItem orderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDescription() {
        return description;
    }

    public DictionaryItem description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<DictionaryTranslation> getDictionaryTranslations() {
        return dictionaryTranslations;
    }

    public DictionaryItem dictionaryTranslations(Set<DictionaryTranslation> dictionaryTranslations) {
        this.dictionaryTranslations = dictionaryTranslations;
        return this;
    }

    public DictionaryItem addDictionaryTranslations(DictionaryTranslation dictionaryTranslation) {
        this.dictionaryTranslations.add(dictionaryTranslation);
        dictionaryTranslation.setDictionaryItem(this);
        return this;
    }

    public DictionaryItem removeDictionaryTranslations(DictionaryTranslation dictionaryTranslation) {
        this.dictionaryTranslations.remove(dictionaryTranslation);
        dictionaryTranslation.setDictionaryItem(null);
        return this;
    }

    public void setDictionaryTranslations(Set<DictionaryTranslation> dictionaryTranslations) {
        this.dictionaryTranslations = dictionaryTranslations;
    }

    public Set<DictionaryItem> getParents() {
        return parents;
    }

    public DictionaryItem parents(Set<DictionaryItem> dictionaryItems) {
        this.parents = dictionaryItems;
        return this;
    }

    public DictionaryItem addParent(DictionaryItem dictionaryItem) {
        this.parents.add(dictionaryItem);
        dictionaryItem.getChildren().add(this);
        return this;
    }

    public DictionaryItem removeParent(DictionaryItem dictionaryItem) {
        this.parents.remove(dictionaryItem);
        dictionaryItem.getChildren().remove(this);
        return this;
    }

    public void setParents(Set<DictionaryItem> dictionaryItems) {
        this.parents = dictionaryItems;
    }

    public Set<DictionaryItem> getChildren() {
        return children;
    }

    public DictionaryItem children(Set<DictionaryItem> dictionaryItems) {
        this.children = dictionaryItems;
        return this;
    }

    public DictionaryItem addChildren(DictionaryItem dictionaryItem) {
        this.children.add(dictionaryItem);
        dictionaryItem.getParents().add(this);
        return this;
    }

    public DictionaryItem removeChildren(DictionaryItem dictionaryItem) {
        this.children.remove(dictionaryItem);
        dictionaryItem.getParents().remove(this);
        return this;
    }

    public void setChildren(Set<DictionaryItem> dictionaryItems) {
        this.children = dictionaryItems;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DictionaryItem)) {
            return false;
        }
        return id != null && id.equals(((DictionaryItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DictionaryItem{" +
            "id=" + getId() +
            ", dictionaryItemType='" + getDictionaryItemType() + "'" +
            ", code='" + getCode() + "'" +
            ", orderNumber=" + getOrderNumber() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
