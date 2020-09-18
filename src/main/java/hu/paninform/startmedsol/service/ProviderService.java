package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.Provider;
import hu.paninform.startmedsol.repository.ProviderRepository;
import hu.paninform.startmedsol.service.dto.ProviderDTO;
import hu.paninform.startmedsol.service.mapper.ProviderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Provider}.
 */
@Service
@Transactional
public class ProviderService {

    private final Logger log = LoggerFactory.getLogger(ProviderService.class);

    private final ProviderRepository providerRepository;

    private final ProviderMapper providerMapper;

    public ProviderService(ProviderRepository providerRepository, ProviderMapper providerMapper) {
        this.providerRepository = providerRepository;
        this.providerMapper = providerMapper;
    }

    /**
     * Save a provider.
     *
     * @param providerDTO the entity to save.
     * @return the persisted entity.
     */
    public ProviderDTO save(ProviderDTO providerDTO) {
        log.debug("Request to save Provider : {}", providerDTO);
        Provider provider = providerMapper.toEntity(providerDTO);
        provider = providerRepository.save(provider);
        return providerMapper.toDto(provider);
    }

    /**
     * Get all the providers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ProviderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Providers");
        return providerRepository.findAll(pageable)
            .map(providerMapper::toDto);
    }


    /**
     * Get all the providers with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<ProviderDTO> findAllWithEagerRelationships(Pageable pageable) {
        return providerRepository.findAllWithEagerRelationships(pageable).map(providerMapper::toDto);
    }

    /**
     * Get one provider by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProviderDTO> findOne(Long id) {
        log.debug("Request to get Provider : {}", id);
        return providerRepository.findOneWithEagerRelationships(id)
            .map(providerMapper::toDto);
    }

    /**
     * Delete the provider by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Provider : {}", id);
        providerRepository.deleteById(id);
    }
}
