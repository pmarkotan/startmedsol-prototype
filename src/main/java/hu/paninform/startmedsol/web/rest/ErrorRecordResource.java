package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.ErrorRecordService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.ErrorRecordDTO;

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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.ErrorRecord}.
 */
@RestController
@RequestMapping("/api")
public class ErrorRecordResource {

    private final Logger log = LoggerFactory.getLogger(ErrorRecordResource.class);

    private static final String ENTITY_NAME = "errorRecord";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ErrorRecordService errorRecordService;

    public ErrorRecordResource(ErrorRecordService errorRecordService) {
        this.errorRecordService = errorRecordService;
    }

    /**
     * {@code POST  /error-records} : Create a new errorRecord.
     *
     * @param errorRecordDTO the errorRecordDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new errorRecordDTO, or with status {@code 400 (Bad Request)} if the errorRecord has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/error-records")
    public ResponseEntity<ErrorRecordDTO> createErrorRecord(@Valid @RequestBody ErrorRecordDTO errorRecordDTO) throws URISyntaxException {
        log.debug("REST request to save ErrorRecord : {}", errorRecordDTO);
        if (errorRecordDTO.getId() != null) {
            throw new BadRequestAlertException("A new errorRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ErrorRecordDTO result = errorRecordService.save(errorRecordDTO);
        return ResponseEntity.created(new URI("/api/error-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /error-records} : Updates an existing errorRecord.
     *
     * @param errorRecordDTO the errorRecordDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated errorRecordDTO,
     * or with status {@code 400 (Bad Request)} if the errorRecordDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the errorRecordDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/error-records")
    public ResponseEntity<ErrorRecordDTO> updateErrorRecord(@Valid @RequestBody ErrorRecordDTO errorRecordDTO) throws URISyntaxException {
        log.debug("REST request to update ErrorRecord : {}", errorRecordDTO);
        if (errorRecordDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ErrorRecordDTO result = errorRecordService.save(errorRecordDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, errorRecordDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /error-records} : get all the errorRecords.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of errorRecords in body.
     */
    @GetMapping("/error-records")
    public ResponseEntity<List<ErrorRecordDTO>> getAllErrorRecords(Pageable pageable) {
        log.debug("REST request to get a page of ErrorRecords");
        Page<ErrorRecordDTO> page = errorRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /error-records/:id} : get the "id" errorRecord.
     *
     * @param id the id of the errorRecordDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the errorRecordDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/error-records/{id}")
    public ResponseEntity<ErrorRecordDTO> getErrorRecord(@PathVariable Long id) {
        log.debug("REST request to get ErrorRecord : {}", id);
        Optional<ErrorRecordDTO> errorRecordDTO = errorRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(errorRecordDTO);
    }

    /**
     * {@code DELETE  /error-records/:id} : delete the "id" errorRecord.
     *
     * @param id the id of the errorRecordDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/error-records/{id}")
    public ResponseEntity<Void> deleteErrorRecord(@PathVariable Long id) {
        log.debug("REST request to delete ErrorRecord : {}", id);
        errorRecordService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
