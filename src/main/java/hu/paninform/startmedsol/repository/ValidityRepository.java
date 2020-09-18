package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.Validity;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Validity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ValidityRepository extends JpaRepository<Validity, Long> {
}
