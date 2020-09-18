package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.CsCountry;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CsCountry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CsCountryRepository extends JpaRepository<CsCountry, Long>, JpaSpecificationExecutor<CsCountry> {
}
