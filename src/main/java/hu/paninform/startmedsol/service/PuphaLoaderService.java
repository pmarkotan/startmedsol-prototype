package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.service.dto.PuphaLoaderDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link hu.paninform.startmedsol.domain.PuphaLoader}.
 */
public interface PuphaLoaderService {

    /**
     * Save a puphaLoader.
     *
     * @param puphaLoaderDTO the entity to save.
     * @return the persisted entity.
     */
    PuphaLoaderDTO save(PuphaLoaderDTO puphaLoaderDTO);

    /**
     * Get all the puphaLoaders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PuphaLoaderDTO> findAll(Pageable pageable);


    /**
     * Get the "id" puphaLoader.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PuphaLoaderDTO> findOne(Long id);

    /**
     * Delete the "id" puphaLoader.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
