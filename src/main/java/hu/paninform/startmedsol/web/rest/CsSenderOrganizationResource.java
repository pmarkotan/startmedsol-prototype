package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.domain.CsSenderOrganization;
import hu.paninform.startmedsol.repository.CsSenderOrganizationRepository;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link hu.paninform.startmedsol.domain.CsSenderOrganization}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CsSenderOrganizationResource {

    private final Logger log = LoggerFactory.getLogger(CsSenderOrganizationResource.class);

    private static final String ENTITY_NAME = "csSenderOrganization";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CsSenderOrganizationRepository csSenderOrganizationRepository;

    public CsSenderOrganizationResource(CsSenderOrganizationRepository csSenderOrganizationRepository) {
        this.csSenderOrganizationRepository = csSenderOrganizationRepository;
    }

    /**
     * {@code POST  /cs-sender-organizations} : Create a new csSenderOrganization.
     *
     * @param csSenderOrganization the csSenderOrganization to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new csSenderOrganization, or with status {@code 400 (Bad Request)} if the csSenderOrganization has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cs-sender-organizations")
    public ResponseEntity<CsSenderOrganization> createCsSenderOrganization(@Valid @RequestBody CsSenderOrganization csSenderOrganization) throws URISyntaxException {
        log.debug("REST request to save CsSenderOrganization : {}", csSenderOrganization);
        if (csSenderOrganization.getId() != null) {
            throw new BadRequestAlertException("A new csSenderOrganization cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CsSenderOrganization result = csSenderOrganizationRepository.save(csSenderOrganization);
        return ResponseEntity.created(new URI("/api/cs-sender-organizations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cs-sender-organizations} : Updates an existing csSenderOrganization.
     *
     * @param csSenderOrganization the csSenderOrganization to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated csSenderOrganization,
     * or with status {@code 400 (Bad Request)} if the csSenderOrganization is not valid,
     * or with status {@code 500 (Internal Server Error)} if the csSenderOrganization couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cs-sender-organizations")
    public ResponseEntity<CsSenderOrganization> updateCsSenderOrganization(@Valid @RequestBody CsSenderOrganization csSenderOrganization) throws URISyntaxException {
        log.debug("REST request to update CsSenderOrganization : {}", csSenderOrganization);
        if (csSenderOrganization.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CsSenderOrganization result = csSenderOrganizationRepository.save(csSenderOrganization);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, csSenderOrganization.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cs-sender-organizations} : get all the csSenderOrganizations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of csSenderOrganizations in body.
     */
    @GetMapping("/cs-sender-organizations")
    public List<CsSenderOrganization> getAllCsSenderOrganizations() {
        log.debug("REST request to get all CsSenderOrganizations");
        return csSenderOrganizationRepository.findAll();
    }

    /**
     * {@code GET  /cs-sender-organizations/:id} : get the "id" csSenderOrganization.
     *
     * @param id the id of the csSenderOrganization to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the csSenderOrganization, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cs-sender-organizations/{id}")
    public ResponseEntity<CsSenderOrganization> getCsSenderOrganization(@PathVariable Long id) {
        log.debug("REST request to get CsSenderOrganization : {}", id);
        Optional<CsSenderOrganization> csSenderOrganization = csSenderOrganizationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(csSenderOrganization);
    }

    /**
     * {@code DELETE  /cs-sender-organizations/:id} : delete the "id" csSenderOrganization.
     *
     * @param id the id of the csSenderOrganization to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cs-sender-organizations/{id}")
    public ResponseEntity<Void> deleteCsSenderOrganization(@PathVariable Long id) {
        log.debug("REST request to delete CsSenderOrganization : {}", id);
        csSenderOrganizationRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
