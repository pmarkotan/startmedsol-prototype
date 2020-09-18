package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.domain.PphPuphaVersion;
import hu.paninform.startmedsol.repository.PphPuphaVersionRepository;
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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.PphPuphaVersion}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PphPuphaVersionResource {

    private final Logger log = LoggerFactory.getLogger(PphPuphaVersionResource.class);

    private static final String ENTITY_NAME = "pphPuphaVersion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PphPuphaVersionRepository pphPuphaVersionRepository;

    public PphPuphaVersionResource(PphPuphaVersionRepository pphPuphaVersionRepository) {
        this.pphPuphaVersionRepository = pphPuphaVersionRepository;
    }

    /**
     * {@code POST  /pph-pupha-versions} : Create a new pphPuphaVersion.
     *
     * @param pphPuphaVersion the pphPuphaVersion to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pphPuphaVersion, or with status {@code 400 (Bad Request)} if the pphPuphaVersion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pph-pupha-versions")
    public ResponseEntity<PphPuphaVersion> createPphPuphaVersion(@Valid @RequestBody PphPuphaVersion pphPuphaVersion) throws URISyntaxException {
        log.debug("REST request to save PphPuphaVersion : {}", pphPuphaVersion);
        if (pphPuphaVersion.getId() != null) {
            throw new BadRequestAlertException("A new pphPuphaVersion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PphPuphaVersion result = pphPuphaVersionRepository.save(pphPuphaVersion);
        return ResponseEntity.created(new URI("/api/pph-pupha-versions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pph-pupha-versions} : Updates an existing pphPuphaVersion.
     *
     * @param pphPuphaVersion the pphPuphaVersion to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pphPuphaVersion,
     * or with status {@code 400 (Bad Request)} if the pphPuphaVersion is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pphPuphaVersion couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pph-pupha-versions")
    public ResponseEntity<PphPuphaVersion> updatePphPuphaVersion(@Valid @RequestBody PphPuphaVersion pphPuphaVersion) throws URISyntaxException {
        log.debug("REST request to update PphPuphaVersion : {}", pphPuphaVersion);
        if (pphPuphaVersion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PphPuphaVersion result = pphPuphaVersionRepository.save(pphPuphaVersion);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pphPuphaVersion.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pph-pupha-versions} : get all the pphPuphaVersions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pphPuphaVersions in body.
     */
    @GetMapping("/pph-pupha-versions")
    public List<PphPuphaVersion> getAllPphPuphaVersions() {
        log.debug("REST request to get all PphPuphaVersions");
        return pphPuphaVersionRepository.findAll();
    }

    /**
     * {@code GET  /pph-pupha-versions/:id} : get the "id" pphPuphaVersion.
     *
     * @param id the id of the pphPuphaVersion to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pphPuphaVersion, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pph-pupha-versions/{id}")
    public ResponseEntity<PphPuphaVersion> getPphPuphaVersion(@PathVariable Long id) {
        log.debug("REST request to get PphPuphaVersion : {}", id);
        Optional<PphPuphaVersion> pphPuphaVersion = pphPuphaVersionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pphPuphaVersion);
    }

    /**
     * {@code DELETE  /pph-pupha-versions/:id} : delete the "id" pphPuphaVersion.
     *
     * @param id the id of the pphPuphaVersion to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pph-pupha-versions/{id}")
    public ResponseEntity<Void> deletePphPuphaVersion(@PathVariable Long id) {
        log.debug("REST request to delete PphPuphaVersion : {}", id);
        pphPuphaVersionRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
