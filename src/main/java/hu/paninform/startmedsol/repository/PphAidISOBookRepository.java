package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.PphAidISOBook;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PphAidISOBook entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PphAidISOBookRepository extends JpaRepository<PphAidISOBook, Long> {
}
