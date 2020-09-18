package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.ProceduresOfPraxis;
import hu.paninform.startmedsol.repository.ProceduresOfPraxisRepository;
import hu.paninform.startmedsol.service.dto.ProceduresOfPraxisDTO;
import hu.paninform.startmedsol.service.mapper.ProceduresOfPraxisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProceduresOfPraxis}.
 */
@Service
@Transactional
public class ProceduresOfPraxisService {

    private final Logger log = LoggerFactory.getLogger(ProceduresOfPraxisService.class);

    private final ProceduresOfPraxisRepository proceduresOfPraxisRepository;

    private final ProceduresOfPraxisMapper proceduresOfPraxisMapper;

    public ProceduresOfPraxisService(ProceduresOfPraxisRepository proceduresOfPraxisRepository, ProceduresOfPraxisMapper proceduresOfPraxisMapper) {
        this.proceduresOfPraxisRepository = proceduresOfPraxisRepository;
        this.proceduresOfPraxisMapper = proceduresOfPraxisMapper;
    }

    /**
     * Save a proceduresOfPraxis.
     *
     * @param proceduresOfPraxisDTO the entity to save.
     * @return the persisted entity.
     */
    public ProceduresOfPraxisDTO save(ProceduresOfPraxisDTO proceduresOfPraxisDTO) {
        log.debug("Request to save ProceduresOfPraxis : {}", proceduresOfPraxisDTO);
        ProceduresOfPraxis proceduresOfPraxis = proceduresOfPraxisMapper.toEntity(proceduresOfPraxisDTO);
        proceduresOfPraxis = proceduresOfPraxisRepository.save(proceduresOfPraxis);
        return proceduresOfPraxisMapper.toDto(proceduresOfPraxis);
    }

    /**
     * Get all the proceduresOfPraxes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ProceduresOfPraxisDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProceduresOfPraxes");
        return proceduresOfPraxisRepository.findAll(pageable)
            .map(proceduresOfPraxisMapper::toDto);
    }


    /**
     * Get one proceduresOfPraxis by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProceduresOfPraxisDTO> findOne(Long id) {
        log.debug("Request to get ProceduresOfPraxis : {}", id);
        return proceduresOfPraxisRepository.findById(id)
            .map(proceduresOfPraxisMapper::toDto);
    }

    /**
     * Delete the proceduresOfPraxis by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ProceduresOfPraxis : {}", id);
        proceduresOfPraxisRepository.deleteById(id);
    }
}
