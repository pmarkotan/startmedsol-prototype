package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.PuphaLoaderService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.PuphaLoaderDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link hu.paninform.startmedsol.domain.PuphaLoader}.
 */
@RestController
@RequestMapping("/api")
public class PuphaLoaderResource {

    private final Logger log = LoggerFactory.getLogger(PuphaLoaderResource.class);

    private static final String ENTITY_NAME = "puphaLoader";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PuphaLoaderService puphaLoaderService;

    public PuphaLoaderResource(PuphaLoaderService puphaLoaderService) {
        this.puphaLoaderService = puphaLoaderService;
    }

    /**
     * {@code POST  /pupha-loaders} : Create a new puphaLoader.
     *
     * @param puphaLoaderDTO the puphaLoaderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new puphaLoaderDTO, or with status {@code 400 (Bad Request)} if the puphaLoader has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pupha-loaders")
    public ResponseEntity<PuphaLoaderDTO> createPuphaLoader(@RequestBody PuphaLoaderDTO puphaLoaderDTO) throws URISyntaxException {
        log.debug("REST request to save PuphaLoader : {}", puphaLoaderDTO);
        if (puphaLoaderDTO.getId() != null) {
            throw new BadRequestAlertException("A new puphaLoader cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PuphaLoaderDTO result = puphaLoaderService.save(puphaLoaderDTO);
        return ResponseEntity.created(new URI("/api/pupha-loaders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pupha-loaders} : Updates an existing puphaLoader.
     *
     * @param puphaLoaderDTO the puphaLoaderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated puphaLoaderDTO,
     * or with status {@code 400 (Bad Request)} if the puphaLoaderDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the puphaLoaderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pupha-loaders")
    public ResponseEntity<PuphaLoaderDTO> updatePuphaLoader(@RequestBody PuphaLoaderDTO puphaLoaderDTO) throws URISyntaxException {
        log.debug("REST request to update PuphaLoader : {}", puphaLoaderDTO);
        if (puphaLoaderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PuphaLoaderDTO result = puphaLoaderService.save(puphaLoaderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, puphaLoaderDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pupha-loaders} : get all the puphaLoaders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of puphaLoaders in body.
     */
    @GetMapping("/pupha-loaders")
    public ResponseEntity<List<PuphaLoaderDTO>> getAllPuphaLoaders(Pageable pageable) {
        log.debug("REST request to get a page of PuphaLoaders");
        Page<PuphaLoaderDTO> page = puphaLoaderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pupha-loaders/:id} : get the "id" puphaLoader.
     *
     * @param id the id of the puphaLoaderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the puphaLoaderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pupha-loaders/{id}")
    public ResponseEntity<PuphaLoaderDTO> getPuphaLoader(@PathVariable Long id) {
        log.debug("REST request to get PuphaLoader : {}", id);
        Optional<PuphaLoaderDTO> puphaLoaderDTO = puphaLoaderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(puphaLoaderDTO);
    }

    /**
     * {@code DELETE  /pupha-loaders/:id} : delete the "id" puphaLoader.
     *
     * @param id the id of the puphaLoaderDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pupha-loaders/{id}")
    public ResponseEntity<Void> deletePuphaLoader(@PathVariable Long id) {
        log.debug("REST request to delete PuphaLoader : {}", id);
        puphaLoaderService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
