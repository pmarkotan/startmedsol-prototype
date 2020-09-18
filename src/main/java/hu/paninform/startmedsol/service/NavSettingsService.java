package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.NavSettings;
import hu.paninform.startmedsol.repository.NavSettingsRepository;
import hu.paninform.startmedsol.service.dto.NavSettingsDTO;
import hu.paninform.startmedsol.service.mapper.NavSettingsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link NavSettings}.
 */
@Service
@Transactional
public class NavSettingsService {

    private final Logger log = LoggerFactory.getLogger(NavSettingsService.class);

    private final NavSettingsRepository navSettingsRepository;

    private final NavSettingsMapper navSettingsMapper;

    public NavSettingsService(NavSettingsRepository navSettingsRepository, NavSettingsMapper navSettingsMapper) {
        this.navSettingsRepository = navSettingsRepository;
        this.navSettingsMapper = navSettingsMapper;
    }

    /**
     * Save a navSettings.
     *
     * @param navSettingsDTO the entity to save.
     * @return the persisted entity.
     */
    public NavSettingsDTO save(NavSettingsDTO navSettingsDTO) {
        log.debug("Request to save NavSettings : {}", navSettingsDTO);
        NavSettings navSettings = navSettingsMapper.toEntity(navSettingsDTO);
        navSettings = navSettingsRepository.save(navSettings);
        return navSettingsMapper.toDto(navSettings);
    }

    /**
     * Get all the navSettings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<NavSettingsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NavSettings");
        return navSettingsRepository.findAll(pageable)
            .map(navSettingsMapper::toDto);
    }


    /**
     * Get one navSettings by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NavSettingsDTO> findOne(Long id) {
        log.debug("Request to get NavSettings : {}", id);
        return navSettingsRepository.findById(id)
            .map(navSettingsMapper::toDto);
    }

    /**
     * Delete the navSettings by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NavSettings : {}", id);
        navSettingsRepository.deleteById(id);
    }
}
