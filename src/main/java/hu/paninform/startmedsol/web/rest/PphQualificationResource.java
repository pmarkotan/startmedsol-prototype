package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.domain.PphQualification;
import hu.paninform.startmedsol.repository.PphQualificationRepository;
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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.PphQualification}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PphQualificationResource {

    private final Logger log = LoggerFactory.getLogger(PphQualificationResource.class);

    private static final String ENTITY_NAME = "pphQualification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PphQualificationRepository pphQualificationRepository;

    public PphQualificationResource(PphQualificationRepository pphQualificationRepository) {
        this.pphQualificationRepository = pphQualificationRepository;
    }

    /**
     * {@code POST  /pph-qualifications} : Create a new pphQualification.
     *
     * @param pphQualification the pphQualification to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pphQualification, or with status {@code 400 (Bad Request)} if the pphQualification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pph-qualifications")
    public ResponseEntity<PphQualification> createPphQualification(@Valid @RequestBody PphQualification pphQualification) throws URISyntaxException {
        log.debug("REST request to save PphQualification : {}", pphQualification);
        if (pphQualification.getId() != null) {
            throw new BadRequestAlertException("A new pphQualification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PphQualification result = pphQualificationRepository.save(pphQualification);
        return ResponseEntity.created(new URI("/api/pph-qualifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pph-qualifications} : Updates an existing pphQualification.
     *
     * @param pphQualification the pphQualification to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pphQualification,
     * or with status {@code 400 (Bad Request)} if the pphQualification is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pphQualification couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pph-qualifications")
    public ResponseEntity<PphQualification> updatePphQualification(@Valid @RequestBody PphQualification pphQualification) throws URISyntaxException {
        log.debug("REST request to update PphQualification : {}", pphQualification);
        if (pphQualification.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PphQualification result = pphQualificationRepository.save(pphQualification);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pphQualification.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pph-qualifications} : get all the pphQualifications.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pphQualifications in body.
     */
    @GetMapping("/pph-qualifications")
    public List<PphQualification> getAllPphQualifications() {
        log.debug("REST request to get all PphQualifications");
        return pphQualificationRepository.findAll();
    }

    /**
     * {@code GET  /pph-qualifications/:id} : get the "id" pphQualification.
     *
     * @param id the id of the pphQualification to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pphQualification, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pph-qualifications/{id}")
    public ResponseEntity<PphQualification> getPphQualification(@PathVariable Long id) {
        log.debug("REST request to get PphQualification : {}", id);
        Optional<PphQualification> pphQualification = pphQualificationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pphQualification);
    }

    /**
     * {@code DELETE  /pph-qualifications/:id} : delete the "id" pphQualification.
     *
     * @param id the id of the pphQualification to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pph-qualifications/{id}")
    public ResponseEntity<Void> deletePphQualification(@PathVariable Long id) {
        log.debug("REST request to delete PphQualification : {}", id);
        pphQualificationRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
