package hu.paninform.startmedsol.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import hu.paninform.startmedsol.domain.enumeration.PcRange;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.CsPostalCode} entity.
 */
public class CsPostalCodeDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 5)
    private String code;

    @NotNull
    private String settlement;

    private String part;

    private String street;

    private String kind;

    @NotNull
    private PcRange rangeType;

    @Min(value = 0)
    private Integer rangeLo;

    @Min(value = 0)
    private Integer rangeHi;

    @Size(max = 20)
    private String district;


    private Long validityId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public PcRange getRangeType() {
        return rangeType;
    }

    public void setRangeType(PcRange rangeType) {
        this.rangeType = rangeType;
    }

    public Integer getRangeLo() {
        return rangeLo;
    }

    public void setRangeLo(Integer rangeLo) {
        this.rangeLo = rangeLo;
    }

    public Integer getRangeHi() {
        return rangeHi;
    }

    public void setRangeHi(Integer rangeHi) {
        this.rangeHi = rangeHi;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Long getValidityId() {
        return validityId;
    }

    public void setValidityId(Long validityId) {
        this.validityId = validityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CsPostalCodeDTO)) {
            return false;
        }

        return id != null && id.equals(((CsPostalCodeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CsPostalCodeDTO{" +
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
            ", validityId=" + getValidityId() +
            "}";
    }
}
