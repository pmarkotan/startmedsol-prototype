package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.BillingInformationService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.BillingInformationDTO;

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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.BillingInformation}.
 */
@RestController
@RequestMapping("/api")
public class BillingInformationResource {

    private final Logger log = LoggerFactory.getLogger(BillingInformationResource.class);

    private static final String ENTITY_NAME = "billingInformation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BillingInformationService billingInformationService;

    public BillingInformationResource(BillingInformationService billingInformationService) {
        this.billingInformationService = billingInformationService;
    }

    /**
     * {@code POST  /billing-informations} : Create a new billingInformation.
     *
     * @param billingInformationDTO the billingInformationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new billingInformationDTO, or with status {@code 400 (Bad Request)} if the billingInformation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/billing-informations")
    public ResponseEntity<BillingInformationDTO> createBillingInformation(@Valid @RequestBody BillingInformationDTO billingInformationDTO) throws URISyntaxException {
        log.debug("REST request to save BillingInformation : {}", billingInformationDTO);
        if (billingInformationDTO.getId() != null) {
            throw new BadRequestAlertException("A new billingInformation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BillingInformationDTO result = billingInformationService.save(billingInformationDTO);
        return ResponseEntity.created(new URI("/api/billing-informations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /billing-informations} : Updates an existing billingInformation.
     *
     * @param billingInformationDTO the billingInformationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billingInformationDTO,
     * or with status {@code 400 (Bad Request)} if the billingInformationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the billingInformationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/billing-informations")
    public ResponseEntity<BillingInformationDTO> updateBillingInformation(@Valid @RequestBody BillingInformationDTO billingInformationDTO) throws URISyntaxException {
        log.debug("REST request to update BillingInformation : {}", billingInformationDTO);
        if (billingInformationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BillingInformationDTO result = billingInformationService.save(billingInformationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, billingInformationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /billing-informations} : get all the billingInformations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of billingInformations in body.
     */
    @GetMapping("/billing-informations")
    public ResponseEntity<List<BillingInformationDTO>> getAllBillingInformations(Pageable pageable) {
        log.debug("REST request to get a page of BillingInformations");
        Page<BillingInformationDTO> page = billingInformationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /billing-informations/:id} : get the "id" billingInformation.
     *
     * @param id the id of the billingInformationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the billingInformationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/billing-informations/{id}")
    public ResponseEntity<BillingInformationDTO> getBillingInformation(@PathVariable Long id) {
        log.debug("REST request to get BillingInformation : {}", id);
        Optional<BillingInformationDTO> billingInformationDTO = billingInformationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(billingInformationDTO);
    }

    /**
     * {@code DELETE  /billing-informations/:id} : delete the "id" billingInformation.
     *
     * @param id the id of the billingInformationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/billing-informations/{id}")
    public ResponseEntity<Void> deleteBillingInformation(@PathVariable Long id) {
        log.debug("REST request to delete BillingInformation : {}", id);
        billingInformationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
