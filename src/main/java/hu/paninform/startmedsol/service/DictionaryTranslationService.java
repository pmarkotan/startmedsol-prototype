package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.service.dto.DictionaryTranslationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link hu.paninform.startmedsol.domain.DictionaryTranslation}.
 */
public interface DictionaryTranslationService {

    /**
     * Save a dictionaryTranslation.
     *
     * @param dictionaryTranslationDTO the entity to save.
     * @return the persisted entity.
     */
    DictionaryTranslationDTO save(DictionaryTranslationDTO dictionaryTranslationDTO);

    /**
     * Get all the dictionaryTranslations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DictionaryTranslationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" dictionaryTranslation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DictionaryTranslationDTO> findOne(Long id);

    /**
     * Delete the "id" dictionaryTranslation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
