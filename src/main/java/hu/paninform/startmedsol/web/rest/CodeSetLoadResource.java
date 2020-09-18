package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.domain.CodeSetLoad;
import hu.paninform.startmedsol.repository.CodeSetLoadRepository;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link hu.paninform.startmedsol.domain.CodeSetLoad}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CodeSetLoadResource {

    private final Logger log = LoggerFactory.getLogger(CodeSetLoadResource.class);

    private static final String ENTITY_NAME = "codeSetLoad";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CodeSetLoadRepository codeSetLoadRepository;

    public CodeSetLoadResource(CodeSetLoadRepository codeSetLoadRepository) {
        this.codeSetLoadRepository = codeSetLoadRepository;
    }

    /**
     * {@code POST  /code-set-loads} : Create a new codeSetLoad.
     *
     * @param codeSetLoad the codeSetLoad to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new codeSetLoad, or with status {@code 400 (Bad Request)} if the codeSetLoad has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/code-set-loads")
    public ResponseEntity<CodeSetLoad> createCodeSetLoad(@RequestBody CodeSetLoad codeSetLoad) throws URISyntaxException {
        log.debug("REST request to save CodeSetLoad : {}", codeSetLoad);
        if (codeSetLoad.getId() != null) {
            throw new BadRequestAlertException("A new codeSetLoad cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CodeSetLoad result = codeSetLoadRepository.save(codeSetLoad);
        return ResponseEntity.created(new URI("/api/code-set-loads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /code-set-loads} : Updates an existing codeSetLoad.
     *
     * @param codeSetLoad the codeSetLoad to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated codeSetLoad,
     * or with status {@code 400 (Bad Request)} if the codeSetLoad is not valid,
     * or with status {@code 500 (Internal Server Error)} if the codeSetLoad couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/code-set-loads")
    public ResponseEntity<CodeSetLoad> updateCodeSetLoad(@RequestBody CodeSetLoad codeSetLoad) throws URISyntaxException {
        log.debug("REST request to update CodeSetLoad : {}", codeSetLoad);
        if (codeSetLoad.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CodeSetLoad result = codeSetLoadRepository.save(codeSetLoad);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, codeSetLoad.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /code-set-loads} : get all the codeSetLoads.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of codeSetLoads in body.
     */
    @GetMapping("/code-set-loads")
    public List<CodeSetLoad> getAllCodeSetLoads() {
        log.debug("REST request to get all CodeSetLoads");
        return codeSetLoadRepository.findAll();
    }

    /**
     * {@code GET  /code-set-loads/:id} : get the "id" codeSetLoad.
     *
     * @param id the id of the codeSetLoad to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the codeSetLoad, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/code-set-loads/{id}")
    public ResponseEntity<CodeSetLoad> getCodeSetLoad(@PathVariable Long id) {
        log.debug("REST request to get CodeSetLoad : {}", id);
        Optional<CodeSetLoad> codeSetLoad = codeSetLoadRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(codeSetLoad);
    }

    /**
     * {@code DELETE  /code-set-loads/:id} : delete the "id" codeSetLoad.
     *
     * @param id the id of the codeSetLoad to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/code-set-loads/{id}")
    public ResponseEntity<Void> deleteCodeSetLoad(@PathVariable Long id) {
        log.debug("REST request to delete CodeSetLoad : {}", id);
        codeSetLoadRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
