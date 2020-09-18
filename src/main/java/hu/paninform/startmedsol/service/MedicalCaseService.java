package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.MedicalCase;
import hu.paninform.startmedsol.repository.MedicalCaseRepository;
import hu.paninform.startmedsol.service.dto.MedicalCaseDTO;
import hu.paninform.startmedsol.service.mapper.MedicalCaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MedicalCase}.
 */
@Service
@Transactional
public class MedicalCaseService {

    private final Logger log = LoggerFactory.getLogger(MedicalCaseService.class);

    private final MedicalCaseRepository medicalCaseRepository;

    private final MedicalCaseMapper medicalCaseMapper;

    public MedicalCaseService(MedicalCaseRepository medicalCaseRepository, MedicalCaseMapper medicalCaseMapper) {
        this.medicalCaseRepository = medicalCaseRepository;
        this.medicalCaseMapper = medicalCaseMapper;
    }

    /**
     * Save a medicalCase.
     *
     * @param medicalCaseDTO the entity to save.
     * @return the persisted entity.
     */
    public MedicalCaseDTO save(MedicalCaseDTO medicalCaseDTO) {
        log.debug("Request to save MedicalCase : {}", medicalCaseDTO);
        MedicalCase medicalCase = medicalCaseMapper.toEntity(medicalCaseDTO);
        medicalCase = medicalCaseRepository.save(medicalCase);
        return medicalCaseMapper.toDto(medicalCase);
    }

    /**
     * Get all the medicalCases.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MedicalCaseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MedicalCases");
        return medicalCaseRepository.findAll(pageable)
            .map(medicalCaseMapper::toDto);
    }


    /**
     * Get one medicalCase by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MedicalCaseDTO> findOne(Long id) {
        log.debug("Request to get MedicalCase : {}", id);
        return medicalCaseRepository.findById(id)
            .map(medicalCaseMapper::toDto);
    }

    /**
     * Delete the medicalCase by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MedicalCase : {}", id);
        medicalCaseRepository.deleteById(id);
    }
}
