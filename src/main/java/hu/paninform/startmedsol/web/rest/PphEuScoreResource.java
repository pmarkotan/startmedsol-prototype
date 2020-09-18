package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.domain.PphEuScore;
import hu.paninform.startmedsol.repository.PphEuScoreRepository;
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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.PphEuScore}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PphEuScoreResource {

    private final Logger log = LoggerFactory.getLogger(PphEuScoreResource.class);

    private static final String ENTITY_NAME = "pphEuScore";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PphEuScoreRepository pphEuScoreRepository;

    public PphEuScoreResource(PphEuScoreRepository pphEuScoreRepository) {
        this.pphEuScoreRepository = pphEuScoreRepository;
    }

    /**
     * {@code POST  /pph-eu-scores} : Create a new pphEuScore.
     *
     * @param pphEuScore the pphEuScore to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pphEuScore, or with status {@code 400 (Bad Request)} if the pphEuScore has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pph-eu-scores")
    public ResponseEntity<PphEuScore> createPphEuScore(@Valid @RequestBody PphEuScore pphEuScore) throws URISyntaxException {
        log.debug("REST request to save PphEuScore : {}", pphEuScore);
        if (pphEuScore.getId() != null) {
            throw new BadRequestAlertException("A new pphEuScore cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PphEuScore result = pphEuScoreRepository.save(pphEuScore);
        return ResponseEntity.created(new URI("/api/pph-eu-scores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pph-eu-scores} : Updates an existing pphEuScore.
     *
     * @param pphEuScore the pphEuScore to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pphEuScore,
     * or with status {@code 400 (Bad Request)} if the pphEuScore is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pphEuScore couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pph-eu-scores")
    public ResponseEntity<PphEuScore> updatePphEuScore(@Valid @RequestBody PphEuScore pphEuScore) throws URISyntaxException {
        log.debug("REST request to update PphEuScore : {}", pphEuScore);
        if (pphEuScore.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PphEuScore result = pphEuScoreRepository.save(pphEuScore);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pphEuScore.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pph-eu-scores} : get all the pphEuScores.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pphEuScores in body.
     */
    @GetMapping("/pph-eu-scores")
    public List<PphEuScore> getAllPphEuScores() {
        log.debug("REST request to get all PphEuScores");
        return pphEuScoreRepository.findAll();
    }

    /**
     * {@code GET  /pph-eu-scores/:id} : get the "id" pphEuScore.
     *
     * @param id the id of the pphEuScore to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pphEuScore, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pph-eu-scores/{id}")
    public ResponseEntity<PphEuScore> getPphEuScore(@PathVariable Long id) {
        log.debug("REST request to get PphEuScore : {}", id);
        Optional<PphEuScore> pphEuScore = pphEuScoreRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pphEuScore);
    }

    /**
     * {@code DELETE  /pph-eu-scores/:id} : delete the "id" pphEuScore.
     *
     * @param id the id of the pphEuScore to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pph-eu-scores/{id}")
    public ResponseEntity<Void> deletePphEuScore(@PathVariable Long id) {
        log.debug("REST request to delete PphEuScore : {}", id);
        pphEuScoreRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
