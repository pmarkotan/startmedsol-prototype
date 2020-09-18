package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.PersonalData;
import hu.paninform.startmedsol.repository.PersonalDataRepository;
import hu.paninform.startmedsol.service.dto.PersonalDataDTO;
import hu.paninform.startmedsol.service.mapper.PersonalDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PersonalData}.
 */
@Service
@Transactional
public class PersonalDataService {

    private final Logger log = LoggerFactory.getLogger(PersonalDataService.class);

    private final PersonalDataRepository personalDataRepository;

    private final PersonalDataMapper personalDataMapper;

    public PersonalDataService(PersonalDataRepository personalDataRepository, PersonalDataMapper personalDataMapper) {
        this.personalDataRepository = personalDataRepository;
        this.personalDataMapper = personalDataMapper;
    }

    /**
     * Save a personalData.
     *
     * @param personalDataDTO the entity to save.
     * @return the persisted entity.
     */
    public PersonalDataDTO save(PersonalDataDTO personalDataDTO) {
        log.debug("Request to save PersonalData : {}", personalDataDTO);
        PersonalData personalData = personalDataMapper.toEntity(personalDataDTO);
        personalData = personalDataRepository.save(personalData);
        return personalDataMapper.toDto(personalData);
    }

    /**
     * Get all the personalData.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PersonalDataDTO> findAll() {
        log.debug("Request to get all PersonalData");
        return personalDataRepository.findAll().stream()
            .map(personalDataMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one personalData by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PersonalDataDTO> findOne(Long id) {
        log.debug("Request to get PersonalData : {}", id);
        return personalDataRepository.findById(id)
            .map(personalDataMapper::toDto);
    }

    /**
     * Delete the personalData by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PersonalData : {}", id);
        personalDataRepository.deleteById(id);
    }
}
