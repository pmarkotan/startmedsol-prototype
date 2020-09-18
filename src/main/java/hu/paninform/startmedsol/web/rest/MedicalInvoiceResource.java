package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.MedicalInvoiceService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.MedicalInvoiceDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link hu.paninform.startmedsol.domain.MedicalInvoice}.
 */
@RestController
@RequestMapping("/api")
public class MedicalInvoiceResource {

    private final Logger log = LoggerFactory.getLogger(MedicalInvoiceResource.class);

    private static final String ENTITY_NAME = "medicalInvoice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MedicalInvoiceService medicalInvoiceService;

    public MedicalInvoiceResource(MedicalInvoiceService medicalInvoiceService) {
        this.medicalInvoiceService = medicalInvoiceService;
    }

    /**
     * {@code POST  /medical-invoices} : Create a new medicalInvoice.
     *
     * @param medicalInvoiceDTO the medicalInvoiceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new medicalInvoiceDTO, or with status {@code 400 (Bad Request)} if the medicalInvoice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/medical-invoices")
    public ResponseEntity<MedicalInvoiceDTO> createMedicalInvoice(@Valid @RequestBody MedicalInvoiceDTO medicalInvoiceDTO) throws URISyntaxException {
        log.debug("REST request to save MedicalInvoice : {}", medicalInvoiceDTO);
        if (medicalInvoiceDTO.getId() != null) {
            throw new BadRequestAlertException("A new medicalInvoice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MedicalInvoiceDTO result = medicalInvoiceService.save(medicalInvoiceDTO);
        return ResponseEntity.created(new URI("/api/medical-invoices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /medical-invoices} : Updates an existing medicalInvoice.
     *
     * @param medicalInvoiceDTO the medicalInvoiceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated medicalInvoiceDTO,
     * or with status {@code 400 (Bad Request)} if the medicalInvoiceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the medicalInvoiceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/medical-invoices")
    public ResponseEntity<MedicalInvoiceDTO> updateMedicalInvoice(@Valid @RequestBody MedicalInvoiceDTO medicalInvoiceDTO) throws URISyntaxException {
        log.debug("REST request to update MedicalInvoice : {}", medicalInvoiceDTO);
        if (medicalInvoiceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MedicalInvoiceDTO result = medicalInvoiceService.save(medicalInvoiceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, medicalInvoiceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /medical-invoices} : get all the medicalInvoices.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of medicalInvoices in body.
     */
    @GetMapping("/medical-invoices")
    public ResponseEntity<List<MedicalInvoiceDTO>> getAllMedicalInvoices(Pageable pageable) {
        log.debug("REST request to get a page of MedicalInvoices");
        Page<MedicalInvoiceDTO> page = medicalInvoiceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /medical-invoices/:id} : get the "id" medicalInvoice.
     *
     * @param id the id of the medicalInvoiceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the medicalInvoiceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/medical-invoices/{id}")
    public ResponseEntity<MedicalInvoiceDTO> getMedicalInvoice(@PathVariable Long id) {
        log.debug("REST request to get MedicalInvoice : {}", id);
        Optional<MedicalInvoiceDTO> medicalInvoiceDTO = medicalInvoiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(medicalInvoiceDTO);
    }

    /**
     * {@code DELETE  /medical-invoices/:id} : delete the "id" medicalInvoice.
     *
     * @param id the id of the medicalInvoiceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/medical-invoices/{id}")
    public ResponseEntity<Void> deleteMedicalInvoice(@PathVariable Long id) {
        log.debug("REST request to delete MedicalInvoice : {}", id);
        medicalInvoiceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
