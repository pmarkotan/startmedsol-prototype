package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.ProceduresOfPraxis;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProceduresOfPraxis entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProceduresOfPraxisRepository extends JpaRepository<ProceduresOfPraxis, Long> {
}
