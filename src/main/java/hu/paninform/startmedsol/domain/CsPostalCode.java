package hu.paninform.startmedsol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import hu.paninform.startmedsol.domain.enumeration.PcRange;

/**
 * A CsPostalCode.
 */
@Entity
@Table(name = "cs_postal_code")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CsPostalCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 5)
    @Column(name = "code", length = 5, nullable = false)
    private String code;

    @NotNull
    @Column(name = "settlement", nullable = false)
    private String settlement;

    @Column(name = "part")
    private String part;

    @Column(name = "street")
    private String street;

    @Column(name = "kind")
    private String kind;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "range_type", nullable = false)
    private PcRange rangeType;

    @Min(value = 0)
    @Column(name = "range_lo")
    private Integer rangeLo;

    @Min(value = 0)
    @Column(name = "range_hi")
    private Integer rangeHi;

    @Size(max = 20)
    @Column(name = "district", length = 20)
    private String district;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "csPostalCodes", allowSetters = true)
    private Validity validity;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public CsPostalCode code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSettlement() {
        return settlement;
    }

    public CsPostalCode settlement(String settlement) {
        this.settlement = settlement;
        return this;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public String getPart() {
        return part;
    }

    public CsPostalCode part(String part) {
        this.part = part;
        return this;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getStreet() {
        return street;
    }

    public CsPostalCode street(String street) {
        this.street = street;
        return this;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getKind() {
        return kind;
    }

    public CsPostalCode kind(String kind) {
        this.kind = kind;
        return this;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public PcRange getRangeType() {
        return rangeType;
    }

    public CsPostalCode rangeType(PcRange rangeType) {
        this.rangeType = rangeType;
        return this;
    }

    public void setRangeType(PcRange rangeType) {
        this.rangeType = rangeType;
    }

    public Integer getRangeLo() {
        return rangeLo;
    }

    public CsPostalCode rangeLo(Integer rangeLo) {
        this.rangeLo = rangeLo;
        return this;
    }

    public void setRangeLo(Integer rangeLo) {
        this.rangeLo = rangeLo;
    }

    public Integer getRangeHi() {
        return rangeHi;
    }

    public CsPostalCode rangeHi(Integer rangeHi) {
        this.rangeHi = rangeHi;
        return this;
    }

    public void setRangeHi(Integer rangeHi) {
        this.rangeHi = rangeHi;
    }

    public String getDistrict() {
        return district;
    }

    public CsPostalCode district(String district) {
        this.district = district;
        return this;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Validity getValidity() {
        return validity;
    }

    public CsPostalCode validity(Validity validity) {
        this.validity = validity;
        return this;
    }

    public void setValidity(Validity validity) {
        this.validity = validity;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CsPostalCode)) {
            return false;
        }
        return id != null && id.equals(((CsPostalCode) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CsPostalCode{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", settlement='" + getSettlement() + "'" +
            ", part='" + getPart() + "'" +
            ", street='" + getStreet() + "'" +
            ", kind='" + getKind() + "'" +
            ", rangeType='" + getRangeType() + "'" +
            ", rangeLo=" + getRangeLo() +
            ", rangeHi=" + getRangeHi() +
            ", district='" + getDistrict() + "'" +
            "}";
    }
}
