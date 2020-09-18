package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.CsCountryService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.CsCountryDTO;
import hu.paninform.startmedsol.service.dto.CsCountryCriteria;
import hu.paninform.startmedsol.service.CsCountryQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link hu.paninform.startmedsol.domain.CsCountry}.
 */
@RestController
@RequestMapping("/api")
public class CsCountryResource {

    private final Logger log = LoggerFactory.getLogger(CsCountryResource.class);

    private static final String ENTITY_NAME = "csCountry";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CsCountryService csCountryService;

    private final CsCountryQueryService csCountryQueryService;

    public CsCountryResource(CsCountryService csCountryService, CsCountryQueryService csCountryQueryService) {
        this.csCountryService = csCountryService;
        this.csCountryQueryService = csCountryQueryService;
    }

    /**
     * {@code POST  /cs-countries} : Create a new csCountry.
     *
     * @param csCountryDTO the csCountryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new csCountryDTO, or with status {@code 400 (Bad Request)} if the csCountry has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cs-countries")
    public ResponseEntity<CsCountryDTO> createCsCountry(@Valid @RequestBody CsCountryDTO csCountryDTO) throws URISyntaxException {
        log.debug("REST request to save CsCountry : {}", csCountryDTO);
        if (csCountryDTO.getId() != null) {
            throw new BadRequestAlertException("A new csCountry cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CsCountryDTO result = csCountryService.save(csCountryDTO);
        return ResponseEntity.created(new URI("/api/cs-countries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cs-countries} : Updates an existing csCountry.
     *
     * @param csCountryDTO the csCountryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated csCountryDTO,
     * or with status {@code 400 (Bad Request)} if the csCountryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the csCountryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cs-countries")
    public ResponseEntity<CsCountryDTO> updateCsCountry(@Valid @RequestBody CsCountryDTO csCountryDTO) throws URISyntaxException {
        log.debug("REST request to update CsCountry : {}", csCountryDTO);
        if (csCountryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CsCountryDTO result = csCountryService.save(csCountryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, csCountryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cs-countries} : get all the csCountries.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of csCountries in body.
     */
    @GetMapping("/cs-countries")
    public ResponseEntity<List<CsCountryDTO>> getAllCsCountries(CsCountryCriteria criteria) {
        log.debug("REST request to get CsCountries by criteria: {}", criteria);
        List<CsCountryDTO> entityList = csCountryQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /cs-countries/count} : count all the csCountries.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/cs-countries/count")
    public ResponseEntity<Long> countCsCountries(CsCountryCriteria criteria) {
        log.debug("REST request to count CsCountries by criteria: {}", criteria);
        return ResponseEntity.ok().body(csCountryQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cs-countries/:id} : get the "id" csCountry.
     *
     * @param id the id of the csCountryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the csCountryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cs-countries/{id}")
    public ResponseEntity<CsCountryDTO> getCsCountry(@PathVariable Long id) {
        log.debug("REST request to get CsCountry : {}", id);
        Optional<CsCountryDTO> csCountryDTO = csCountryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(csCountryDTO);
    }

    /**
     * {@code DELETE  /cs-countries/:id} : delete the "id" csCountry.
     *
     * @param id the id of the csCountryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cs-countries/{id}")
    public ResponseEntity<Void> deleteCsCountry(@PathVariable Long id) {
        log.debug("REST request to delete CsCountry : {}", id);
        csCountryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
