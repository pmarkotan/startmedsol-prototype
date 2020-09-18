package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.DataGeneratorService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.DataGeneratorDTO;

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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.DataGenerator}.
 */
@RestController
@RequestMapping("/api")
public class DataGeneratorResource {

    private final Logger log = LoggerFactory.getLogger(DataGeneratorResource.class);

    private static final String ENTITY_NAME = "dataGenerator";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DataGeneratorService dataGeneratorService;

    public DataGeneratorResource(DataGeneratorService dataGeneratorService) {
        this.dataGeneratorService = dataGeneratorService;
    }

    /**
     * {@code POST  /data-generators} : Create a new dataGenerator.
     *
     * @param dataGeneratorDTO the dataGeneratorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dataGeneratorDTO, or with status {@code 400 (Bad Request)} if the dataGenerator has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/data-generators")
    public ResponseEntity<DataGeneratorDTO> createDataGenerator(@RequestBody DataGeneratorDTO dataGeneratorDTO) throws URISyntaxException {
        log.debug("REST request to save DataGenerator : {}", dataGeneratorDTO);
        if (dataGeneratorDTO.getId() != null) {
            throw new BadRequestAlertException("A new dataGenerator cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DataGeneratorDTO result = dataGeneratorService.save(dataGeneratorDTO);
        return ResponseEntity.created(new URI("/api/data-generators/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /data-generators} : Updates an existing dataGenerator.
     *
     * @param dataGeneratorDTO the dataGeneratorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dataGeneratorDTO,
     * or with status {@code 400 (Bad Request)} if the dataGeneratorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dataGeneratorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/data-generators")
    public ResponseEntity<DataGeneratorDTO> updateDataGenerator(@RequestBody DataGeneratorDTO dataGeneratorDTO) throws URISyntaxException {
        log.debug("REST request to update DataGenerator : {}", dataGeneratorDTO);
        if (dataGeneratorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DataGeneratorDTO result = dataGeneratorService.save(dataGeneratorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dataGeneratorDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /data-generators} : get all the dataGenerators.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dataGenerators in body.
     */
    @GetMapping("/data-generators")
    public ResponseEntity<List<DataGeneratorDTO>> getAllDataGenerators(Pageable pageable) {
        log.debug("REST request to get a page of DataGenerators");
        Page<DataGeneratorDTO> page = dataGeneratorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /data-generators/:id} : get the "id" dataGenerator.
     *
     * @param id the id of the dataGeneratorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dataGeneratorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/data-generators/{id}")
    public ResponseEntity<DataGeneratorDTO> getDataGenerator(@PathVariable Long id) {
        log.debug("REST request to get DataGenerator : {}", id);
        Optional<DataGeneratorDTO> dataGeneratorDTO = dataGeneratorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dataGeneratorDTO);
    }

    /**
     * {@code DELETE  /data-generators/:id} : delete the "id" dataGenerator.
     *
     * @param id the id of the dataGeneratorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/data-generators/{id}")
    public ResponseEntity<Void> deleteDataGenerator(@PathVariable Long id) {
        log.debug("REST request to delete DataGenerator : {}", id);
        dataGeneratorService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
