package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.Validity;
import hu.paninform.startmedsol.repository.ValidityRepository;
import hu.paninform.startmedsol.service.dto.ValidityDTO;
import hu.paninform.startmedsol.service.mapper.ValidityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Validity}.
 */
@Service
@Transactional
public class ValidityService {

    private final Logger log = LoggerFactory.getLogger(ValidityService.class);

    private final ValidityRepository validityRepository;

    private final ValidityMapper validityMapper;

    public ValidityService(ValidityRepository validityRepository, ValidityMapper validityMapper) {
        this.validityRepository = validityRepository;
        this.validityMapper = validityMapper;
    }

    /**
     * Save a validity.
     *
     * @param validityDTO the entity to save.
     * @return the persisted entity.
     */
    public ValidityDTO save(ValidityDTO validityDTO) {
        log.debug("Request to save Validity : {}", validityDTO);
        Validity validity = validityMapper.toEntity(validityDTO);
        validity = validityRepository.save(validity);
        return validityMapper.toDto(validity);
    }

    /**
     * Get all the validities.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ValidityDTO> findAll() {
        log.debug("Request to get all Validities");
        return validityRepository.findAll().stream()
            .map(validityMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one validity by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ValidityDTO> findOne(Long id) {
        log.debug("Request to get Validity : {}", id);
        return validityRepository.findById(id)
            .map(validityMapper::toDto);
    }

    /**
     * Delete the validity by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Validity : {}", id);
        validityRepository.deleteById(id);
    }
}
