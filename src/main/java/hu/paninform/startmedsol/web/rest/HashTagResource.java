package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.HashTagService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.HashTagDTO;
import hu.paninform.startmedsol.service.dto.HashTagCriteria;
import hu.paninform.startmedsol.service.HashTagQueryService;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link hu.paninform.startmedsol.domain.HashTag}.
 */
@RestController
@RequestMapping("/api")
public class HashTagResource {

    private final Logger log = LoggerFactory.getLogger(HashTagResource.class);

    private static final String ENTITY_NAME = "hashTag";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HashTagService hashTagService;

    private final HashTagQueryService hashTagQueryService;

    public HashTagResource(HashTagService hashTagService, HashTagQueryService hashTagQueryService) {
        this.hashTagService = hashTagService;
        this.hashTagQueryService = hashTagQueryService;
    }

    /**
     * {@code POST  /hash-tags} : Create a new hashTag.
     *
     * @param hashTagDTO the hashTagDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new hashTagDTO, or with status {@code 400 (Bad Request)} if the hashTag has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/hash-tags")
    public ResponseEntity<HashTagDTO> createHashTag(@Valid @RequestBody HashTagDTO hashTagDTO) throws URISyntaxException {
        log.debug("REST request to save HashTag : {}", hashTagDTO);
        if (hashTagDTO.getId() != null) {
            throw new BadRequestAlertException("A new hashTag cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HashTagDTO result = hashTagService.save(hashTagDTO);
        return ResponseEntity.created(new URI("/api/hash-tags/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /hash-tags} : Updates an existing hashTag.
     *
     * @param hashTagDTO the hashTagDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hashTagDTO,
     * or with status {@code 400 (Bad Request)} if the hashTagDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the hashTagDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/hash-tags")
    public ResponseEntity<HashTagDTO> updateHashTag(@Valid @RequestBody HashTagDTO hashTagDTO) throws URISyntaxException {
        log.debug("REST request to update HashTag : {}", hashTagDTO);
        if (hashTagDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HashTagDTO result = hashTagService.save(hashTagDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, hashTagDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /hash-tags} : get all the hashTags.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hashTags in body.
     */
    @GetMapping("/hash-tags")
    public ResponseEntity<List<HashTagDTO>> getAllHashTags(HashTagCriteria criteria, Pageable pageable) {
        log.debug("REST request to get HashTags by criteria: {}", criteria);
        Page<HashTagDTO> page = hashTagQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /hash-tags/count} : count all the hashTags.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/hash-tags/count")
    public ResponseEntity<Long> countHashTags(HashTagCriteria criteria) {
        log.debug("REST request to count HashTags by criteria: {}", criteria);
        return ResponseEntity.ok().body(hashTagQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /hash-tags/:id} : get the "id" hashTag.
     *
     * @param id the id of the hashTagDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hashTagDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/hash-tags/{id}")
    public ResponseEntity<HashTagDTO> getHashTag(@PathVariable Long id) {
        log.debug("REST request to get HashTag : {}", id);
        Optional<HashTagDTO> hashTagDTO = hashTagService.findOne(id);
        return ResponseUtil.wrapOrNotFound(hashTagDTO);
    }

    /**
     * {@code DELETE  /hash-tags/:id} : delete the "id" hashTag.
     *
     * @param id the id of the hashTagDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/hash-tags/{id}")
    public ResponseEntity<Void> deleteHashTag(@PathVariable Long id) {
        log.debug("REST request to delete HashTag : {}", id);
        hashTagService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
