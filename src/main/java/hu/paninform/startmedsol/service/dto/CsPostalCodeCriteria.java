package hu.paninform.startmedsol.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import hu.paninform.startmedsol.domain.enumeration.PcRange;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link hu.paninform.startmedsol.domain.CsPostalCode} entity. This class is used
 * in {@link hu.paninform.startmedsol.web.rest.CsPostalCodeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /cs-postal-codes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CsPostalCodeCriteria implements Serializable, Criteria {
    /**
     * Class for filtering PcRange
     */
    public static class PcRangeFilter extends Filter<PcRange> {

        public PcRangeFilter() {
        }

        public PcRangeFilter(PcRangeFilter filter) {
            super(filter);
        }

        @Override
        public PcRangeFilter copy() {
            return new PcRangeFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private StringFilter settlement;

    private StringFilter part;

    private StringFilter street;

    private StringFilter kind;

    private PcRangeFilter rangeType;

    private IntegerFilter rangeLo;

    private IntegerFilter rangeHi;

    private StringFilter district;

    private LongFilter validityId;

    public CsPostalCodeCriteria() {
    }

    public CsPostalCodeCriteria(CsPostalCodeCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.settlement = other.settlement == null ? null : other.settlement.copy();
        this.part = other.part == null ? null : other.part.copy();
        this.street = other.street == null ? null : other.street.copy();
        this.kind = other.kind == null ? null : other.kind.copy();
        this.rangeType = other.rangeType == null ? null : other.rangeType.copy();
        this.rangeLo = other.rangeLo == null ? null : other.rangeLo.copy();
        this.rangeHi = other.rangeHi == null ? null : other.rangeHi.copy();
        this.district = other.district == null ? null : other.district.copy();
        this.validityId = other.validityId == null ? null : other.validityId.copy();
    }

    @Override
    public CsPostalCodeCriteria copy() {
        return new CsPostalCodeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public StringFilter getSettlement() {
        return settlement;
    }

    public void setSettlement(StringFilter settlement) {
        this.settlement = settlement;
    }

    public StringFilter getPart() {
        return part;
    }

    public void setPart(StringFilter part) {
        this.part = part;
    }

    public StringFilter getStreet() {
        return street;
    }

    public void setStreet(StringFilter street) {
        this.street = street;
    }

    public StringFilter getKind() {
        return kind;
    }

    public void setKind(StringFilter kind) {
        this.kind = kind;
    }

    public PcRangeFilter getRangeType() {
        return rangeType;
    }

    public void setRangeType(PcRangeFilter rangeType) {
        this.rangeType = rangeType;
    }

    public IntegerFilter getRangeLo() {
        return rangeLo;
    }

    public void setRangeLo(IntegerFilter rangeLo) {
        this.rangeLo = rangeLo;
    }

    public IntegerFilter getRangeHi() {
        return rangeHi;
    }

    public void setRangeHi(IntegerFilter rangeHi) {
        this.rangeHi = rangeHi;
    }

    public StringFilter getDistrict() {
        return district;
    }

    public void setDistrict(StringFilter district) {
        this.district = district;
    }

    public LongFilter getValidityId() {
        return validityId;
    }

    public void setValidityId(LongFilter validityId) {
        this.validityId = validityId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CsPostalCodeCriteria that = (CsPostalCodeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(settlement, that.settlement) &&
            Objects.equals(part, that.part) &&
            Objects.equals(street, that.street) &&
            Objects.equals(kind, that.kind) &&
            Objects.equals(rangeType, that.rangeType) &&
            Objects.equals(rangeLo, that.rangeLo) &&
            Objects.equals(rangeHi, that.rangeHi) &&
            Objects.equals(district, that.district) &&
            Objects.equals(validityId, that.validityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        settlement,
        part,
        street,
        kind,
        rangeType,
        rangeLo,
        rangeHi,
        district,
        validityId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CsPostalCodeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (settlement != null ? "settlement=" + settlement + ", " : "") +
                (part != null ? "part=" + part + ", " : "") +
                (street != null ? "street=" + street + ", " : "") +
                (kind != null ? "kind=" + kind + ", " : "") +
                (rangeType != null ? "rangeType=" + rangeType + ", " : "") +
                (rangeLo != null ? "rangeLo=" + rangeLo + ", " : "") +
                (rangeHi != null ? "rangeHi=" + rangeHi + ", " : "") +
                (district != null ? "district=" + district + ", " : "") +
                (validityId != null ? "validityId=" + validityId + ", " : "") +
            "}";
    }

}
