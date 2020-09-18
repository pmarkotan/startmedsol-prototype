package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.CsProcedure;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CsProcedure entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CsProcedureRepository extends JpaRepository<CsProcedure, Long>, JpaSpecificationExecutor<CsProcedure> {
}
