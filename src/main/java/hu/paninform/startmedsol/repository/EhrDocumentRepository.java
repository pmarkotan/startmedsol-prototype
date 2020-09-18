package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.EhrDocument;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EhrDocument entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EhrDocumentRepository extends JpaRepository<EhrDocument, Long> {
}
