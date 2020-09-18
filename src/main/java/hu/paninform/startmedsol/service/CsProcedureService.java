package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.CsProcedure;
import hu.paninform.startmedsol.repository.CsProcedureRepository;
import hu.paninform.startmedsol.service.dto.CsProcedureDTO;
import hu.paninform.startmedsol.service.mapper.CsProcedureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CsProcedure}.
 */
@Service
@Transactional
public class CsProcedureService {

    private final Logger log = LoggerFactory.getLogger(CsProcedureService.class);

    private final CsProcedureRepository csProcedureRepository;

    private final CsProcedureMapper csProcedureMapper;

    public CsProcedureService(CsProcedureRepository csProcedureRepository, CsProcedureMapper csProcedureMapper) {
        this.csProcedureRepository = csProcedureRepository;
        this.csProcedureMapper = csProcedureMapper;
    }

    /**
     * Save a csProcedure.
     *
     * @param csProcedureDTO the entity to save.
     * @return the persisted entity.
     */
    public CsProcedureDTO save(CsProcedureDTO csProcedureDTO) {
        log.debug("Request to save CsProcedure : {}", csProcedureDTO);
        CsProcedure csProcedure = csProcedureMapper.toEntity(csProcedureDTO);
        csProcedure = csProcedureRepository.save(csProcedure);
        return csProcedureMapper.toDto(csProcedure);
    }

    /**
     * Get all the csProcedures.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CsProcedureDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CsProcedures");
        return csProcedureRepository.findAll(pageable)
            .map(csProcedureMapper::toDto);
    }


    /**
     * Get one csProcedure by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CsProcedureDTO> findOne(Long id) {
        log.debug("Request to get CsProcedure : {}", id);
        return csProcedureRepository.findById(id)
            .map(csProcedureMapper::toDto);
    }

    /**
     * Delete the csProcedure by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CsProcedure : {}", id);
        csProcedureRepository.deleteById(id);
    }
}
