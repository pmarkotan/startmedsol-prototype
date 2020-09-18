package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.DictionaryTranslation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DictionaryTranslation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DictionaryTranslationRepository extends JpaRepository<DictionaryTranslation, Long> {
}
