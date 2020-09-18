package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.MedicalInvoice;
import hu.paninform.startmedsol.repository.MedicalInvoiceRepository;
import hu.paninform.startmedsol.service.dto.MedicalInvoiceDTO;
import hu.paninform.startmedsol.service.mapper.MedicalInvoiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MedicalInvoice}.
 */
@Service
@Transactional
public class MedicalInvoiceService {

    private final Logger log = LoggerFactory.getLogger(MedicalInvoiceService.class);

    private final MedicalInvoiceRepository medicalInvoiceRepository;

    private final MedicalInvoiceMapper medicalInvoiceMapper;

    public MedicalInvoiceService(MedicalInvoiceRepository medicalInvoiceRepository, MedicalInvoiceMapper medicalInvoiceMapper) {
        this.medicalInvoiceRepository = medicalInvoiceRepository;
        this.medicalInvoiceMapper = medicalInvoiceMapper;
    }

    /**
     * Save a medicalInvoice.
     *
     * @param medicalInvoiceDTO the entity to save.
     * @return the persisted entity.
     */
    public MedicalInvoiceDTO save(MedicalInvoiceDTO medicalInvoiceDTO) {
        log.debug("Request to save MedicalInvoice : {}", medicalInvoiceDTO);
        MedicalInvoice medicalInvoice = medicalInvoiceMapper.toEntity(medicalInvoiceDTO);
        medicalInvoice = medicalInvoiceRepository.save(medicalInvoice);
        return medicalInvoiceMapper.toDto(medicalInvoice);
    }

    /**
     * Get all the medicalInvoices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MedicalInvoiceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MedicalInvoices");
        return medicalInvoiceRepository.findAll(pageable)
            .map(medicalInvoiceMapper::toDto);
    }


    /**
     * Get one medicalInvoice by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MedicalInvoiceDTO> findOne(Long id) {
        log.debug("Request to get MedicalInvoice : {}", id);
        return medicalInvoiceRepository.findById(id)
            .map(medicalInvoiceMapper::toDto);
    }

    /**
     * Delete the medicalInvoice by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MedicalInvoice : {}", id);
        medicalInvoiceRepository.deleteById(id);
    }
}
