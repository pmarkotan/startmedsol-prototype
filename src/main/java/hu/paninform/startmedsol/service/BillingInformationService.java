package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.BillingInformation;
import hu.paninform.startmedsol.repository.BillingInformationRepository;
import hu.paninform.startmedsol.service.dto.BillingInformationDTO;
import hu.paninform.startmedsol.service.mapper.BillingInformationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BillingInformation}.
 */
@Service
@Transactional
public class BillingInformationService {

    private final Logger log = LoggerFactory.getLogger(BillingInformationService.class);

    private final BillingInformationRepository billingInformationRepository;

    private final BillingInformationMapper billingInformationMapper;

    public BillingInformationService(BillingInformationRepository billingInformationRepository, BillingInformationMapper billingInformationMapper) {
        this.billingInformationRepository = billingInformationRepository;
        this.billingInformationMapper = billingInformationMapper;
    }

    /**
     * Save a billingInformation.
     *
     * @param billingInformationDTO the entity to save.
     * @return the persisted entity.
     */
    public BillingInformationDTO save(BillingInformationDTO billingInformationDTO) {
        log.debug("Request to save BillingInformation : {}", billingInformationDTO);
        BillingInformation billingInformation = billingInformationMapper.toEntity(billingInformationDTO);
        billingInformation = billingInformationRepository.save(billingInformation);
        return billingInformationMapper.toDto(billingInformation);
    }

    /**
     * Get all the billingInformations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BillingInformationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BillingInformations");
        return billingInformationRepository.findAll(pageable)
            .map(billingInformationMapper::toDto);
    }


    /**
     * Get one billingInformation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BillingInformationDTO> findOne(Long id) {
        log.debug("Request to get BillingInformation : {}", id);
        return billingInformationRepository.findById(id)
            .map(billingInformationMapper::toDto);
    }

    /**
     * Delete the billingInformation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BillingInformation : {}", id);
        billingInformationRepository.deleteById(id);
    }
}
