package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.service.dto.DictionaryItemDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link hu.paninform.startmedsol.domain.DictionaryItem}.
 */
public interface DictionaryItemService {

    /**
     * Save a dictionaryItem.
     *
     * @param dictionaryItemDTO the entity to save.
     * @return the persisted entity.
     */
    DictionaryItemDTO save(DictionaryItemDTO dictionaryItemDTO);

    /**
     * Get all the dictionaryItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DictionaryItemDTO> findAll(Pageable pageable);

    /**
     * Get all the dictionaryItems with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<DictionaryItemDTO> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" dictionaryItem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DictionaryItemDTO> findOne(Long id);

    /**
     * Delete the "id" dictionaryItem.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
