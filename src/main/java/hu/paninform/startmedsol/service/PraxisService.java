package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.Praxis;
import hu.paninform.startmedsol.repository.PraxisRepository;
import hu.paninform.startmedsol.service.dto.PraxisDTO;
import hu.paninform.startmedsol.service.mapper.PraxisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Praxis}.
 */
@Service
@Transactional
public class PraxisService {

    private final Logger log = LoggerFactory.getLogger(PraxisService.class);

    private final PraxisRepository praxisRepository;

    private final PraxisMapper praxisMapper;

    public PraxisService(PraxisRepository praxisRepository, PraxisMapper praxisMapper) {
        this.praxisRepository = praxisRepository;
        this.praxisMapper = praxisMapper;
    }

    /**
     * Save a praxis.
     *
     * @param praxisDTO the entity to save.
     * @return the persisted entity.
     */
    public PraxisDTO save(PraxisDTO praxisDTO) {
        log.debug("Request to save Praxis : {}", praxisDTO);
        Praxis praxis = praxisMapper.toEntity(praxisDTO);
        praxis = praxisRepository.save(praxis);
        return praxisMapper.toDto(praxis);
    }

    /**
     * Get all the praxes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PraxisDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Praxes");
        return praxisRepository.findAll(pageable)
            .map(praxisMapper::toDto);
    }


    /**
     * Get one praxis by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PraxisDTO> findOne(Long id) {
        log.debug("Request to get Praxis : {}", id);
        return praxisRepository.findById(id)
            .map(praxisMapper::toDto);
    }

    /**
     * Delete the praxis by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Praxis : {}", id);
        praxisRepository.deleteById(id);
    }
}
