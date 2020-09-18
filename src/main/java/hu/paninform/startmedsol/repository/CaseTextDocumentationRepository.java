package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.CaseTextDocumentation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CaseTextDocumentation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseTextDocumentationRepository extends JpaRepository<CaseTextDocumentation, Long> {
}
