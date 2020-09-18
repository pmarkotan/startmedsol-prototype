package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.PphMedicineQualifiedName;
import hu.paninform.startmedsol.repository.PphMedicineQualifiedNameRepository;
import hu.paninform.startmedsol.service.dto.PphMedicineQualifiedNameDTO;
import hu.paninform.startmedsol.service.mapper.PphMedicineQualifiedNameMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PphMedicineQualifiedName}.
 */
@Service
@Transactional
public class PphMedicineQualifiedNameService {

    private final Logger log = LoggerFactory.getLogger(PphMedicineQualifiedNameService.class);

    private final PphMedicineQualifiedNameRepository pphMedicineQualifiedNameRepository;

    private final PphMedicineQualifiedNameMapper pphMedicineQualifiedNameMapper;

    public PphMedicineQualifiedNameService(PphMedicineQualifiedNameRepository pphMedicineQualifiedNameRepository, PphMedicineQualifiedNameMapper pphMedicineQualifiedNameMapper) {
        this.pphMedicineQualifiedNameRepository = pphMedicineQualifiedNameRepository;
        this.pphMedicineQualifiedNameMapper = pphMedicineQualifiedNameMapper;
    }

    /**
     * Save a pphMedicineQualifiedName.
     *
     * @param pphMedicineQualifiedNameDTO the entity to save.
     * @return the persisted entity.
     */
    public PphMedicineQualifiedNameDTO save(PphMedicineQualifiedNameDTO pphMedicineQualifiedNameDTO) {
        log.debug("Request to save PphMedicineQualifiedName : {}", pphMedicineQualifiedNameDTO);
        PphMedicineQualifiedName pphMedicineQualifiedName = pphMedicineQualifiedNameMapper.toEntity(pphMedicineQualifiedNameDTO);
        pphMedicineQualifiedName = pphMedicineQualifiedNameRepository.save(pphMedicineQualifiedName);
        return pphMedicineQualifiedNameMapper.toDto(pphMedicineQualifiedName);
    }

    /**
     * Get all the pphMedicineQualifiedNames.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PphMedicineQualifiedNameDTO> findAll() {
        log.debug("Request to get all PphMedicineQualifiedNames");
        return pphMedicineQualifiedNameRepository.findAll().stream()
            .map(pphMedicineQualifiedNameMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one pphMedicineQualifiedName by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PphMedicineQualifiedNameDTO> findOne(Long id) {
        log.debug("Request to get PphMedicineQualifiedName : {}", id);
        return pphMedicineQualifiedNameRepository.findById(id)
            .map(pphMedicineQualifiedNameMapper::toDto);
    }

    /**
     * Delete the pphMedicineQualifiedName by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PphMedicineQualifiedName : {}", id);
        pphMedicineQualifiedNameRepository.deleteById(id);
    }
}
