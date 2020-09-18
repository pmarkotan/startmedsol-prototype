package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.domain.PphSubsidy;
import hu.paninform.startmedsol.repository.PphSubsidyRepository;
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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.PphSubsidy}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PphSubsidyResource {

    private final Logger log = LoggerFactory.getLogger(PphSubsidyResource.class);

    private static final String ENTITY_NAME = "pphSubsidy";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PphSubsidyRepository pphSubsidyRepository;

    public PphSubsidyResource(PphSubsidyRepository pphSubsidyRepository) {
        this.pphSubsidyRepository = pphSubsidyRepository;
    }

    /**
     * {@code POST  /pph-subsidies} : Create a new pphSubsidy.
     *
     * @param pphSubsidy the pphSubsidy to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pphSubsidy, or with status {@code 400 (Bad Request)} if the pphSubsidy has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pph-subsidies")
    public ResponseEntity<PphSubsidy> createPphSubsidy(@Valid @RequestBody PphSubsidy pphSubsidy) throws URISyntaxException {
        log.debug("REST request to save PphSubsidy : {}", pphSubsidy);
        if (pphSubsidy.getId() != null) {
            throw new BadRequestAlertException("A new pphSubsidy cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PphSubsidy result = pphSubsidyRepository.save(pphSubsidy);
        return ResponseEntity.created(new URI("/api/pph-subsidies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pph-subsidies} : Updates an existing pphSubsidy.
     *
     * @param pphSubsidy the pphSubsidy to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pphSubsidy,
     * or with status {@code 400 (Bad Request)} if the pphSubsidy is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pphSubsidy couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pph-subsidies")
    public ResponseEntity<PphSubsidy> updatePphSubsidy(@Valid @RequestBody PphSubsidy pphSubsidy) throws URISyntaxException {
        log.debug("REST request to update PphSubsidy : {}", pphSubsidy);
        if (pphSubsidy.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PphSubsidy result = pphSubsidyRepository.save(pphSubsidy);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pphSubsidy.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pph-subsidies} : get all the pphSubsidies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pphSubsidies in body.
     */
    @GetMapping("/pph-subsidies")
    public List<PphSubsidy> getAllPphSubsidies() {
        log.debug("REST request to get all PphSubsidies");
        return pphSubsidyRepository.findAll();
    }

    /**
     * {@code GET  /pph-subsidies/:id} : get the "id" pphSubsidy.
     *
     * @param id the id of the pphSubsidy to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pphSubsidy, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pph-subsidies/{id}")
    public ResponseEntity<PphSubsidy> getPphSubsidy(@PathVariable Long id) {
        log.debug("REST request to get PphSubsidy : {}", id);
        Optional<PphSubsidy> pphSubsidy = pphSubsidyRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pphSubsidy);
    }

    /**
     * {@code DELETE  /pph-subsidies/:id} : delete the "id" pphSubsidy.
     *
     * @param id the id of the pphSubsidy to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pph-subsidies/{id}")
    public ResponseEntity<Void> deletePphSubsidy(@PathVariable Long id) {
        log.debug("REST request to delete PphSubsidy : {}", id);
        pphSubsidyRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
