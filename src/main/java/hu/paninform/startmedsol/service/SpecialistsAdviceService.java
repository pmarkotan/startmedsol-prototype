package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.SpecialistsAdvice;
import hu.paninform.startmedsol.repository.SpecialistsAdviceRepository;
import hu.paninform.startmedsol.service.dto.SpecialistsAdviceDTO;
import hu.paninform.startmedsol.service.mapper.SpecialistsAdviceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SpecialistsAdvice}.
 */
@Service
@Transactional
public class SpecialistsAdviceService {

    private final Logger log = LoggerFactory.getLogger(SpecialistsAdviceService.class);

    private final SpecialistsAdviceRepository specialistsAdviceRepository;

    private final SpecialistsAdviceMapper specialistsAdviceMapper;

    public SpecialistsAdviceService(SpecialistsAdviceRepository specialistsAdviceRepository, SpecialistsAdviceMapper specialistsAdviceMapper) {
        this.specialistsAdviceRepository = specialistsAdviceRepository;
        this.specialistsAdviceMapper = specialistsAdviceMapper;
    }

    /**
     * Save a specialistsAdvice.
     *
     * @param specialistsAdviceDTO the entity to save.
     * @return the persisted entity.
     */
    public SpecialistsAdviceDTO save(SpecialistsAdviceDTO specialistsAdviceDTO) {
        log.debug("Request to save SpecialistsAdvice : {}", specialistsAdviceDTO);
        SpecialistsAdvice specialistsAdvice = specialistsAdviceMapper.toEntity(specialistsAdviceDTO);
        specialistsAdvice = specialistsAdviceRepository.save(specialistsAdvice);
        return specialistsAdviceMapper.toDto(specialistsAdvice);
    }

    /**
     * Get all the specialistsAdvices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SpecialistsAdviceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SpecialistsAdvices");
        return specialistsAdviceRepository.findAll(pageable)
            .map(specialistsAdviceMapper::toDto);
    }


    /**
     * Get one specialistsAdvice by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SpecialistsAdviceDTO> findOne(Long id) {
        log.debug("Request to get SpecialistsAdvice : {}", id);
        return specialistsAdviceRepository.findById(id)
            .map(specialistsAdviceMapper::toDto);
    }

    /**
     * Delete the specialistsAdvice by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SpecialistsAdvice : {}", id);
        specialistsAdviceRepository.deleteById(id);
    }
}
