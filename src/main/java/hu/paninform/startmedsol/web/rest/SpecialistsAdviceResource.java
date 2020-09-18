package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.SpecialistsAdviceService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.SpecialistsAdviceDTO;

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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.SpecialistsAdvice}.
 */
@RestController
@RequestMapping("/api")
public class SpecialistsAdviceResource {

    private final Logger log = LoggerFactory.getLogger(SpecialistsAdviceResource.class);

    private static final String ENTITY_NAME = "specialistsAdvice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SpecialistsAdviceService specialistsAdviceService;

    public SpecialistsAdviceResource(SpecialistsAdviceService specialistsAdviceService) {
        this.specialistsAdviceService = specialistsAdviceService;
    }

    /**
     * {@code POST  /specialists-advices} : Create a new specialistsAdvice.
     *
     * @param specialistsAdviceDTO the specialistsAdviceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new specialistsAdviceDTO, or with status {@code 400 (Bad Request)} if the specialistsAdvice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/specialists-advices")
    public ResponseEntity<SpecialistsAdviceDTO> createSpecialistsAdvice(@Valid @RequestBody SpecialistsAdviceDTO specialistsAdviceDTO) throws URISyntaxException {
        log.debug("REST request to save SpecialistsAdvice : {}", specialistsAdviceDTO);
        if (specialistsAdviceDTO.getId() != null) {
            throw new BadRequestAlertException("A new specialistsAdvice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SpecialistsAdviceDTO result = specialistsAdviceService.save(specialistsAdviceDTO);
        return ResponseEntity.created(new URI("/api/specialists-advices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /specialists-advices} : Updates an existing specialistsAdvice.
     *
     * @param specialistsAdviceDTO the specialistsAdviceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated specialistsAdviceDTO,
     * or with status {@code 400 (Bad Request)} if the specialistsAdviceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the specialistsAdviceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/specialists-advices")
    public ResponseEntity<SpecialistsAdviceDTO> updateSpecialistsAdvice(@Valid @RequestBody SpecialistsAdviceDTO specialistsAdviceDTO) throws URISyntaxException {
        log.debug("REST request to update SpecialistsAdvice : {}", specialistsAdviceDTO);
        if (specialistsAdviceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SpecialistsAdviceDTO result = specialistsAdviceService.save(specialistsAdviceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, specialistsAdviceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /specialists-advices} : get all the specialistsAdvices.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of specialistsAdvices in body.
     */
    @GetMapping("/specialists-advices")
    public ResponseEntity<List<SpecialistsAdviceDTO>> getAllSpecialistsAdvices(Pageable pageable) {
        log.debug("REST request to get a page of SpecialistsAdvices");
        Page<SpecialistsAdviceDTO> page = specialistsAdviceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /specialists-advices/:id} : get the "id" specialistsAdvice.
     *
     * @param id the id of the specialistsAdviceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the specialistsAdviceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/specialists-advices/{id}")
    public ResponseEntity<SpecialistsAdviceDTO> getSpecialistsAdvice(@PathVariable Long id) {
        log.debug("REST request to get SpecialistsAdvice : {}", id);
        Optional<SpecialistsAdviceDTO> specialistsAdviceDTO = specialistsAdviceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(specialistsAdviceDTO);
    }

    /**
     * {@code DELETE  /specialists-advices/:id} : delete the "id" specialistsAdvice.
     *
     * @param id the id of the specialistsAdviceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/specialists-advices/{id}")
    public ResponseEntity<Void> deleteSpecialistsAdvice(@PathVariable Long id) {
        log.debug("REST request to delete SpecialistsAdvice : {}", id);
        specialistsAdviceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
