package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.domain.PphMotivationGroup;
import hu.paninform.startmedsol.repository.PphMotivationGroupRepository;
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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.PphMotivationGroup}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PphMotivationGroupResource {

    private final Logger log = LoggerFactory.getLogger(PphMotivationGroupResource.class);

    private static final String ENTITY_NAME = "pphMotivationGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PphMotivationGroupRepository pphMotivationGroupRepository;

    public PphMotivationGroupResource(PphMotivationGroupRepository pphMotivationGroupRepository) {
        this.pphMotivationGroupRepository = pphMotivationGroupRepository;
    }

    /**
     * {@code POST  /pph-motivation-groups} : Create a new pphMotivationGroup.
     *
     * @param pphMotivationGroup the pphMotivationGroup to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pphMotivationGroup, or with status {@code 400 (Bad Request)} if the pphMotivationGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pph-motivation-groups")
    public ResponseEntity<PphMotivationGroup> createPphMotivationGroup(@Valid @RequestBody PphMotivationGroup pphMotivationGroup) throws URISyntaxException {
        log.debug("REST request to save PphMotivationGroup : {}", pphMotivationGroup);
        if (pphMotivationGroup.getId() != null) {
            throw new BadRequestAlertException("A new pphMotivationGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PphMotivationGroup result = pphMotivationGroupRepository.save(pphMotivationGroup);
        return ResponseEntity.created(new URI("/api/pph-motivation-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pph-motivation-groups} : Updates an existing pphMotivationGroup.
     *
     * @param pphMotivationGroup the pphMotivationGroup to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pphMotivationGroup,
     * or with status {@code 400 (Bad Request)} if the pphMotivationGroup is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pphMotivationGroup couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pph-motivation-groups")
    public ResponseEntity<PphMotivationGroup> updatePphMotivationGroup(@Valid @RequestBody PphMotivationGroup pphMotivationGroup) throws URISyntaxException {
        log.debug("REST request to update PphMotivationGroup : {}", pphMotivationGroup);
        if (pphMotivationGroup.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PphMotivationGroup result = pphMotivationGroupRepository.save(pphMotivationGroup);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pphMotivationGroup.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pph-motivation-groups} : get all the pphMotivationGroups.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pphMotivationGroups in body.
     */
    @GetMapping("/pph-motivation-groups")
    public List<PphMotivationGroup> getAllPphMotivationGroups() {
        log.debug("REST request to get all PphMotivationGroups");
        return pphMotivationGroupRepository.findAll();
    }

    /**
     * {@code GET  /pph-motivation-groups/:id} : get the "id" pphMotivationGroup.
     *
     * @param id the id of the pphMotivationGroup to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pphMotivationGroup, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pph-motivation-groups/{id}")
    public ResponseEntity<PphMotivationGroup> getPphMotivationGroup(@PathVariable Long id) {
        log.debug("REST request to get PphMotivationGroup : {}", id);
        Optional<PphMotivationGroup> pphMotivationGroup = pphMotivationGroupRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pphMotivationGroup);
    }

    /**
     * {@code DELETE  /pph-motivation-groups/:id} : delete the "id" pphMotivationGroup.
     *
     * @param id the id of the pphMotivationGroup to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pph-motivation-groups/{id}")
    public ResponseEntity<Void> deletePphMotivationGroup(@PathVariable Long id) {
        log.debug("REST request to delete PphMotivationGroup : {}", id);
        pphMotivationGroupRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
