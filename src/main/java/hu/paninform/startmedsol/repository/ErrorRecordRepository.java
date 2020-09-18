package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.ErrorRecord;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ErrorRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ErrorRecordRepository extends JpaRepository<ErrorRecord, Long> {
}
