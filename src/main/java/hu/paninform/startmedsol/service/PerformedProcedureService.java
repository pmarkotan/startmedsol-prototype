package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.PerformedProcedure;
import hu.paninform.startmedsol.repository.PerformedProcedureRepository;
import hu.paninform.startmedsol.service.dto.PerformedProcedureDTO;
import hu.paninform.startmedsol.service.mapper.PerformedProcedureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PerformedProcedure}.
 */
@Service
@Transactional
public class PerformedProcedureService {

    private final Logger log = LoggerFactory.getLogger(PerformedProcedureService.class);

    private final PerformedProcedureRepository performedProcedureRepository;

    private final PerformedProcedureMapper performedProcedureMapper;

    public PerformedProcedureService(PerformedProcedureRepository performedProcedureRepository, PerformedProcedureMapper performedProcedureMapper) {
        this.performedProcedureRepository = performedProcedureRepository;
        this.performedProcedureMapper = performedProcedureMapper;
    }

    /**
     * Save a performedProcedure.
     *
     * @param performedProcedureDTO the entity to save.
     * @return the persisted entity.
     */
    public PerformedProcedureDTO save(PerformedProcedureDTO performedProcedureDTO) {
        log.debug("Request to save PerformedProcedure : {}", performedProcedureDTO);
        PerformedProcedure performedProcedure = performedProcedureMapper.toEntity(performedProcedureDTO);
        performedProcedure = performedProcedureRepository.save(performedProcedure);
        return performedProcedureMapper.toDto(performedProcedure);
    }

    /**
     * Get all the performedProcedures.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PerformedProcedureDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PerformedProcedures");
        return performedProcedureRepository.findAll(pageable)
            .map(performedProcedureMapper::toDto);
    }


    /**
     * Get one performedProcedure by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PerformedProcedureDTO> findOne(Long id) {
        log.debug("Request to get PerformedProcedure : {}", id);
        return performedProcedureRepository.findById(id)
            .map(performedProcedureMapper::toDto);
    }

    /**
     * Delete the performedProcedure by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PerformedProcedure : {}", id);
        performedProcedureRepository.deleteById(id);
    }
}
