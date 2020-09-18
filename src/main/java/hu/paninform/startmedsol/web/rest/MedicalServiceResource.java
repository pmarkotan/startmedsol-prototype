package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.MedicalServiceService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.MedicalServiceDTO;

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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.MedicalService}.
 */
@RestController
@RequestMapping("/api")
public class MedicalServiceResource {

    private final Logger log = LoggerFactory.getLogger(MedicalServiceResource.class);

    private static final String ENTITY_NAME = "medicalService";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MedicalServiceService medicalServiceService;

    public MedicalServiceResource(MedicalServiceService medicalServiceService) {
        this.medicalServiceService = medicalServiceService;
    }

    /**
     * {@code POST  /medical-services} : Create a new medicalService.
     *
     * @param medicalServiceDTO the medicalServiceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new medicalServiceDTO, or with status {@code 400 (Bad Request)} if the medicalService has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/medical-services")
    public ResponseEntity<MedicalServiceDTO> createMedicalService(@Valid @RequestBody MedicalServiceDTO medicalServiceDTO) throws URISyntaxException {
        log.debug("REST request to save MedicalService : {}", medicalServiceDTO);
        if (medicalServiceDTO.getId() != null) {
            throw new BadRequestAlertException("A new medicalService cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MedicalServiceDTO result = medicalServiceService.save(medicalServiceDTO);
        return ResponseEntity.created(new URI("/api/medical-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /medical-services} : Updates an existing medicalService.
     *
     * @param medicalServiceDTO the medicalServiceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated medicalServiceDTO,
     * or with status {@code 400 (Bad Request)} if the medicalServiceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the medicalServiceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/medical-services")
    public ResponseEntity<MedicalServiceDTO> updateMedicalService(@Valid @RequestBody MedicalServiceDTO medicalServiceDTO) throws URISyntaxException {
        log.debug("REST request to update MedicalService : {}", medicalServiceDTO);
        if (medicalServiceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MedicalServiceDTO result = medicalServiceService.save(medicalServiceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, medicalServiceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /medical-services} : get all the medicalServices.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of medicalServices in body.
     */
    @GetMapping("/medical-services")
    public ResponseEntity<List<MedicalServiceDTO>> getAllMedicalServices(Pageable pageable) {
        log.debug("REST request to get a page of MedicalServices");
        Page<MedicalServiceDTO> page = medicalServiceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /medical-services/:id} : get the "id" medicalService.
     *
     * @param id the id of the medicalServiceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the medicalServiceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/medical-services/{id}")
    public ResponseEntity<MedicalServiceDTO> getMedicalService(@PathVariable Long id) {
        log.debug("REST request to get MedicalService : {}", id);
        Optional<MedicalServiceDTO> medicalServiceDTO = medicalServiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(medicalServiceDTO);
    }

    /**
     * {@code DELETE  /medical-services/:id} : delete the "id" medicalService.
     *
     * @param id the id of the medicalServiceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/medical-services/{id}")
    public ResponseEntity<Void> deleteMedicalService(@PathVariable Long id) {
        log.debug("REST request to delete MedicalService : {}", id);
        medicalServiceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
