package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.domain.PphMedicineForm;
import hu.paninform.startmedsol.repository.PphMedicineFormRepository;
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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.PphMedicineForm}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PphMedicineFormResource {

    private final Logger log = LoggerFactory.getLogger(PphMedicineFormResource.class);

    private static final String ENTITY_NAME = "pphMedicineForm";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PphMedicineFormRepository pphMedicineFormRepository;

    public PphMedicineFormResource(PphMedicineFormRepository pphMedicineFormRepository) {
        this.pphMedicineFormRepository = pphMedicineFormRepository;
    }

    /**
     * {@code POST  /pph-medicine-forms} : Create a new pphMedicineForm.
     *
     * @param pphMedicineForm the pphMedicineForm to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pphMedicineForm, or with status {@code 400 (Bad Request)} if the pphMedicineForm has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pph-medicine-forms")
    public ResponseEntity<PphMedicineForm> createPphMedicineForm(@Valid @RequestBody PphMedicineForm pphMedicineForm) throws URISyntaxException {
        log.debug("REST request to save PphMedicineForm : {}", pphMedicineForm);
        if (pphMedicineForm.getId() != null) {
            throw new BadRequestAlertException("A new pphMedicineForm cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PphMedicineForm result = pphMedicineFormRepository.save(pphMedicineForm);
        return ResponseEntity.created(new URI("/api/pph-medicine-forms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pph-medicine-forms} : Updates an existing pphMedicineForm.
     *
     * @param pphMedicineForm the pphMedicineForm to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pphMedicineForm,
     * or with status {@code 400 (Bad Request)} if the pphMedicineForm is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pphMedicineForm couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pph-medicine-forms")
    public ResponseEntity<PphMedicineForm> updatePphMedicineForm(@Valid @RequestBody PphMedicineForm pphMedicineForm) throws URISyntaxException {
        log.debug("REST request to update PphMedicineForm : {}", pphMedicineForm);
        if (pphMedicineForm.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PphMedicineForm result = pphMedicineFormRepository.save(pphMedicineForm);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pphMedicineForm.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pph-medicine-forms} : get all the pphMedicineForms.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pphMedicineForms in body.
     */
    @GetMapping("/pph-medicine-forms")
    public List<PphMedicineForm> getAllPphMedicineForms() {
        log.debug("REST request to get all PphMedicineForms");
        return pphMedicineFormRepository.findAll();
    }

    /**
     * {@code GET  /pph-medicine-forms/:id} : get the "id" pphMedicineForm.
     *
     * @param id the id of the pphMedicineForm to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pphMedicineForm, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pph-medicine-forms/{id}")
    public ResponseEntity<PphMedicineForm> getPphMedicineForm(@PathVariable Long id) {
        log.debug("REST request to get PphMedicineForm : {}", id);
        Optional<PphMedicineForm> pphMedicineForm = pphMedicineFormRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pphMedicineForm);
    }

    /**
     * {@code DELETE  /pph-medicine-forms/:id} : delete the "id" pphMedicineForm.
     *
     * @param id the id of the pphMedicineForm to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pph-medicine-forms/{id}")
    public ResponseEntity<Void> deletePphMedicineForm(@PathVariable Long id) {
        log.debug("REST request to delete PphMedicineForm : {}", id);
        pphMedicineFormRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
