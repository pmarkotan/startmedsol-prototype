package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.domain.PphQualificEuScoreLink;
import hu.paninform.startmedsol.repository.PphQualificEuScoreLinkRepository;
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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.PphQualificEuScoreLink}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PphQualificEuScoreLinkResource {

    private final Logger log = LoggerFactory.getLogger(PphQualificEuScoreLinkResource.class);

    private static final String ENTITY_NAME = "pphQualificEuScoreLink";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PphQualificEuScoreLinkRepository pphQualificEuScoreLinkRepository;

    public PphQualificEuScoreLinkResource(PphQualificEuScoreLinkRepository pphQualificEuScoreLinkRepository) {
        this.pphQualificEuScoreLinkRepository = pphQualificEuScoreLinkRepository;
    }

    /**
     * {@code POST  /pph-qualific-eu-score-links} : Create a new pphQualificEuScoreLink.
     *
     * @param pphQualificEuScoreLink the pphQualificEuScoreLink to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pphQualificEuScoreLink, or with status {@code 400 (Bad Request)} if the pphQualificEuScoreLink has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pph-qualific-eu-score-links")
    public ResponseEntity<PphQualificEuScoreLink> createPphQualificEuScoreLink(@Valid @RequestBody PphQualificEuScoreLink pphQualificEuScoreLink) throws URISyntaxException {
        log.debug("REST request to save PphQualificEuScoreLink : {}", pphQualificEuScoreLink);
        if (pphQualificEuScoreLink.getId() != null) {
            throw new BadRequestAlertException("A new pphQualificEuScoreLink cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PphQualificEuScoreLink result = pphQualificEuScoreLinkRepository.save(pphQualificEuScoreLink);
        return ResponseEntity.created(new URI("/api/pph-qualific-eu-score-links/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pph-qualific-eu-score-links} : Updates an existing pphQualificEuScoreLink.
     *
     * @param pphQualificEuScoreLink the pphQualificEuScoreLink to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pphQualificEuScoreLink,
     * or with status {@code 400 (Bad Request)} if the pphQualificEuScoreLink is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pphQualificEuScoreLink couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pph-qualific-eu-score-links")
    public ResponseEntity<PphQualificEuScoreLink> updatePphQualificEuScoreLink(@Valid @RequestBody PphQualificEuScoreLink pphQualificEuScoreLink) throws URISyntaxException {
        log.debug("REST request to update PphQualificEuScoreLink : {}", pphQualificEuScoreLink);
        if (pphQualificEuScoreLink.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PphQualificEuScoreLink result = pphQualificEuScoreLinkRepository.save(pphQualificEuScoreLink);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pphQualificEuScoreLink.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pph-qualific-eu-score-links} : get all the pphQualificEuScoreLinks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pphQualificEuScoreLinks in body.
     */
    @GetMapping("/pph-qualific-eu-score-links")
    public List<PphQualificEuScoreLink> getAllPphQualificEuScoreLinks() {
        log.debug("REST request to get all PphQualificEuScoreLinks");
        return pphQualificEuScoreLinkRepository.findAll();
    }

    /**
     * {@code GET  /pph-qualific-eu-score-links/:id} : get the "id" pphQualificEuScoreLink.
     *
     * @param id the id of the pphQualificEuScoreLink to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pphQualificEuScoreLink, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pph-qualific-eu-score-links/{id}")
    public ResponseEntity<PphQualificEuScoreLink> getPphQualificEuScoreLink(@PathVariable Long id) {
        log.debug("REST request to get PphQualificEuScoreLink : {}", id);
        Optional<PphQualificEuScoreLink> pphQualificEuScoreLink = pphQualificEuScoreLinkRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pphQualificEuScoreLink);
    }

    /**
     * {@code DELETE  /pph-qualific-eu-score-links/:id} : delete the "id" pphQualificEuScoreLink.
     *
     * @param id the id of the pphQualificEuScoreLink to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pph-qualific-eu-score-links/{id}")
    public ResponseEntity<Void> deletePphQualificEuScoreLink(@PathVariable Long id) {
        log.debug("REST request to delete PphQualificEuScoreLink : {}", id);
        pphQualificEuScoreLinkRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
