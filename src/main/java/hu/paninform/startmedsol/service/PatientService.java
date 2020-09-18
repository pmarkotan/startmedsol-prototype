package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.Patient;
import hu.paninform.startmedsol.repository.PatientRepository;
import hu.paninform.startmedsol.service.dto.PatientDTO;
import hu.paninform.startmedsol.service.mapper.PatientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Patient}.
 */
@Service
@Transactional
public class PatientService {

    private final Logger log = LoggerFactory.getLogger(PatientService.class);

    private final PatientRepository patientRepository;

    private final PatientMapper patientMapper;

    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    /**
     * Save a patient.
     *
     * @param patientDTO the entity to save.
     * @return the persisted entity.
     */
    public PatientDTO save(PatientDTO patientDTO) {
        log.debug("Request to save Patient : {}", patientDTO);
        Patient patient = patientMapper.toEntity(patientDTO);
        patient = patientRepository.save(patient);
        return patientMapper.toDto(patient);
    }

    /**
     * Get all the patients.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PatientDTO> findAll() {
        log.debug("Request to get all Patients");
        return patientRepository.findAllWithEagerRelationships().stream()
            .map(patientMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get all the patients with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<PatientDTO> findAllWithEagerRelationships(Pageable pageable) {
        return patientRepository.findAllWithEagerRelationships(pageable).map(patientMapper::toDto);
    }

    /**
     * Get one patient by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PatientDTO> findOne(Long id) {
        log.debug("Request to get Patient : {}", id);
        return patientRepository.findOneWithEagerRelationships(id)
            .map(patientMapper::toDto);
    }

    /**
     * Delete the patient by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Patient : {}", id);
        patientRepository.deleteById(id);
    }
}
