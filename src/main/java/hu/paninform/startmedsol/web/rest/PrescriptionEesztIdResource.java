package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.domain.PrescriptionEesztId;
import hu.paninform.startmedsol.repository.PrescriptionEesztIdRepository;
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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.PrescriptionEesztId}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PrescriptionEesztIdResource {

    private final Logger log = LoggerFactory.getLogger(PrescriptionEesztIdResource.class);

    private static final String ENTITY_NAME = "prescriptionEesztId";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrescriptionEesztIdRepository prescriptionEesztIdRepository;

    public PrescriptionEesztIdResource(PrescriptionEesztIdRepository prescriptionEesztIdRepository) {
        this.prescriptionEesztIdRepository = prescriptionEesztIdRepository;
    }

    /**
     * {@code POST  /prescription-eeszt-ids} : Create a new prescriptionEesztId.
     *
     * @param prescriptionEesztId the prescriptionEesztId to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new prescriptionEesztId, or with status {@code 400 (Bad Request)} if the prescriptionEesztId has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/prescription-eeszt-ids")
    public ResponseEntity<PrescriptionEesztId> createPrescriptionEesztId(@Valid @RequestBody PrescriptionEesztId prescriptionEesztId) throws URISyntaxException {
        log.debug("REST request to save PrescriptionEesztId : {}", prescriptionEesztId);
        if (prescriptionEesztId.getId() != null) {
            throw new BadRequestAlertException("A new prescriptionEesztId cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrescriptionEesztId result = prescriptionEesztIdRepository.save(prescriptionEesztId);
        return ResponseEntity.created(new URI("/api/prescription-eeszt-ids/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /prescription-eeszt-ids} : Updates an existing prescriptionEesztId.
     *
     * @param prescriptionEesztId the prescriptionEesztId to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated prescriptionEesztId,
     * or with status {@code 400 (Bad Request)} if the prescriptionEesztId is not valid,
     * or with status {@code 500 (Internal Server Error)} if the prescriptionEesztId couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/prescription-eeszt-ids")
    public ResponseEntity<PrescriptionEesztId> updatePrescriptionEesztId(@Valid @RequestBody PrescriptionEesztId prescriptionEesztId) throws URISyntaxException {
        log.debug("REST request to update PrescriptionEesztId : {}", prescriptionEesztId);
        if (prescriptionEesztId.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PrescriptionEesztId result = prescriptionEesztIdRepository.save(prescriptionEesztId);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, prescriptionEesztId.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /prescription-eeszt-ids} : get all the prescriptionEesztIds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of prescriptionEesztIds in body.
     */
    @GetMapping("/prescription-eeszt-ids")
    public List<PrescriptionEesztId> getAllPrescriptionEesztIds() {
        log.debug("REST request to get all PrescriptionEesztIds");
        return prescriptionEesztIdRepository.findAll();
    }

    /**
     * {@code GET  /prescription-eeszt-ids/:id} : get the "id" prescriptionEesztId.
     *
     * @param id the id of the prescriptionEesztId to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the prescriptionEesztId, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/prescription-eeszt-ids/{id}")
    public ResponseEntity<PrescriptionEesztId> getPrescriptionEesztId(@PathVariable Long id) {
        log.debug("REST request to get PrescriptionEesztId : {}", id);
        Optional<PrescriptionEesztId> prescriptionEesztId = prescriptionEesztIdRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(prescriptionEesztId);
    }

    /**
     * {@code DELETE  /prescription-eeszt-ids/:id} : delete the "id" prescriptionEesztId.
     *
     * @param id the id of the prescriptionEesztId to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/prescription-eeszt-ids/{id}")
    public ResponseEntity<Void> deletePrescriptionEesztId(@PathVariable Long id) {
        log.debug("REST request to delete PrescriptionEesztId : {}", id);
        prescriptionEesztIdRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
