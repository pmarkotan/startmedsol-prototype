package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.PerformedProcedure;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PerformedProcedure entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PerformedProcedureRepository extends JpaRepository<PerformedProcedure, Long> {
}
