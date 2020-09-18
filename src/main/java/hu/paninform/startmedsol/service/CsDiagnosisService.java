package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.CsDiagnosis;
import hu.paninform.startmedsol.repository.CsDiagnosisRepository;
import hu.paninform.startmedsol.service.dto.CsDiagnosisDTO;
import hu.paninform.startmedsol.service.mapper.CsDiagnosisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link CsDiagnosis}.
 */
@Service
@Transactional
public class CsDiagnosisService {

    private final Logger log = LoggerFactory.getLogger(CsDiagnosisService.class);

    private final CsDiagnosisRepository csDiagnosisRepository;

    private final CsDiagnosisMapper csDiagnosisMapper;

    public CsDiagnosisService(CsDiagnosisRepository csDiagnosisRepository, CsDiagnosisMapper csDiagnosisMapper) {
        this.csDiagnosisRepository = csDiagnosisRepository;
        this.csDiagnosisMapper = csDiagnosisMapper;
    }

    /**
     * Save a csDiagnosis.
     *
     * @param csDiagnosisDTO the entity to save.
     * @return the persisted entity.
     */
    public CsDiagnosisDTO save(CsDiagnosisDTO csDiagnosisDTO) {
        log.debug("Request to save CsDiagnosis : {}", csDiagnosisDTO);
        CsDiagnosis csDiagnosis = csDiagnosisMapper.toEntity(csDiagnosisDTO);
        csDiagnosis = csDiagnosisRepository.save(csDiagnosis);
        return csDiagnosisMapper.toDto(csDiagnosis);
    }

    /**
     * Get all the csDiagnoses.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CsDiagnosisDTO> findAll() {
        log.debug("Request to get all CsDiagnoses");
        return csDiagnosisRepository.findAll().stream()
            .map(csDiagnosisMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one csDiagnosis by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CsDiagnosisDTO> findOne(Long id) {
        log.debug("Request to get CsDiagnosis : {}", id);
        return csDiagnosisRepository.findById(id)
            .map(csDiagnosisMapper::toDto);
    }

    /**
     * Delete the csDiagnosis by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CsDiagnosis : {}", id);
        csDiagnosisRepository.deleteById(id);
    }
}
