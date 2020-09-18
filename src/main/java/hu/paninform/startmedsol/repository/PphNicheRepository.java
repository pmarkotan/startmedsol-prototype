package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.PphNiche;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PphNiche entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PphNicheRepository extends JpaRepository<PphNiche, Long> {
}
