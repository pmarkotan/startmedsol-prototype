package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.PphEuIndication;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PphEuIndication entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PphEuIndicationRepository extends JpaRepository<PphEuIndication, Long> {
}
