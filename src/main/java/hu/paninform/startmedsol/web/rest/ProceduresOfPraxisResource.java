package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.ProceduresOfPraxisService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.ProceduresOfPraxisDTO;

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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.ProceduresOfPraxis}.
 */
@RestController
@RequestMapping("/api")
public class ProceduresOfPraxisResource {

    private final Logger log = LoggerFactory.getLogger(ProceduresOfPraxisResource.class);

    private static final String ENTITY_NAME = "proceduresOfPraxis";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProceduresOfPraxisService proceduresOfPraxisService;

    public ProceduresOfPraxisResource(ProceduresOfPraxisService proceduresOfPraxisService) {
        this.proceduresOfPraxisService = proceduresOfPraxisService;
    }

    /**
     * {@code POST  /procedures-of-praxes} : Create a new proceduresOfPraxis.
     *
     * @param proceduresOfPraxisDTO the proceduresOfPraxisDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proceduresOfPraxisDTO, or with status {@code 400 (Bad Request)} if the proceduresOfPraxis has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/procedures-of-praxes")
    public ResponseEntity<ProceduresOfPraxisDTO> createProceduresOfPraxis(@Valid @RequestBody ProceduresOfPraxisDTO proceduresOfPraxisDTO) throws URISyntaxException {
        log.debug("REST request to save ProceduresOfPraxis : {}", proceduresOfPraxisDTO);
        if (proceduresOfPraxisDTO.getId() != null) {
            throw new BadRequestAlertException("A new proceduresOfPraxis cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProceduresOfPraxisDTO result = proceduresOfPraxisService.save(proceduresOfPraxisDTO);
        return ResponseEntity.created(new URI("/api/procedures-of-praxes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /procedures-of-praxes} : Updates an existing proceduresOfPraxis.
     *
     * @param proceduresOfPraxisDTO the proceduresOfPraxisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proceduresOfPraxisDTO,
     * or with status {@code 400 (Bad Request)} if the proceduresOfPraxisDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proceduresOfPraxisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/procedures-of-praxes")
    public ResponseEntity<ProceduresOfPraxisDTO> updateProceduresOfPraxis(@Valid @RequestBody ProceduresOfPraxisDTO proceduresOfPraxisDTO) throws URISyntaxException {
        log.debug("REST request to update ProceduresOfPraxis : {}", proceduresOfPraxisDTO);
        if (proceduresOfPraxisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProceduresOfPraxisDTO result = proceduresOfPraxisService.save(proceduresOfPraxisDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proceduresOfPraxisDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /procedures-of-praxes} : get all the proceduresOfPraxes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proceduresOfPraxes in body.
     */
    @GetMapping("/procedures-of-praxes")
    public ResponseEntity<List<ProceduresOfPraxisDTO>> getAllProceduresOfPraxes(Pageable pageable) {
        log.debug("REST request to get a page of ProceduresOfPraxes");
        Page<ProceduresOfPraxisDTO> page = proceduresOfPraxisService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /procedures-of-praxes/:id} : get the "id" proceduresOfPraxis.
     *
     * @param id the id of the proceduresOfPraxisDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proceduresOfPraxisDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/procedures-of-praxes/{id}")
    public ResponseEntity<ProceduresOfPraxisDTO> getProceduresOfPraxis(@PathVariable Long id) {
        log.debug("REST request to get ProceduresOfPraxis : {}", id);
        Optional<ProceduresOfPraxisDTO> proceduresOfPraxisDTO = proceduresOfPraxisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proceduresOfPraxisDTO);
    }

    /**
     * {@code DELETE  /procedures-of-praxes/:id} : delete the "id" proceduresOfPraxis.
     *
     * @param id the id of the proceduresOfPraxisDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/procedures-of-praxes/{id}")
    public ResponseEntity<Void> deleteProceduresOfPraxis(@PathVariable Long id) {
        log.debug("REST request to delete ProceduresOfPraxis : {}", id);
        proceduresOfPraxisService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
