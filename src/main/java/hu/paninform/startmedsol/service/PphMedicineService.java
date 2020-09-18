package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.PphMedicine;
import hu.paninform.startmedsol.repository.PphMedicineRepository;
import hu.paninform.startmedsol.service.dto.PphMedicineDTO;
import hu.paninform.startmedsol.service.mapper.PphMedicineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PphMedicine}.
 */
@Service
@Transactional
public class PphMedicineService {

    private final Logger log = LoggerFactory.getLogger(PphMedicineService.class);

    private final PphMedicineRepository pphMedicineRepository;

    private final PphMedicineMapper pphMedicineMapper;

    public PphMedicineService(PphMedicineRepository pphMedicineRepository, PphMedicineMapper pphMedicineMapper) {
        this.pphMedicineRepository = pphMedicineRepository;
        this.pphMedicineMapper = pphMedicineMapper;
    }

    /**
     * Save a pphMedicine.
     *
     * @param pphMedicineDTO the entity to save.
     * @return the persisted entity.
     */
    public PphMedicineDTO save(PphMedicineDTO pphMedicineDTO) {
        log.debug("Request to save PphMedicine : {}", pphMedicineDTO);
        PphMedicine pphMedicine = pphMedicineMapper.toEntity(pphMedicineDTO);
        pphMedicine = pphMedicineRepository.save(pphMedicine);
        return pphMedicineMapper.toDto(pphMedicine);
    }

    /**
     * Get all the pphMedicines.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PphMedicineDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PphMedicines");
        return pphMedicineRepository.findAll(pageable)
            .map(pphMedicineMapper::toDto);
    }


    /**
     * Get all the pphMedicines with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<PphMedicineDTO> findAllWithEagerRelationships(Pageable pageable) {
        return pphMedicineRepository.findAllWithEagerRelationships(pageable).map(pphMedicineMapper::toDto);
    }

    /**
     * Get one pphMedicine by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PphMedicineDTO> findOne(Long id) {
        log.debug("Request to get PphMedicine : {}", id);
        return pphMedicineRepository.findOneWithEagerRelationships(id)
            .map(pphMedicineMapper::toDto);
    }

    /**
     * Delete the pphMedicine by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PphMedicine : {}", id);
        pphMedicineRepository.deleteById(id);
    }
}
