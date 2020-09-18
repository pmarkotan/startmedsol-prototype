package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.PphEuScore;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PphEuScore entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PphEuScoreRepository extends JpaRepository<PphEuScore, Long> {
}
