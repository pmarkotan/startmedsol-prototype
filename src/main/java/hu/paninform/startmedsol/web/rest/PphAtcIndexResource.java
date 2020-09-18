package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.domain.PphAtcIndex;
import hu.paninform.startmedsol.repository.PphAtcIndexRepository;
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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.PphAtcIndex}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PphAtcIndexResource {

    private final Logger log = LoggerFactory.getLogger(PphAtcIndexResource.class);

    private static final String ENTITY_NAME = "pphAtcIndex";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PphAtcIndexRepository pphAtcIndexRepository;

    public PphAtcIndexResource(PphAtcIndexRepository pphAtcIndexRepository) {
        this.pphAtcIndexRepository = pphAtcIndexRepository;
    }

    /**
     * {@code POST  /pph-atc-indices} : Create a new pphAtcIndex.
     *
     * @param pphAtcIndex the pphAtcIndex to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pphAtcIndex, or with status {@code 400 (Bad Request)} if the pphAtcIndex has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pph-atc-indices")
    public ResponseEntity<PphAtcIndex> createPphAtcIndex(@Valid @RequestBody PphAtcIndex pphAtcIndex) throws URISyntaxException {
        log.debug("REST request to save PphAtcIndex : {}", pphAtcIndex);
        if (pphAtcIndex.getId() != null) {
            throw new BadRequestAlertException("A new pphAtcIndex cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PphAtcIndex result = pphAtcIndexRepository.save(pphAtcIndex);
        return ResponseEntity.created(new URI("/api/pph-atc-indices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pph-atc-indices} : Updates an existing pphAtcIndex.
     *
     * @param pphAtcIndex the pphAtcIndex to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pphAtcIndex,
     * or with status {@code 400 (Bad Request)} if the pphAtcIndex is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pphAtcIndex couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pph-atc-indices")
    public ResponseEntity<PphAtcIndex> updatePphAtcIndex(@Valid @RequestBody PphAtcIndex pphAtcIndex) throws URISyntaxException {
        log.debug("REST request to update PphAtcIndex : {}", pphAtcIndex);
        if (pphAtcIndex.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PphAtcIndex result = pphAtcIndexRepository.save(pphAtcIndex);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pphAtcIndex.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pph-atc-indices} : get all the pphAtcIndices.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pphAtcIndices in body.
     */
    @GetMapping("/pph-atc-indices")
    public List<PphAtcIndex> getAllPphAtcIndices() {
        log.debug("REST request to get all PphAtcIndices");
        return pphAtcIndexRepository.findAll();
    }

    /**
     * {@code GET  /pph-atc-indices/:id} : get the "id" pphAtcIndex.
     *
     * @param id the id of the pphAtcIndex to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pphAtcIndex, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pph-atc-indices/{id}")
    public ResponseEntity<PphAtcIndex> getPphAtcIndex(@PathVariable Long id) {
        log.debug("REST request to get PphAtcIndex : {}", id);
        Optional<PphAtcIndex> pphAtcIndex = pphAtcIndexRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pphAtcIndex);
    }

    /**
     * {@code DELETE  /pph-atc-indices/:id} : delete the "id" pphAtcIndex.
     *
     * @param id the id of the pphAtcIndex to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pph-atc-indices/{id}")
    public ResponseEntity<Void> deletePphAtcIndex(@PathVariable Long id) {
        log.debug("REST request to delete PphAtcIndex : {}", id);
        pphAtcIndexRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
