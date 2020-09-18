package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.PphPuphaInstitution;
import hu.paninform.startmedsol.repository.PphPuphaInstitutionRepository;

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

import hu.paninform.startmedsol.domain.enumeration.InstitutionType;
/**
 * Integration tests for the {@link PphPuphaInstitutionResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PphPuphaInstitutionResourceIT {

    private static final Integer DEFAULT_EXTERNAL_ID = 1;
    private static final Integer UPDATED_EXTERNAL_ID = 2;

    private static final String DEFAULT_COUNTY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTY = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_GYFKOD = "AAAAAAAAAA";
    private static final String UPDATED_GYFKOD = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REGISTRATION_NO = "AAAAAA";
    private static final String UPDATED_REGISTRATION_NO = "BBBBBB";

    private static final InstitutionType DEFAULT_INSTITUTION_TYPE = InstitutionType.OUTPATIENT;
    private static final InstitutionType UPDATED_INSTITUTION_TYPE = InstitutionType.INPATIENT;

    private static final Boolean DEFAULT_ACTIVE_PUPHA_DATA = false;
    private static final Boolean UPDATED_ACTIVE_PUPHA_DATA = true;

    @Autowired
    private PphPuphaInstitutionRepository pphPuphaInstitutionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPphPuphaInstitutionMockMvc;

    private PphPuphaInstitution pphPuphaInstitution;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphPuphaInstitution createEntity(EntityManager em) {
        PphPuphaInstitution pphPuphaInstitution = new PphPuphaInstitution()
            .externalId(DEFAULT_EXTERNAL_ID)
            .county(DEFAULT_COUNTY)
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .gyfkod(DEFAULT_GYFKOD)
            .unitName(DEFAULT_UNIT_NAME)
            .doctorName(DEFAULT_DOCTOR_NAME)
            .registrationNo(DEFAULT_REGISTRATION_NO)
            .institutionType(DEFAULT_INSTITUTION_TYPE)
            .activePuphaData(DEFAULT_ACTIVE_PUPHA_DATA);
        return pphPuphaInstitution;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphPuphaInstitution createUpdatedEntity(EntityManager em) {
        PphPuphaInstitution pphPuphaInstitution = new PphPuphaInstitution()
            .externalId(UPDATED_EXTERNAL_ID)
            .county(UPDATED_COUNTY)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .gyfkod(UPDATED_GYFKOD)
            .unitName(UPDATED_UNIT_NAME)
            .doctorName(UPDATED_DOCTOR_NAME)
            .registrationNo(UPDATED_REGISTRATION_NO)
            .institutionType(UPDATED_INSTITUTION_TYPE)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);
        return pphPuphaInstitution;
    }

    @BeforeEach
    public void initTest() {
        pphPuphaInstitution = createEntity(em);
    }

    @Test
    @Transactional
    public void createPphPuphaInstitution() throws Exception {
        int databaseSizeBeforeCreate = pphPuphaInstitutionRepository.findAll().size();
        // Create the PphPuphaInstitution
        restPphPuphaInstitutionMockMvc.perform(post("/api/pph-pupha-institutions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphPuphaInstitution)))
            .andExpect(status().isCreated());

        // Validate the PphPuphaInstitution in the database
        List<PphPuphaInstitution> pphPuphaInstitutionList = pphPuphaInstitutionRepository.findAll();
        assertThat(pphPuphaInstitutionList).hasSize(databaseSizeBeforeCreate + 1);
        PphPuphaInstitution testPphPuphaInstitution = pphPuphaInstitutionList.get(pphPuphaInstitutionList.size() - 1);
        assertThat(testPphPuphaInstitution.getExternalId()).isEqualTo(DEFAULT_EXTERNAL_ID);
        assertThat(testPphPuphaInstitution.getCounty()).isEqualTo(DEFAULT_COUNTY);
        assertThat(testPphPuphaInstitution.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testPphPuphaInstitution.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPphPuphaInstitution.getGyfkod()).isEqualTo(DEFAULT_GYFKOD);
        assertThat(testPphPuphaInstitution.getUnitName()).isEqualTo(DEFAULT_UNIT_NAME);
        assertThat(testPphPuphaInstitution.getDoctorName()).isEqualTo(DEFAULT_DOCTOR_NAME);
        assertThat(testPphPuphaInstitution.getRegistrationNo()).isEqualTo(DEFAULT_REGISTRATION_NO);
        assertThat(testPphPuphaInstitution.getInstitutionType()).isEqualTo(DEFAULT_INSTITUTION_TYPE);
        assertThat(testPphPuphaInstitution.isActivePuphaData()).isEqualTo(DEFAULT_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void createPphPuphaInstitutionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pphPuphaInstitutionRepository.findAll().size();

        // Create the PphPuphaInstitution with an existing ID
        pphPuphaInstitution.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPphPuphaInstitutionMockMvc.perform(post("/api/pph-pupha-institutions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphPuphaInstitution)))
            .andExpect(status().isBadRequest());

        // Validate the PphPuphaInstitution in the database
        List<PphPuphaInstitution> pphPuphaInstitutionList = pphPuphaInstitutionRepository.findAll();
        assertThat(pphPuphaInstitutionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkActivePuphaDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = pphPuphaInstitutionRepository.findAll().size();
        // set the field null
        pphPuphaInstitution.setActivePuphaData(null);

        // Create the PphPuphaInstitution, which fails.


        restPphPuphaInstitutionMockMvc.perform(post("/api/pph-pupha-institutions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphPuphaInstitution)))
            .andExpect(status().isBadRequest());

        List<PphPuphaInstitution> pphPuphaInstitutionList = pphPuphaInstitutionRepository.findAll();
        assertThat(pphPuphaInstitutionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPphPuphaInstitutions() throws Exception {
        // Initialize the database
        pphPuphaInstitutionRepository.saveAndFlush(pphPuphaInstitution);

        // Get all the pphPuphaInstitutionList
        restPphPuphaInstitutionMockMvc.perform(get("/api/pph-pupha-institutions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pphPuphaInstitution.getId().intValue())))
            .andExpect(jsonPath("$.[*].externalId").value(hasItem(DEFAULT_EXTERNAL_ID)))
            .andExpect(jsonPath("$.[*].county").value(hasItem(DEFAULT_COUNTY)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].gyfkod").value(hasItem(DEFAULT_GYFKOD)))
            .andExpect(jsonPath("$.[*].unitName").value(hasItem(DEFAULT_UNIT_NAME)))
            .andExpect(jsonPath("$.[*].doctorName").value(hasItem(DEFAULT_DOCTOR_NAME)))
            .andExpect(jsonPath("$.[*].registrationNo").value(hasItem(DEFAULT_REGISTRATION_NO)))
            .andExpect(jsonPath("$.[*].institutionType").value(hasItem(DEFAULT_INSTITUTION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].activePuphaData").value(hasItem(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPphPuphaInstitution() throws Exception {
        // Initialize the database
        pphPuphaInstitutionRepository.saveAndFlush(pphPuphaInstitution);

        // Get the pphPuphaInstitution
        restPphPuphaInstitutionMockMvc.perform(get("/api/pph-pupha-institutions/{id}", pphPuphaInstitution.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pphPuphaInstitution.getId().intValue()))
            .andExpect(jsonPath("$.externalId").value(DEFAULT_EXTERNAL_ID))
            .andExpect(jsonPath("$.county").value(DEFAULT_COUNTY))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.gyfkod").value(DEFAULT_GYFKOD))
            .andExpect(jsonPath("$.unitName").value(DEFAULT_UNIT_NAME))
            .andExpect(jsonPath("$.doctorName").value(DEFAULT_DOCTOR_NAME))
            .andExpect(jsonPath("$.registrationNo").value(DEFAULT_REGISTRATION_NO))
            .andExpect(jsonPath("$.institutionType").value(DEFAULT_INSTITUTION_TYPE.toString()))
            .andExpect(jsonPath("$.activePuphaData").value(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPphPuphaInstitution() throws Exception {
        // Get the pphPuphaInstitution
        restPphPuphaInstitutionMockMvc.perform(get("/api/pph-pupha-institutions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePphPuphaInstitution() throws Exception {
        // Initialize the database
        pphPuphaInstitutionRepository.saveAndFlush(pphPuphaInstitution);

        int databaseSizeBeforeUpdate = pphPuphaInstitutionRepository.findAll().size();

        // Update the pphPuphaInstitution
        PphPuphaInstitution updatedPphPuphaInstitution = pphPuphaInstitutionRepository.findById(pphPuphaInstitution.getId()).get();
        // Disconnect from session so that the updates on updatedPphPuphaInstitution are not directly saved in db
        em.detach(updatedPphPuphaInstitution);
        updatedPphPuphaInstitution
            .externalId(UPDATED_EXTERNAL_ID)
            .county(UPDATED_COUNTY)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .gyfkod(UPDATED_GYFKOD)
            .unitName(UPDATED_UNIT_NAME)
            .doctorName(UPDATED_DOCTOR_NAME)
            .registrationNo(UPDATED_REGISTRATION_NO)
            .institutionType(UPDATED_INSTITUTION_TYPE)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);

        restPphPuphaInstitutionMockMvc.perform(put("/api/pph-pupha-institutions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPphPuphaInstitution)))
            .andExpect(status().isOk());

        // Validate the PphPuphaInstitution in the database
        List<PphPuphaInstitution> pphPuphaInstitutionList = pphPuphaInstitutionRepository.findAll();
        assertThat(pphPuphaInstitutionList).hasSize(databaseSizeBeforeUpdate);
        PphPuphaInstitution testPphPuphaInstitution = pphPuphaInstitutionList.get(pphPuphaInstitutionList.size() - 1);
        assertThat(testPphPuphaInstitution.getExternalId()).isEqualTo(UPDATED_EXTERNAL_ID);
        assertThat(testPphPuphaInstitution.getCounty()).isEqualTo(UPDATED_COUNTY);
        assertThat(testPphPuphaInstitution.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testPphPuphaInstitution.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPphPuphaInstitution.getGyfkod()).isEqualTo(UPDATED_GYFKOD);
        assertThat(testPphPuphaInstitution.getUnitName()).isEqualTo(UPDATED_UNIT_NAME);
        assertThat(testPphPuphaInstitution.getDoctorName()).isEqualTo(UPDATED_DOCTOR_NAME);
        assertThat(testPphPuphaInstitution.getRegistrationNo()).isEqualTo(UPDATED_REGISTRATION_NO);
        assertThat(testPphPuphaInstitution.getInstitutionType()).isEqualTo(UPDATED_INSTITUTION_TYPE);
        assertThat(testPphPuphaInstitution.isActivePuphaData()).isEqualTo(UPDATED_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingPphPuphaInstitution() throws Exception {
        int databaseSizeBeforeUpdate = pphPuphaInstitutionRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPphPuphaInstitutionMockMvc.perform(put("/api/pph-pupha-institutions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphPuphaInstitution)))
            .andExpect(status().isBadRequest());

        // Validate the PphPuphaInstitution in the database
        List<PphPuphaInstitution> pphPuphaInstitutionList = pphPuphaInstitutionRepository.findAll();
        assertThat(pphPuphaInstitutionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePphPuphaInstitution() throws Exception {
        // Initialize the database
        pphPuphaInstitutionRepository.saveAndFlush(pphPuphaInstitution);

        int databaseSizeBeforeDelete = pphPuphaInstitutionRepository.findAll().size();

        // Delete the pphPuphaInstitution
        restPphPuphaInstitutionMockMvc.perform(delete("/api/pph-pupha-institutions/{id}", pphPuphaInstitution.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PphPuphaInstitution> pphPuphaInstitutionList = pphPuphaInstitutionRepository.findAll();
        assertThat(pphPuphaInstitutionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
