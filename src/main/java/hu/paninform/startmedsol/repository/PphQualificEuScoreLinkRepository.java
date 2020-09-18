package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.PphQualificEuScoreLink;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PphQualificEuScoreLink entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PphQualificEuScoreLinkRepository extends JpaRepository<PphQualificEuScoreLink, Long> {
}
