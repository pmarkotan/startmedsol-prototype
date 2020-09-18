package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.domain.PphQualificationDoctorAll;
import hu.paninform.startmedsol.repository.PphQualificationDoctorAllRepository;
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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.PphQualificationDoctorAll}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PphQualificationDoctorAllResource {

    private final Logger log = LoggerFactory.getLogger(PphQualificationDoctorAllResource.class);

    private static final String ENTITY_NAME = "pphQualificationDoctorAll";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PphQualificationDoctorAllRepository pphQualificationDoctorAllRepository;

    public PphQualificationDoctorAllResource(PphQualificationDoctorAllRepository pphQualificationDoctorAllRepository) {
        this.pphQualificationDoctorAllRepository = pphQualificationDoctorAllRepository;
    }

    /**
     * {@code POST  /pph-qualification-doctor-alls} : Create a new pphQualificationDoctorAll.
     *
     * @param pphQualificationDoctorAll the pphQualificationDoctorAll to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pphQualificationDoctorAll, or with status {@code 400 (Bad Request)} if the pphQualificationDoctorAll has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pph-qualification-doctor-alls")
    public ResponseEntity<PphQualificationDoctorAll> createPphQualificationDoctorAll(@Valid @RequestBody PphQualificationDoctorAll pphQualificationDoctorAll) throws URISyntaxException {
        log.debug("REST request to save PphQualificationDoctorAll : {}", pphQualificationDoctorAll);
        if (pphQualificationDoctorAll.getId() != null) {
            throw new BadRequestAlertException("A new pphQualificationDoctorAll cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PphQualificationDoctorAll result = pphQualificationDoctorAllRepository.save(pphQualificationDoctorAll);
        return ResponseEntity.created(new URI("/api/pph-qualification-doctor-alls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pph-qualification-doctor-alls} : Updates an existing pphQualificationDoctorAll.
     *
     * @param pphQualificationDoctorAll the pphQualificationDoctorAll to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pphQualificationDoctorAll,
     * or with status {@code 400 (Bad Request)} if the pphQualificationDoctorAll is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pphQualificationDoctorAll couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pph-qualification-doctor-alls")
    public ResponseEntity<PphQualificationDoctorAll> updatePphQualificationDoctorAll(@Valid @RequestBody PphQualificationDoctorAll pphQualificationDoctorAll) throws URISyntaxException {
        log.debug("REST request to update PphQualificationDoctorAll : {}", pphQualificationDoctorAll);
        if (pphQualificationDoctorAll.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PphQualificationDoctorAll result = pphQualificationDoctorAllRepository.save(pphQualificationDoctorAll);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pphQualificationDoctorAll.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pph-qualification-doctor-alls} : get all the pphQualificationDoctorAlls.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pphQualificationDoctorAlls in body.
     */
    @GetMapping("/pph-qualification-doctor-alls")
    public List<PphQualificationDoctorAll> getAllPphQualificationDoctorAlls() {
        log.debug("REST request to get all PphQualificationDoctorAlls");
        return pphQualificationDoctorAllRepository.findAll();
    }

    /**
     * {@code GET  /pph-qualification-doctor-alls/:id} : get the "id" pphQualificationDoctorAll.
     *
     * @param id the id of the pphQualificationDoctorAll to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pphQualificationDoctorAll, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pph-qualification-doctor-alls/{id}")
    public ResponseEntity<PphQualificationDoctorAll> getPphQualificationDoctorAll(@PathVariable Long id) {
        log.debug("REST request to get PphQualificationDoctorAll : {}", id);
        Optional<PphQualificationDoctorAll> pphQualificationDoctorAll = pphQualificationDoctorAllRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pphQualificationDoctorAll);
    }

    /**
     * {@code DELETE  /pph-qualification-doctor-alls/:id} : delete the "id" pphQualificationDoctorAll.
     *
     * @param id the id of the pphQualificationDoctorAll to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pph-qualification-doctor-alls/{id}")
    public ResponseEntity<Void> deletePphQualificationDoctorAll(@PathVariable Long id) {
        log.debug("REST request to delete PphQualificationDoctorAll : {}", id);
        pphQualificationDoctorAllRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
