package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.Praxis;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Praxis entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PraxisRepository extends JpaRepository<Praxis, Long> {
}
