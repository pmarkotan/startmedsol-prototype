package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.CsPostalCode;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CsPostalCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CsPostalCodeRepository extends JpaRepository<CsPostalCode, Long>, JpaSpecificationExecutor<CsPostalCode> {
}
