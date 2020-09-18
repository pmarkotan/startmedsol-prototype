package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.CsSenderOrganization;
import hu.paninform.startmedsol.domain.Validity;
import hu.paninform.startmedsol.repository.CsSenderOrganizationRepository;

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
 * Integration tests for the {@link CsSenderOrganizationResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CsSenderOrganizationResourceIT {

    private static final String DEFAULT_COUNTY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTY = "BBBBBBBBBB";

    private static final String DEFAULT_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FINANCING_NUMBER = "AAAAAAAAA";
    private static final String UPDATED_FINANCING_NUMBER = "BBBBBBBBB";

    private static final String DEFAULT_FINANCING_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FINANCING_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    @Autowired
    private CsSenderOrganizationRepository csSenderOrganizationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCsSenderOrganizationMockMvc;

    private CsSenderOrganization csSenderOrganization;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CsSenderOrganization createEntity(EntityManager em) {
        CsSenderOrganization csSenderOrganization = new CsSenderOrganization()
            .county(DEFAULT_COUNTY)
            .shortName(DEFAULT_SHORT_NAME)
            .name(DEFAULT_NAME)
            .financingNumber(DEFAULT_FINANCING_NUMBER)
            .financingName(DEFAULT_FINANCING_NAME)
            .type(DEFAULT_TYPE);
        // Add required entity
        Validity validity;
        if (TestUtil.findAll(em, Validity.class).isEmpty()) {
            validity = ValidityResourceIT.createEntity(em);
            em.persist(validity);
            em.flush();
        } else {
            validity = TestUtil.findAll(em, Validity.class).get(0);
        }
        csSenderOrganization.setValidity(validity);
        return csSenderOrganization;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CsSenderOrganization createUpdatedEntity(EntityManager em) {
        CsSenderOrganization csSenderOrganization = new CsSenderOrganization()
            .county(UPDATED_COUNTY)
            .shortName(UPDATED_SHORT_NAME)
            .name(UPDATED_NAME)
            .financingNumber(UPDATED_FINANCING_NUMBER)
            .financingName(UPDATED_FINANCING_NAME)
            .type(UPDATED_TYPE);
        // Add required entity
        Validity validity;
        if (TestUtil.findAll(em, Validity.class).isEmpty()) {
            validity = ValidityResourceIT.createUpdatedEntity(em);
            em.persist(validity);
            em.flush();
        } else {
            validity = TestUtil.findAll(em, Validity.class).get(0);
        }
        csSenderOrganization.setValidity(validity);
        return csSenderOrganization;
    }

    @BeforeEach
    public void initTest() {
        csSenderOrganization = createEntity(em);
    }

    @Test
    @Transactional
    public void createCsSenderOrganization() throws Exception {
        int databaseSizeBeforeCreate = csSenderOrganizationRepository.findAll().size();
        // Create the CsSenderOrganization
        restCsSenderOrganizationMockMvc.perform(post("/api/cs-sender-organizations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csSenderOrganization)))
            .andExpect(status().isCreated());

        // Validate the CsSenderOrganization in the database
        List<CsSenderOrganization> csSenderOrganizationList = csSenderOrganizationRepository.findAll();
        assertThat(csSenderOrganizationList).hasSize(databaseSizeBeforeCreate + 1);
        CsSenderOrganization testCsSenderOrganization = csSenderOrganizationList.get(csSenderOrganizationList.size() - 1);
        assertThat(testCsSenderOrganization.getCounty()).isEqualTo(DEFAULT_COUNTY);
        assertThat(testCsSenderOrganization.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
        assertThat(testCsSenderOrganization.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCsSenderOrganization.getFinancingNumber()).isEqualTo(DEFAULT_FINANCING_NUMBER);
        assertThat(testCsSenderOrganization.getFinancingName()).isEqualTo(DEFAULT_FINANCING_NAME);
        assertThat(testCsSenderOrganization.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createCsSenderOrganizationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = csSenderOrganizationRepository.findAll().size();

        // Create the CsSenderOrganization with an existing ID
        csSenderOrganization.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCsSenderOrganizationMockMvc.perform(post("/api/cs-sender-organizations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csSenderOrganization)))
            .andExpect(status().isBadRequest());

        // Validate the CsSenderOrganization in the database
        List<CsSenderOrganization> csSenderOrganizationList = csSenderOrganizationRepository.findAll();
        assertThat(csSenderOrganizationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFinancingNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = csSenderOrganizationRepository.findAll().size();
        // set the field null
        csSenderOrganization.setFinancingNumber(null);

        // Create the CsSenderOrganization, which fails.


        restCsSenderOrganizationMockMvc.perform(post("/api/cs-sender-organizations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csSenderOrganization)))
            .andExpect(status().isBadRequest());

        List<CsSenderOrganization> csSenderOrganizationList = csSenderOrganizationRepository.findAll();
        assertThat(csSenderOrganizationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = csSenderOrganizationRepository.findAll().size();
        // set the field null
        csSenderOrganization.setType(null);

        // Create the CsSenderOrganization, which fails.


        restCsSenderOrganizationMockMvc.perform(post("/api/cs-sender-organizations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csSenderOrganization)))
            .andExpect(status().isBadRequest());

        List<CsSenderOrganization> csSenderOrganizationList = csSenderOrganizationRepository.findAll();
        assertThat(csSenderOrganizationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCsSenderOrganizations() throws Exception {
        // Initialize the database
        csSenderOrganizationRepository.saveAndFlush(csSenderOrganization);

        // Get all the csSenderOrganizationList
        restCsSenderOrganizationMockMvc.perform(get("/api/cs-sender-organizations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(csSenderOrganization.getId().intValue())))
            .andExpect(jsonPath("$.[*].county").value(hasItem(DEFAULT_COUNTY)))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].financingNumber").value(hasItem(DEFAULT_FINANCING_NUMBER)))
            .andExpect(jsonPath("$.[*].financingName").value(hasItem(DEFAULT_FINANCING_NAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)));
    }
    
    @Test
    @Transactional
    public void getCsSenderOrganization() throws Exception {
        // Initialize the database
        csSenderOrganizationRepository.saveAndFlush(csSenderOrganization);

        // Get the csSenderOrganization
        restCsSenderOrganizationMockMvc.perform(get("/api/cs-sender-organizations/{id}", csSenderOrganization.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(csSenderOrganization.getId().intValue()))
            .andExpect(jsonPath("$.county").value(DEFAULT_COUNTY))
            .andExpect(jsonPath("$.shortName").value(DEFAULT_SHORT_NAME))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.financingNumber").value(DEFAULT_FINANCING_NUMBER))
            .andExpect(jsonPath("$.financingName").value(DEFAULT_FINANCING_NAME))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE));
    }
    @Test
    @Transactional
    public void getNonExistingCsSenderOrganization() throws Exception {
        // Get the csSenderOrganization
        restCsSenderOrganizationMockMvc.perform(get("/api/cs-sender-organizations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCsSenderOrganization() throws Exception {
        // Initialize the database
        csSenderOrganizationRepository.saveAndFlush(csSenderOrganization);

        int databaseSizeBeforeUpdate = csSenderOrganizationRepository.findAll().size();

        // Update the csSenderOrganization
        CsSenderOrganization updatedCsSenderOrganization = csSenderOrganizationRepository.findById(csSenderOrganization.getId()).get();
        // Disconnect from session so that the updates on updatedCsSenderOrganization are not directly saved in db
        em.detach(updatedCsSenderOrganization);
        updatedCsSenderOrganization
            .county(UPDATED_COUNTY)
            .shortName(UPDATED_SHORT_NAME)
            .name(UPDATED_NAME)
            .financingNumber(UPDATED_FINANCING_NUMBER)
            .financingName(UPDATED_FINANCING_NAME)
            .type(UPDATED_TYPE);

        restCsSenderOrganizationMockMvc.perform(put("/api/cs-sender-organizations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCsSenderOrganization)))
            .andExpect(status().isOk());

        // Validate the CsSenderOrganization in the database
        List<CsSenderOrganization> csSenderOrganizationList = csSenderOrganizationRepository.findAll();
        assertThat(csSenderOrganizationList).hasSize(databaseSizeBeforeUpdate);
        CsSenderOrganization testCsSenderOrganization = csSenderOrganizationList.get(csSenderOrganizationList.size() - 1);
        assertThat(testCsSenderOrganization.getCounty()).isEqualTo(UPDATED_COUNTY);
        assertThat(testCsSenderOrganization.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
        assertThat(testCsSenderOrganization.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCsSenderOrganization.getFinancingNumber()).isEqualTo(UPDATED_FINANCING_NUMBER);
        assertThat(testCsSenderOrganization.getFinancingName()).isEqualTo(UPDATED_FINANCING_NAME);
        assertThat(testCsSenderOrganization.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingCsSenderOrganization() throws Exception {
        int databaseSizeBeforeUpdate = csSenderOrganizationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCsSenderOrganizationMockMvc.perform(put("/api/cs-sender-organizations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csSenderOrganization)))
            .andExpect(status().isBadRequest());

        // Validate the CsSenderOrganization in the database
        List<CsSenderOrganization> csSenderOrganizationList = csSenderOrganizationRepository.findAll();
        assertThat(csSenderOrganizationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCsSenderOrganization() throws Exception {
        // Initialize the database
        csSenderOrganizationRepository.saveAndFlush(csSenderOrganization);

        int databaseSizeBeforeDelete = csSenderOrganizationRepository.findAll().size();

        // Delete the csSenderOrganization
        restCsSenderOrganizationMockMvc.perform(delete("/api/cs-sender-organizations/{id}", csSenderOrganization.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CsSenderOrganization> csSenderOrganizationList = csSenderOrganizationRepository.findAll();
        assertThat(csSenderOrganizationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
