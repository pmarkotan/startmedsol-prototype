package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.CsCountry;
import hu.paninform.startmedsol.domain.Validity;
import hu.paninform.startmedsol.repository.CsCountryRepository;
import hu.paninform.startmedsol.service.CsCountryService;
import hu.paninform.startmedsol.service.dto.CsCountryDTO;
import hu.paninform.startmedsol.service.mapper.CsCountryMapper;
import hu.paninform.startmedsol.service.dto.CsCountryCriteria;
import hu.paninform.startmedsol.service.CsCountryQueryService;

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
 * Integration tests for the {@link CsCountryResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CsCountryResourceIT {

    private static final String DEFAULT_CODE = "AAAA";
    private static final String UPDATED_CODE = "BBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private CsCountryRepository csCountryRepository;

    @Autowired
    private CsCountryMapper csCountryMapper;

    @Autowired
    private CsCountryService csCountryService;

    @Autowired
    private CsCountryQueryService csCountryQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCsCountryMockMvc;

    private CsCountry csCountry;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CsCountry createEntity(EntityManager em) {
        CsCountry csCountry = new CsCountry()
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
        csCountry.setValidity(validity);
        return csCountry;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CsCountry createUpdatedEntity(EntityManager em) {
        CsCountry csCountry = new CsCountry()
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
        csCountry.setValidity(validity);
        return csCountry;
    }

    @BeforeEach
    public void initTest() {
        csCountry = createEntity(em);
    }

    @Test
    @Transactional
    public void createCsCountry() throws Exception {
        int databaseSizeBeforeCreate = csCountryRepository.findAll().size();
        // Create the CsCountry
        CsCountryDTO csCountryDTO = csCountryMapper.toDto(csCountry);
        restCsCountryMockMvc.perform(post("/api/cs-countries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csCountryDTO)))
            .andExpect(status().isCreated());

        // Validate the CsCountry in the database
        List<CsCountry> csCountryList = csCountryRepository.findAll();
        assertThat(csCountryList).hasSize(databaseSizeBeforeCreate + 1);
        CsCountry testCsCountry = csCountryList.get(csCountryList.size() - 1);
        assertThat(testCsCountry.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testCsCountry.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createCsCountryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = csCountryRepository.findAll().size();

        // Create the CsCountry with an existing ID
        csCountry.setId(1L);
        CsCountryDTO csCountryDTO = csCountryMapper.toDto(csCountry);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCsCountryMockMvc.perform(post("/api/cs-countries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csCountryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CsCountry in the database
        List<CsCountry> csCountryList = csCountryRepository.findAll();
        assertThat(csCountryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = csCountryRepository.findAll().size();
        // set the field null
        csCountry.setCode(null);

        // Create the CsCountry, which fails.
        CsCountryDTO csCountryDTO = csCountryMapper.toDto(csCountry);


        restCsCountryMockMvc.perform(post("/api/cs-countries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csCountryDTO)))
            .andExpect(status().isBadRequest());

        List<CsCountry> csCountryList = csCountryRepository.findAll();
        assertThat(csCountryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = csCountryRepository.findAll().size();
        // set the field null
        csCountry.setDescription(null);

        // Create the CsCountry, which fails.
        CsCountryDTO csCountryDTO = csCountryMapper.toDto(csCountry);


        restCsCountryMockMvc.perform(post("/api/cs-countries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csCountryDTO)))
            .andExpect(status().isBadRequest());

        List<CsCountry> csCountryList = csCountryRepository.findAll();
        assertThat(csCountryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCsCountries() throws Exception {
        // Initialize the database
        csCountryRepository.saveAndFlush(csCountry);

        // Get all the csCountryList
        restCsCountryMockMvc.perform(get("/api/cs-countries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(csCountry.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getCsCountry() throws Exception {
        // Initialize the database
        csCountryRepository.saveAndFlush(csCountry);

        // Get the csCountry
        restCsCountryMockMvc.perform(get("/api/cs-countries/{id}", csCountry.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(csCountry.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }


    @Test
    @Transactional
    public void getCsCountriesByIdFiltering() throws Exception {
        // Initialize the database
        csCountryRepository.saveAndFlush(csCountry);

        Long id = csCountry.getId();

        defaultCsCountryShouldBeFound("id.equals=" + id);
        defaultCsCountryShouldNotBeFound("id.notEquals=" + id);

        defaultCsCountryShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCsCountryShouldNotBeFound("id.greaterThan=" + id);

        defaultCsCountryShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCsCountryShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCsCountriesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        csCountryRepository.saveAndFlush(csCountry);

        // Get all the csCountryList where code equals to DEFAULT_CODE
        defaultCsCountryShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the csCountryList where code equals to UPDATED_CODE
        defaultCsCountryShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllCsCountriesByCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        csCountryRepository.saveAndFlush(csCountry);

        // Get all the csCountryList where code not equals to DEFAULT_CODE
        defaultCsCountryShouldNotBeFound("code.notEquals=" + DEFAULT_CODE);

        // Get all the csCountryList where code not equals to UPDATED_CODE
        defaultCsCountryShouldBeFound("code.notEquals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllCsCountriesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        csCountryRepository.saveAndFlush(csCountry);

        // Get all the csCountryList where code in DEFAULT_CODE or UPDATED_CODE
        defaultCsCountryShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the csCountryList where code equals to UPDATED_CODE
        defaultCsCountryShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllCsCountriesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        csCountryRepository.saveAndFlush(csCountry);

        // Get all the csCountryList where code is not null
        defaultCsCountryShouldBeFound("code.specified=true");

        // Get all the csCountryList where code is null
        defaultCsCountryShouldNotBeFound("code.specified=false");
    }
                @Test
    @Transactional
    public void getAllCsCountriesByCodeContainsSomething() throws Exception {
        // Initialize the database
        csCountryRepository.saveAndFlush(csCountry);

        // Get all the csCountryList where code contains DEFAULT_CODE
        defaultCsCountryShouldBeFound("code.contains=" + DEFAULT_CODE);

        // Get all the csCountryList where code contains UPDATED_CODE
        defaultCsCountryShouldNotBeFound("code.contains=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllCsCountriesByCodeNotContainsSomething() throws Exception {
        // Initialize the database
        csCountryRepository.saveAndFlush(csCountry);

        // Get all the csCountryList where code does not contain DEFAULT_CODE
        defaultCsCountryShouldNotBeFound("code.doesNotContain=" + DEFAULT_CODE);

        // Get all the csCountryList where code does not contain UPDATED_CODE
        defaultCsCountryShouldBeFound("code.doesNotContain=" + UPDATED_CODE);
    }


    @Test
    @Transactional
    public void getAllCsCountriesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        csCountryRepository.saveAndFlush(csCountry);

        // Get all the csCountryList where description equals to DEFAULT_DESCRIPTION
        defaultCsCountryShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the csCountryList where description equals to UPDATED_DESCRIPTION
        defaultCsCountryShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllCsCountriesByDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        csCountryRepository.saveAndFlush(csCountry);

        // Get all the csCountryList where description not equals to DEFAULT_DESCRIPTION
        defaultCsCountryShouldNotBeFound("description.notEquals=" + DEFAULT_DESCRIPTION);

        // Get all the csCountryList where description not equals to UPDATED_DESCRIPTION
        defaultCsCountryShouldBeFound("description.notEquals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllCsCountriesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        csCountryRepository.saveAndFlush(csCountry);

        // Get all the csCountryList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultCsCountryShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the csCountryList where description equals to UPDATED_DESCRIPTION
        defaultCsCountryShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllCsCountriesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        csCountryRepository.saveAndFlush(csCountry);

        // Get all the csCountryList where description is not null
        defaultCsCountryShouldBeFound("description.specified=true");

        // Get all the csCountryList where description is null
        defaultCsCountryShouldNotBeFound("description.specified=false");
    }
                @Test
    @Transactional
    public void getAllCsCountriesByDescriptionContainsSomething() throws Exception {
        // Initialize the database
        csCountryRepository.saveAndFlush(csCountry);

        // Get all the csCountryList where description contains DEFAULT_DESCRIPTION
        defaultCsCountryShouldBeFound("description.contains=" + DEFAULT_DESCRIPTION);

        // Get all the csCountryList where description contains UPDATED_DESCRIPTION
        defaultCsCountryShouldNotBeFound("description.contains=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllCsCountriesByDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        csCountryRepository.saveAndFlush(csCountry);

        // Get all the csCountryList where description does not contain DEFAULT_DESCRIPTION
        defaultCsCountryShouldNotBeFound("description.doesNotContain=" + DEFAULT_DESCRIPTION);

        // Get all the csCountryList where description does not contain UPDATED_DESCRIPTION
        defaultCsCountryShouldBeFound("description.doesNotContain=" + UPDATED_DESCRIPTION);
    }


    @Test
    @Transactional
    public void getAllCsCountriesByValidityIsEqualToSomething() throws Exception {
        // Get already existing entity
        Validity validity = csCountry.getValidity();
        csCountryRepository.saveAndFlush(csCountry);
        Long validityId = validity.getId();

        // Get all the csCountryList where validity equals to validityId
        defaultCsCountryShouldBeFound("validityId.equals=" + validityId);

        // Get all the csCountryList where validity equals to validityId + 1
        defaultCsCountryShouldNotBeFound("validityId.equals=" + (validityId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCsCountryShouldBeFound(String filter) throws Exception {
        restCsCountryMockMvc.perform(get("/api/cs-countries?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(csCountry.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));

        // Check, that the count call also returns 1
        restCsCountryMockMvc.perform(get("/api/cs-countries/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCsCountryShouldNotBeFound(String filter) throws Exception {
        restCsCountryMockMvc.perform(get("/api/cs-countries?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCsCountryMockMvc.perform(get("/api/cs-countries/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingCsCountry() throws Exception {
        // Get the csCountry
        restCsCountryMockMvc.perform(get("/api/cs-countries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCsCountry() throws Exception {
        // Initialize the database
        csCountryRepository.saveAndFlush(csCountry);

        int databaseSizeBeforeUpdate = csCountryRepository.findAll().size();

        // Update the csCountry
        CsCountry updatedCsCountry = csCountryRepository.findById(csCountry.getId()).get();
        // Disconnect from session so that the updates on updatedCsCountry are not directly saved in db
        em.detach(updatedCsCountry);
        updatedCsCountry
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION);
        CsCountryDTO csCountryDTO = csCountryMapper.toDto(updatedCsCountry);

        restCsCountryMockMvc.perform(put("/api/cs-countries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csCountryDTO)))
            .andExpect(status().isOk());

        // Validate the CsCountry in the database
        List<CsCountry> csCountryList = csCountryRepository.findAll();
        assertThat(csCountryList).hasSize(databaseSizeBeforeUpdate);
        CsCountry testCsCountry = csCountryList.get(csCountryList.size() - 1);
        assertThat(testCsCountry.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testCsCountry.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingCsCountry() throws Exception {
        int databaseSizeBeforeUpdate = csCountryRepository.findAll().size();

        // Create the CsCountry
        CsCountryDTO csCountryDTO = csCountryMapper.toDto(csCountry);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCsCountryMockMvc.perform(put("/api/cs-countries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csCountryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CsCountry in the database
        List<CsCountry> csCountryList = csCountryRepository.findAll();
        assertThat(csCountryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCsCountry() throws Exception {
        // Initialize the database
        csCountryRepository.saveAndFlush(csCountry);

        int databaseSizeBeforeDelete = csCountryRepository.findAll().size();

        // Delete the csCountry
        restCsCountryMockMvc.perform(delete("/api/cs-countries/{id}", csCountry.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CsCountry> csCountryList = csCountryRepository.findAll();
        assertThat(csCountryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
