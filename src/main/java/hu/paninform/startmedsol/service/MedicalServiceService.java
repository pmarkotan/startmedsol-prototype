package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.MedicalService;
import hu.paninform.startmedsol.repository.MedicalServiceRepository;
import hu.paninform.startmedsol.service.dto.MedicalServiceDTO;
import hu.paninform.startmedsol.service.mapper.MedicalServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MedicalService}.
 */
@Service
@Transactional
public class MedicalServiceService {

    private final Logger log = LoggerFactory.getLogger(MedicalServiceService.class);

    private final MedicalServiceRepository medicalServiceRepository;

    private final MedicalServiceMapper medicalServiceMapper;

    public MedicalServiceService(MedicalServiceRepository medicalServiceRepository, MedicalServiceMapper medicalServiceMapper) {
        this.medicalServiceRepository = medicalServiceRepository;
        this.medicalServiceMapper = medicalServiceMapper;
    }

    /**
     * Save a medicalService.
     *
     * @param medicalServiceDTO the entity to save.
     * @return the persisted entity.
     */
    public MedicalServiceDTO save(MedicalServiceDTO medicalServiceDTO) {
        log.debug("Request to save MedicalService : {}", medicalServiceDTO);
        MedicalService medicalService = medicalServiceMapper.toEntity(medicalServiceDTO);
        medicalService = medicalServiceRepository.save(medicalService);
        return medicalServiceMapper.toDto(medicalService);
    }

    /**
     * Get all the medicalServices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MedicalServiceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MedicalServices");
        return medicalServiceRepository.findAll(pageable)
            .map(medicalServiceMapper::toDto);
    }


    /**
     * Get one medicalService by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MedicalServiceDTO> findOne(Long id) {
        log.debug("Request to get MedicalService : {}", id);
        return medicalServiceRepository.findById(id)
            .map(medicalServiceMapper::toDto);
    }

    /**
     * Delete the medicalService by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MedicalService : {}", id);
        medicalServiceRepository.deleteById(id);
    }
}
