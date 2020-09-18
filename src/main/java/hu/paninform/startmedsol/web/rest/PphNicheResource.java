package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.domain.PphNiche;
import hu.paninform.startmedsol.repository.PphNicheRepository;
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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.PphNiche}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PphNicheResource {

    private final Logger log = LoggerFactory.getLogger(PphNicheResource.class);

    private static final String ENTITY_NAME = "pphNiche";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PphNicheRepository pphNicheRepository;

    public PphNicheResource(PphNicheRepository pphNicheRepository) {
        this.pphNicheRepository = pphNicheRepository;
    }

    /**
     * {@code POST  /pph-niches} : Create a new pphNiche.
     *
     * @param pphNiche the pphNiche to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pphNiche, or with status {@code 400 (Bad Request)} if the pphNiche has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pph-niches")
    public ResponseEntity<PphNiche> createPphNiche(@Valid @RequestBody PphNiche pphNiche) throws URISyntaxException {
        log.debug("REST request to save PphNiche : {}", pphNiche);
        if (pphNiche.getId() != null) {
            throw new BadRequestAlertException("A new pphNiche cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PphNiche result = pphNicheRepository.save(pphNiche);
        return ResponseEntity.created(new URI("/api/pph-niches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pph-niches} : Updates an existing pphNiche.
     *
     * @param pphNiche the pphNiche to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pphNiche,
     * or with status {@code 400 (Bad Request)} if the pphNiche is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pphNiche couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pph-niches")
    public ResponseEntity<PphNiche> updatePphNiche(@Valid @RequestBody PphNiche pphNiche) throws URISyntaxException {
        log.debug("REST request to update PphNiche : {}", pphNiche);
        if (pphNiche.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PphNiche result = pphNicheRepository.save(pphNiche);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pphNiche.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pph-niches} : get all the pphNiches.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pphNiches in body.
     */
    @GetMapping("/pph-niches")
    public List<PphNiche> getAllPphNiches() {
        log.debug("REST request to get all PphNiches");
        return pphNicheRepository.findAll();
    }

    /**
     * {@code GET  /pph-niches/:id} : get the "id" pphNiche.
     *
     * @param id the id of the pphNiche to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pphNiche, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pph-niches/{id}")
    public ResponseEntity<PphNiche> getPphNiche(@PathVariable Long id) {
        log.debug("REST request to get PphNiche : {}", id);
        Optional<PphNiche> pphNiche = pphNicheRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pphNiche);
    }

    /**
     * {@code DELETE  /pph-niches/:id} : delete the "id" pphNiche.
     *
     * @param id the id of the pphNiche to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pph-niches/{id}")
    public ResponseEntity<Void> deletePphNiche(@PathVariable Long id) {
        log.debug("REST request to delete PphNiche : {}", id);
        pphNicheRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
