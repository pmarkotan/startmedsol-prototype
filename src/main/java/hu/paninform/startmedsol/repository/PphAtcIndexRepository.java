package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.PphAtcIndex;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PphAtcIndex entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PphAtcIndexRepository extends JpaRepository<PphAtcIndex, Long> {
}
