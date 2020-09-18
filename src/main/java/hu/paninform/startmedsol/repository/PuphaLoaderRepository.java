package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.PuphaLoader;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PuphaLoader entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PuphaLoaderRepository extends JpaRepository<PuphaLoader, Long> {
}
