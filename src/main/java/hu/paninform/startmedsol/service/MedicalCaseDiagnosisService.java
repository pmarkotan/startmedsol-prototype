package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.MedicalCaseDiagnosis;
import hu.paninform.startmedsol.repository.MedicalCaseDiagnosisRepository;
import hu.paninform.startmedsol.service.dto.MedicalCaseDiagnosisDTO;
import hu.paninform.startmedsol.service.mapper.MedicalCaseDiagnosisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MedicalCaseDiagnosis}.
 */
@Service
@Transactional
public class MedicalCaseDiagnosisService {

    private final Logger log = LoggerFactory.getLogger(MedicalCaseDiagnosisService.class);

    private final MedicalCaseDiagnosisRepository medicalCaseDiagnosisRepository;

    private final MedicalCaseDiagnosisMapper medicalCaseDiagnosisMapper;

    public MedicalCaseDiagnosisService(MedicalCaseDiagnosisRepository medicalCaseDiagnosisRepository, MedicalCaseDiagnosisMapper medicalCaseDiagnosisMapper) {
        this.medicalCaseDiagnosisRepository = medicalCaseDiagnosisRepository;
        this.medicalCaseDiagnosisMapper = medicalCaseDiagnosisMapper;
    }

    /**
     * Save a medicalCaseDiagnosis.
     *
     * @param medicalCaseDiagnosisDTO the entity to save.
     * @return the persisted entity.
     */
    public MedicalCaseDiagnosisDTO save(MedicalCaseDiagnosisDTO medicalCaseDiagnosisDTO) {
        log.debug("Request to save MedicalCaseDiagnosis : {}", medicalCaseDiagnosisDTO);
        MedicalCaseDiagnosis medicalCaseDiagnosis = medicalCaseDiagnosisMapper.toEntity(medicalCaseDiagnosisDTO);
        medicalCaseDiagnosis = medicalCaseDiagnosisRepository.save(medicalCaseDiagnosis);
        return medicalCaseDiagnosisMapper.toDto(medicalCaseDiagnosis);
    }

    /**
     * Get all the medicalCaseDiagnoses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MedicalCaseDiagnosisDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MedicalCaseDiagnoses");
        return medicalCaseDiagnosisRepository.findAll(pageable)
            .map(medicalCaseDiagnosisMapper::toDto);
    }


    /**
     * Get one medicalCaseDiagnosis by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MedicalCaseDiagnosisDTO> findOne(Long id) {
        log.debug("Request to get MedicalCaseDiagnosis : {}", id);
        return medicalCaseDiagnosisRepository.findById(id)
            .map(medicalCaseDiagnosisMapper::toDto);
    }

    /**
     * Delete the medicalCaseDiagnosis by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MedicalCaseDiagnosis : {}", id);
        medicalCaseDiagnosisRepository.deleteById(id);
    }
}
