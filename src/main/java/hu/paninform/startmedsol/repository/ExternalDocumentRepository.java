package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.ExternalDocument;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ExternalDocument entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExternalDocumentRepository extends JpaRepository<ExternalDocument, Long> {
}
