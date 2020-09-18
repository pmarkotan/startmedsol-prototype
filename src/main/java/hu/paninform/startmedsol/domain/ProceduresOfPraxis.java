package hu.paninform.startmedsol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ProceduresOfPraxis.
 */
@Entity
@Table(name = "procedures_of_praxis")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProceduresOfPraxis implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "proceduresOfPraxes", allowSetters = true)
    private CsProcedure procedure;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "proceduresOfPraxes", allowSetters = true)
    private Praxis praxis;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CsProcedure getProcedure() {
        return procedure;
    }

    public ProceduresOfPraxis procedure(CsProcedure csProcedure) {
        this.procedure = csProcedure;
        return this;
    }

    public void setProcedure(CsProcedure csProcedure) {
        this.procedure = csProcedure;
    }

    public Praxis getPraxis() {
        return praxis;
    }

    public ProceduresOfPraxis praxis(Praxis praxis) {
        this.praxis = praxis;
        return this;
    }

    public void setPraxis(Praxis praxis) {
        this.praxis = praxis;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProceduresOfPraxis)) {
            return false;
        }
        return id != null && id.equals(((ProceduresOfPraxis) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProceduresOfPraxis{" +
            "id=" + getId() +
            "}";
    }
}
