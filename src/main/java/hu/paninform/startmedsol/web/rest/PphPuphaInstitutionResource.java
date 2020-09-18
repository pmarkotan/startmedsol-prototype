package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.domain.PphPuphaInstitution;
import hu.paninform.startmedsol.repository.PphPuphaInstitutionRepository;
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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.PphPuphaInstitution}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PphPuphaInstitutionResource {

    private final Logger log = LoggerFactory.getLogger(PphPuphaInstitutionResource.class);

    private static final String ENTITY_NAME = "pphPuphaInstitution";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PphPuphaInstitutionRepository pphPuphaInstitutionRepository;

    public PphPuphaInstitutionResource(PphPuphaInstitutionRepository pphPuphaInstitutionRepository) {
        this.pphPuphaInstitutionRepository = pphPuphaInstitutionRepository;
    }

    /**
     * {@code POST  /pph-pupha-institutions} : Create a new pphPuphaInstitution.
     *
     * @param pphPuphaInstitution the pphPuphaInstitution to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pphPuphaInstitution, or with status {@code 400 (Bad Request)} if the pphPuphaInstitution has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pph-pupha-institutions")
    public ResponseEntity<PphPuphaInstitution> createPphPuphaInstitution(@Valid @RequestBody PphPuphaInstitution pphPuphaInstitution) throws URISyntaxException {
        log.debug("REST request to save PphPuphaInstitution : {}", pphPuphaInstitution);
        if (pphPuphaInstitution.getId() != null) {
            throw new BadRequestAlertException("A new pphPuphaInstitution cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PphPuphaInstitution result = pphPuphaInstitutionRepository.save(pphPuphaInstitution);
        return ResponseEntity.created(new URI("/api/pph-pupha-institutions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pph-pupha-institutions} : Updates an existing pphPuphaInstitution.
     *
     * @param pphPuphaInstitution the pphPuphaInstitution to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pphPuphaInstitution,
     * or with status {@code 400 (Bad Request)} if the pphPuphaInstitution is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pphPuphaInstitution couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pph-pupha-institutions")
    public ResponseEntity<PphPuphaInstitution> updatePphPuphaInstitution(@Valid @RequestBody PphPuphaInstitution pphPuphaInstitution) throws URISyntaxException {
        log.debug("REST request to update PphPuphaInstitution : {}", pphPuphaInstitution);
        if (pphPuphaInstitution.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PphPuphaInstitution result = pphPuphaInstitutionRepository.save(pphPuphaInstitution);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pphPuphaInstitution.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pph-pupha-institutions} : get all the pphPuphaInstitutions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pphPuphaInstitutions in body.
     */
    @GetMapping("/pph-pupha-institutions")
    public List<PphPuphaInstitution> getAllPphPuphaInstitutions() {
        log.debug("REST request to get all PphPuphaInstitutions");
        return pphPuphaInstitutionRepository.findAll();
    }

    /**
     * {@code GET  /pph-pupha-institutions/:id} : get the "id" pphPuphaInstitution.
     *
     * @param id the id of the pphPuphaInstitution to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pphPuphaInstitution, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pph-pupha-institutions/{id}")
    public ResponseEntity<PphPuphaInstitution> getPphPuphaInstitution(@PathVariable Long id) {
        log.debug("REST request to get PphPuphaInstitution : {}", id);
        Optional<PphPuphaInstitution> pphPuphaInstitution = pphPuphaInstitutionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pphPuphaInstitution);
    }

    /**
     * {@code DELETE  /pph-pupha-institutions/:id} : delete the "id" pphPuphaInstitution.
     *
     * @param id the id of the pphPuphaInstitution to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pph-pupha-institutions/{id}")
    public ResponseEntity<Void> deletePphPuphaInstitution(@PathVariable Long id) {
        log.debug("REST request to delete PphPuphaInstitution : {}", id);
        pphPuphaInstitutionRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
