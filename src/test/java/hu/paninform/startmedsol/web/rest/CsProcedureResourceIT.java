package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.CsProcedure;
import hu.paninform.startmedsol.domain.Validity;
import hu.paninform.startmedsol.repository.CsProcedureRepository;
import hu.paninform.startmedsol.service.CsProcedureService;
import hu.paninform.startmedsol.service.dto.CsProcedureDTO;
import hu.paninform.startmedsol.service.mapper.CsProcedureMapper;
import hu.paninform.startmedsol.service.dto.CsProcedureCriteria;
import hu.paninform.startmedsol.service.CsProcedureQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CsProcedureResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CsProcedureResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private CsProcedureRepository csProcedureRepository;

    @Autowired
    private CsProcedureMapper csProcedureMapper;

    @Autowired
    private CsProcedureService csProcedureService;

    @Autowired
    private CsProcedureQueryService csProcedureQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCsProcedureMockMvc;

    private CsProcedure csProcedure;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CsProcedure createEntity(EntityManager em) {
        CsProcedure csProcedure = new CsProcedure()
            .code(DEFAULT_CODE)
            .description(DEFAULT_DESCRIPTION);
        // Add required entity
        Validity validity;
        if (TestUtil.findAll(em, Validity.class).isEmpty()) {
            validity = ValidityResourceIT.createEntity(em);
            em.persist(validity);
            em.flush();
        } else {
            validity = TestUtil.findAll(em, Validity.class).get(0);
        }
        csProcedure.setValidity(validity);
        return csProcedure;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CsProcedure createUpdatedEntity(EntityManager em) {
        CsProcedure csProcedure = new CsProcedure()
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION);
        // Add required entity
        Validity validity;
        if (TestUtil.findAll(em, Validity.class).isEmpty()) {
            validity = ValidityResourceIT.createUpdatedEntity(em);
            em.persist(validity);
            em.flush();
        } else {
            validity = TestUtil.findAll(em, Validity.class).get(0);
        }
        csProcedure.setValidity(validity);
        return csProcedure;
    }

    @BeforeEach
    public void initTest() {
        csProcedure = createEntity(em);
    }

    @Test
    @Transactional
    public void createCsProcedure() throws Exception {
        int databaseSizeBeforeCreate = csProcedureRepository.findAll().size();
        // Create the CsProcedure
        CsProcedureDTO csProcedureDTO = csProcedureMapper.toDto(csProcedure);
        restCsProcedureMockMvc.perform(post("/api/cs-procedures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csProcedureDTO)))
            .andExpect(status().isCreated());

        // Validate the CsProcedure in the database
        List<CsProcedure> csProcedureList = csProcedureRepository.findAll();
        assertThat(csProcedureList).hasSize(databaseSizeBeforeCreate + 1);
        CsProcedure testCsProcedure = csProcedureList.get(csProcedureList.size() - 1);
        assertThat(testCsProcedure.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testCsProcedure.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createCsProcedureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = csProcedureRepository.findAll().size();

        // Create the CsProcedure with an existing ID
        csProcedure.setId(1L);
        CsProcedureDTO csProcedureDTO = csProcedureMapper.toDto(csProcedure);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCsProcedureMockMvc.perform(post("/api/cs-procedures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csProcedureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CsProcedure in the database
        List<CsProcedure> csProcedureList = csProcedureRepository.findAll();
        assertThat(csProcedureList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = csProcedureRepository.findAll().size();
        // set the field null
        csProcedure.setCode(null);

        // Create the CsProcedure, which fails.
        CsProcedureDTO csProcedureDTO = csProcedureMapper.toDto(csProcedure);


        restCsProcedureMockMvc.perform(post("/api/cs-procedures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csProcedureDTO)))
            .andExpect(status().isBadRequest());

        List<CsProcedure> csProcedureList = csProcedureRepository.findAll();
        assertThat(csProcedureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = csProcedureRepository.findAll().size();
        // set the field null
        csProcedure.setDescription(null);

        // Create the CsProcedure, which fails.
        CsProcedureDTO csProcedureDTO = csProcedureMapper.toDto(csProcedure);


        restCsProcedureMockMvc.perform(post("/api/cs-procedures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csProcedureDTO)))
            .andExpect(status().isBadRequest());

        List<CsProcedure> csProcedureList = csProcedureRepository.findAll();
        assertThat(csProcedureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCsProcedures() throws Exception {
        // Initialize the database
        csProcedureRepository.saveAndFlush(csProcedure);

        // Get all the csProcedureList
        restCsProcedureMockMvc.perform(get("/api/cs-procedures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(csProcedure.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getCsProcedure() throws Exception {
        // Initialize the database
        csProcedureRepository.saveAndFlush(csProcedure);

        // Get the csProcedure
        restCsProcedureMockMvc.perform(get("/api/cs-procedures/{id}", csProcedure.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(csProcedure.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }


    @Test
    @Transactional
    public void getCsProceduresByIdFiltering() throws Exception {
        // Initialize the database
        csProcedureRepository.saveAndFlush(csProcedure);

        Long id = csProcedure.getId();

        defaultCsProcedureShouldBeFound("id.equals=" + id);
        defaultCsProcedureShouldNotBeFound("id.notEquals=" + id);

        defaultCsProcedureShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCsProcedureShouldNotBeFound("id.greaterThan=" + id);

        defaultCsProcedureShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCsProcedureShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCsProceduresByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        csProcedureRepository.saveAndFlush(csProcedure);

        // Get all the csProcedureList where code equals to DEFAULT_CODE
        defaultCsProcedureShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the csProcedureList where code equals to UPDATED_CODE
        defaultCsProcedureShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllCsProceduresByCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        csProcedureRepository.saveAndFlush(csProcedure);

        // Get all the csProcedureList where code not equals to DEFAULT_CODE
        defaultCsProcedureShouldNotBeFound("code.notEquals=" + DEFAULT_CODE);

        // Get all the csProcedureList where code not equals to UPDATED_CODE
        defaultCsProcedureShouldBeFound("code.notEquals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllCsProceduresByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        csProcedureRepository.saveAndFlush(csProcedure);

        // Get all the csProcedureList where code in DEFAULT_CODE or UPDATED_CODE
        defaultCsProcedureShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the csProcedureList where code equals to UPDATED_CODE
        defaultCsProcedureShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllCsProceduresByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        csProcedureRepository.saveAndFlush(csProcedure);

        // Get all the csProcedureList where code is not null
        defaultCsProcedureShouldBeFound("code.specified=true");

        // Get all the csProcedureList where code is null
        defaultCsProcedureShouldNotBeFound("code.specified=false");
    }
                @Test
    @Transactional
    public void getAllCsProceduresByCodeContainsSomething() throws Exception {
        // Initialize the database
        csProcedureRepository.saveAndFlush(csProcedure);

        // Get all the csProcedureList where code contains DEFAULT_CODE
        defaultCsProcedureShouldBeFound("code.contains=" + DEFAULT_CODE);

        // Get all the csProcedureList where code contains UPDATED_CODE
        defaultCsProcedureShouldNotBeFound("code.contains=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllCsProceduresByCodeNotContainsSomething() throws Exception {
        // Initialize the database
        csProcedureRepository.saveAndFlush(csProcedure);

        // Get all the csProcedureList where code does not contain DEFAULT_CODE
        defaultCsProcedureShouldNotBeFound("code.doesNotContain=" + DEFAULT_CODE);

        // Get all the csProcedureList where code does not contain UPDATED_CODE
        defaultCsProcedureShouldBeFound("code.doesNotContain=" + UPDATED_CODE);
    }


    @Test
    @Transactional
    public void getAllCsProceduresByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        csProcedureRepository.saveAndFlush(csProcedure);

        // Get all the csProcedureList where description equals to DEFAULT_DESCRIPTION
        defaultCsProcedureShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the csProcedureList where description equals to UPDATED_DESCRIPTION
        defaultCsProcedureShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllCsProceduresByDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        csProcedureRepository.saveAndFlush(csProcedure);

        // Get all the csProcedureList where description not equals to DEFAULT_DESCRIPTION
        defaultCsProcedureShouldNotBeFound("description.notEquals=" + DEFAULT_DESCRIPTION);

        // Get all the csProcedureList where description not equals to UPDATED_DESCRIPTION
        defaultCsProcedureShouldBeFound("description.notEquals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllCsProceduresByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        csProcedureRepository.saveAndFlush(csProcedure);

        // Get all the csProcedureList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultCsProcedureShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the csProcedureList where description equals to UPDATED_DESCRIPTION
        defaultCsProcedureShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllCsProceduresByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        csProcedureRepository.saveAndFlush(csProcedure);

        // Get all the csProcedureList where description is not null
        defaultCsProcedureShouldBeFound("description.specified=true");

        // Get all the csProcedureList where description is null
        defaultCsProcedureShouldNotBeFound("description.specified=false");
    }
                @Test
    @Transactional
    public void getAllCsProceduresByDescriptionContainsSomething() throws Exception {
        // Initialize the database
        csProcedureRepository.saveAndFlush(csProcedure);

        // Get all the csProcedureList where description contains DEFAULT_DESCRIPTION
        defaultCsProcedureShouldBeFound("description.contains=" + DEFAULT_DESCRIPTION);

        // Get all the csProcedureList where description contains UPDATED_DESCRIPTION
        defaultCsProcedureShouldNotBeFound("description.contains=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllCsProceduresByDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        csProcedureRepository.saveAndFlush(csProcedure);

        // Get all the csProcedureList where description does not contain DEFAULT_DESCRIPTION
        defaultCsProcedureShouldNotBeFound("description.doesNotContain=" + DEFAULT_DESCRIPTION);

        // Get all the csProcedureList where description does not contain UPDATED_DESCRIPTION
        defaultCsProcedureShouldBeFound("description.doesNotContain=" + UPDATED_DESCRIPTION);
    }


    @Test
    @Transactional
    public void getAllCsProceduresByValidityIsEqualToSomething() throws Exception {
        // Get already existing entity
        Validity validity = csProcedure.getValidity();
        csProcedureRepository.saveAndFlush(csProcedure);
        Long validityId = validity.getId();

        // Get all the csProcedureList where validity equals to validityId
        defaultCsProcedureShouldBeFound("validityId.equals=" + validityId);

        // Get all the csProcedureList where validity equals to validityId + 1
        defaultCsProcedureShouldNotBeFound("validityId.equals=" + (validityId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCsProcedureShouldBeFound(String filter) throws Exception {
        restCsProcedureMockMvc.perform(get("/api/cs-procedures?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(csProcedure.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));

        // Check, that the count call also returns 1
        restCsProcedureMockMvc.perform(get("/api/cs-procedures/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCsProcedureShouldNotBeFound(String filter) throws Exception {
        restCsProcedureMockMvc.perform(get("/api/cs-procedures?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCsProcedureMockMvc.perform(get("/api/cs-procedures/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingCsProcedure() throws Exception {
        // Get the csProcedure
        restCsProcedureMockMvc.perform(get("/api/cs-procedures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCsProcedure() throws Exception {
        // Initialize the database
        csProcedureRepository.saveAndFlush(csProcedure);

        int databaseSizeBeforeUpdate = csProcedureRepository.findAll().size();

        // Update the csProcedure
        CsProcedure updatedCsProcedure = csProcedureRepository.findById(csProcedure.getId()).get();
        // Disconnect from session so that the updates on updatedCsProcedure are not directly saved in db
        em.detach(updatedCsProcedure);
        updatedCsProcedure
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION);
        CsProcedureDTO csProcedureDTO = csProcedureMapper.toDto(updatedCsProcedure);

        restCsProcedureMockMvc.perform(put("/api/cs-procedures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csProcedureDTO)))
            .andExpect(status().isOk());

        // Validate the CsProcedure in the database
        List<CsProcedure> csProcedureList = csProcedureRepository.findAll();
        assertThat(csProcedureList).hasSize(databaseSizeBeforeUpdate);
        CsProcedure testCsProcedure = csProcedureList.get(csProcedureList.size() - 1);
        assertThat(testCsProcedure.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testCsProcedure.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingCsProcedure() throws Exception {
        int databaseSizeBeforeUpdate = csProcedureRepository.findAll().size();

        // Create the CsProcedure
        CsProcedureDTO csProcedureDTO = csProcedureMapper.toDto(csProcedure);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCsProcedureMockMvc.perform(put("/api/cs-procedures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csProcedureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CsProcedure in the database
        List<CsProcedure> csProcedureList = csProcedureRepository.findAll();
        assertThat(csProcedureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCsProcedure() throws Exception {
        // Initialize the database
        csProcedureRepository.saveAndFlush(csProcedure);

        int databaseSizeBeforeDelete = csProcedureRepository.findAll().size();

        // Delete the csProcedure
        restCsProcedureMockMvc.perform(delete("/api/cs-procedures/{id}", csProcedure.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CsProcedure> csProcedureList = csProcedureRepository.findAll();
        assertThat(csProcedureList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
