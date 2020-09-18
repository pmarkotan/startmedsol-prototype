package hu.paninform.startmedsol.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import hu.paninform.startmedsol.domain.enumeration.DgSex;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link hu.paninform.startmedsol.domain.CsDiagnosis} entity. This class is used
 * in {@link hu.paninform.startmedsol.web.rest.CsDiagnosisResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /cs-diagnoses?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CsDiagnosisCriteria implements Serializable, Criteria {
    /**
     * Class for filtering DgSex
     */
    public static class DgSexFilter extends Filter<DgSex> {

        public DgSexFilter() {
        }

        public DgSexFilter(DgSexFilter filter) {
            super(filter);
        }

        @Override
        public DgSexFilter copy() {
            return new DgSexFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private BooleanFilter report;

    private StringFilter descr;

    private DgSexFilter sex;

    private IntegerFilter ageMin;

    private IntegerFilter ageMax;

    private LongFilter validityId;

    public CsDiagnosisCriteria() {
    }

    public CsDiagnosisCriteria(CsDiagnosisCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.report = other.report == null ? null : other.report.copy();
        this.descr = other.descr == null ? null : other.descr.copy();
        this.sex = other.sex == null ? null : other.sex.copy();
        this.ageMin = other.ageMin == null ? null : other.ageMin.copy();
        this.ageMax = other.ageMax == null ? null : other.ageMax.copy();
        this.validityId = other.validityId == null ? null : other.validityId.copy();
    }

    @Override
    public CsDiagnosisCriteria copy() {
        return new CsDiagnosisCriteria(this);
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

    public BooleanFilter getReport() {
        return report;
    }

    public void setReport(BooleanFilter report) {
        this.report = report;
    }

    public StringFilter getDescr() {
        return descr;
    }

    public void setDescr(StringFilter descr) {
        this.descr = descr;
    }

    public DgSexFilter getSex() {
        return sex;
    }

    public void setSex(DgSexFilter sex) {
        this.sex = sex;
    }

    public IntegerFilter getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(IntegerFilter ageMin) {
        this.ageMin = ageMin;
    }

    public IntegerFilter getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(IntegerFilter ageMax) {
        this.ageMax = ageMax;
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
        final CsDiagnosisCriteria that = (CsDiagnosisCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(report, that.report) &&
            Objects.equals(descr, that.descr) &&
            Objects.equals(sex, that.sex) &&
            Objects.equals(ageMin, that.ageMin) &&
            Objects.equals(ageMax, that.ageMax) &&
            Objects.equals(validityId, that.validityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        report,
        descr,
        sex,
        ageMin,
        ageMax,
        validityId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CsDiagnosisCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (report != null ? "report=" + report + ", " : "") +
                (descr != null ? "descr=" + descr + ", " : "") +
                (sex != null ? "sex=" + sex + ", " : "") +
                (ageMin != null ? "ageMin=" + ageMin + ", " : "") +
                (ageMax != null ? "ageMax=" + ageMax + ", " : "") +
                (validityId != null ? "validityId=" + validityId + ", " : "") +
            "}";
    }

}
