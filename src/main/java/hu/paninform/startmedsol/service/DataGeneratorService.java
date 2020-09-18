package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.DataGenerator;
import hu.paninform.startmedsol.repository.DataGeneratorRepository;
import hu.paninform.startmedsol.service.dto.DataGeneratorDTO;
import hu.paninform.startmedsol.service.mapper.DataGeneratorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DataGenerator}.
 */
@Service
@Transactional
public class DataGeneratorService {

    private final Logger log = LoggerFactory.getLogger(DataGeneratorService.class);

    private final DataGeneratorRepository dataGeneratorRepository;

    private final DataGeneratorMapper dataGeneratorMapper;

    public DataGeneratorService(DataGeneratorRepository dataGeneratorRepository, DataGeneratorMapper dataGeneratorMapper) {
        this.dataGeneratorRepository = dataGeneratorRepository;
        this.dataGeneratorMapper = dataGeneratorMapper;
    }

    /**
     * Save a dataGenerator.
     *
     * @param dataGeneratorDTO the entity to save.
     * @return the persisted entity.
     */
    public DataGeneratorDTO save(DataGeneratorDTO dataGeneratorDTO) {
        log.debug("Request to save DataGenerator : {}", dataGeneratorDTO);
        DataGenerator dataGenerator = dataGeneratorMapper.toEntity(dataGeneratorDTO);
        dataGenerator = dataGeneratorRepository.save(dataGenerator);
        return dataGeneratorMapper.toDto(dataGenerator);
    }

    /**
     * Get all the dataGenerators.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DataGeneratorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DataGenerators");
        return dataGeneratorRepository.findAll(pageable)
            .map(dataGeneratorMapper::toDto);
    }


    /**
     * Get one dataGenerator by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DataGeneratorDTO> findOne(Long id) {
        log.debug("Request to get DataGenerator : {}", id);
        return dataGeneratorRepository.findById(id)
            .map(dataGeneratorMapper::toDto);
    }

    /**
     * Delete the dataGenerator by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DataGenerator : {}", id);
        dataGeneratorRepository.deleteById(id);
    }
}
