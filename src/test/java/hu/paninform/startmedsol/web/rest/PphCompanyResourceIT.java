package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.PphCompany;
import hu.paninform.startmedsol.repository.PphCompanyRepository;

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
 * Integration tests for the {@link PphCompanyResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PphCompanyResourceIT {

    private static final Long DEFAULT_EXTERNAL_ID = 1L;
    private static final Long UPDATED_EXTERNAL_ID = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE_PUPHA_DATA = false;
    private static final Boolean UPDATED_ACTIVE_PUPHA_DATA = true;

    @Autowired
    private PphCompanyRepository pphCompanyRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPphCompanyMockMvc;

    private PphCompany pphCompany;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphCompany createEntity(EntityManager em) {
        PphCompany pphCompany = new PphCompany()
            .externalId(DEFAULT_EXTERNAL_ID)
            .name(DEFAULT_NAME)
            .activePuphaData(DEFAULT_ACTIVE_PUPHA_DATA);
        return pphCompany;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphCompany createUpdatedEntity(EntityManager em) {
        PphCompany pphCompany = new PphCompany()
            .externalId(UPDATED_EXTERNAL_ID)
            .name(UPDATED_NAME)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);
        return pphCompany;
    }

    @BeforeEach
    public void initTest() {
        pphCompany = createEntity(em);
    }

    @Test
    @Transactional
    public void createPphCompany() throws Exception {
        int databaseSizeBeforeCreate = pphCompanyRepository.findAll().size();
        // Create the PphCompany
        restPphCompanyMockMvc.perform(post("/api/pph-companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphCompany)))
            .andExpect(status().isCreated());

        // Validate the PphCompany in the database
        List<PphCompany> pphCompanyList = pphCompanyRepository.findAll();
        assertThat(pphCompanyList).hasSize(databaseSizeBeforeCreate + 1);
        PphCompany testPphCompany = pphCompanyList.get(pphCompanyList.size() - 1);
        assertThat(testPphCompany.getExternalId()).isEqualTo(DEFAULT_EXTERNAL_ID);
        assertThat(testPphCompany.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPphCompany.isActivePuphaData()).isEqualTo(DEFAULT_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void createPphCompanyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pphCompanyRepository.findAll().size();

        // Create the PphCompany with an existing ID
        pphCompany.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPphCompanyMockMvc.perform(post("/api/pph-companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphCompany)))
            .andExpect(status().isBadRequest());

        // Validate the PphCompany in the database
        List<PphCompany> pphCompanyList = pphCompanyRepository.findAll();
        assertThat(pphCompanyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkActivePuphaDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = pphCompanyRepository.findAll().size();
        // set the field null
        pphCompany.setActivePuphaData(null);

        // Create the PphCompany, which fails.


        restPphCompanyMockMvc.perform(post("/api/pph-companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphCompany)))
            .andExpect(status().isBadRequest());

        List<PphCompany> pphCompanyList = pphCompanyRepository.findAll();
        assertThat(pphCompanyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPphCompanies() throws Exception {
        // Initialize the database
        pphCompanyRepository.saveAndFlush(pphCompany);

        // Get all the pphCompanyList
        restPphCompanyMockMvc.perform(get("/api/pph-companies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pphCompany.getId().intValue())))
            .andExpect(jsonPath("$.[*].externalId").value(hasItem(DEFAULT_EXTERNAL_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].activePuphaData").value(hasItem(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPphCompany() throws Exception {
        // Initialize the database
        pphCompanyRepository.saveAndFlush(pphCompany);

        // Get the pphCompany
        restPphCompanyMockMvc.perform(get("/api/pph-companies/{id}", pphCompany.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pphCompany.getId().intValue()))
            .andExpect(jsonPath("$.externalId").value(DEFAULT_EXTERNAL_ID.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.activePuphaData").value(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPphCompany() throws Exception {
        // Get the pphCompany
        restPphCompanyMockMvc.perform(get("/api/pph-companies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePphCompany() throws Exception {
        // Initialize the database
        pphCompanyRepository.saveAndFlush(pphCompany);

        int databaseSizeBeforeUpdate = pphCompanyRepository.findAll().size();

        // Update the pphCompany
        PphCompany updatedPphCompany = pphCompanyRepository.findById(pphCompany.getId()).get();
        // Disconnect from session so that the updates on updatedPphCompany are not directly saved in db
        em.detach(updatedPphCompany);
        updatedPphCompany
            .externalId(UPDATED_EXTERNAL_ID)
            .name(UPDATED_NAME)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);

        restPphCompanyMockMvc.perform(put("/api/pph-companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPphCompany)))
            .andExpect(status().isOk());

        // Validate the PphCompany in the database
        List<PphCompany> pphCompanyList = pphCompanyRepository.findAll();
        assertThat(pphCompanyList).hasSize(databaseSizeBeforeUpdate);
        PphCompany testPphCompany = pphCompanyList.get(pphCompanyList.size() - 1);
        assertThat(testPphCompany.getExternalId()).isEqualTo(UPDATED_EXTERNAL_ID);
        assertThat(testPphCompany.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPphCompany.isActivePuphaData()).isEqualTo(UPDATED_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingPphCompany() throws Exception {
        int databaseSizeBeforeUpdate = pphCompanyRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPphCompanyMockMvc.perform(put("/api/pph-companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphCompany)))
            .andExpect(status().isBadRequest());

        // Validate the PphCompany in the database
        List<PphCompany> pphCompanyList = pphCompanyRepository.findAll();
        assertThat(pphCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePphCompany() throws Exception {
        // Initialize the database
        pphCompanyRepository.saveAndFlush(pphCompany);

        int databaseSizeBeforeDelete = pphCompanyRepository.findAll().size();

        // Delete the pphCompany
        restPphCompanyMockMvc.perform(delete("/api/pph-companies/{id}", pphCompany.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PphCompany> pphCompanyList = pphCompanyRepository.findAll();
        assertThat(pphCompanyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
