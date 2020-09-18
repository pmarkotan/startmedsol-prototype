package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.PphMedicineForm;
import hu.paninform.startmedsol.repository.PphMedicineFormRepository;

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
 * Integration tests for the {@link PphMedicineFormResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PphMedicineFormResourceIT {

    private static final Long DEFAULT_EXTERNAL_ID = 1L;
    private static final Long UPDATED_EXTERNAL_ID = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE_PUPHA_DATA = false;
    private static final Boolean UPDATED_ACTIVE_PUPHA_DATA = true;

    @Autowired
    private PphMedicineFormRepository pphMedicineFormRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPphMedicineFormMockMvc;

    private PphMedicineForm pphMedicineForm;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphMedicineForm createEntity(EntityManager em) {
        PphMedicineForm pphMedicineForm = new PphMedicineForm()
            .externalId(DEFAULT_EXTERNAL_ID)
            .name(DEFAULT_NAME)
            .activePuphaData(DEFAULT_ACTIVE_PUPHA_DATA);
        return pphMedicineForm;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphMedicineForm createUpdatedEntity(EntityManager em) {
        PphMedicineForm pphMedicineForm = new PphMedicineForm()
            .externalId(UPDATED_EXTERNAL_ID)
            .name(UPDATED_NAME)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);
        return pphMedicineForm;
    }

    @BeforeEach
    public void initTest() {
        pphMedicineForm = createEntity(em);
    }

    @Test
    @Transactional
    public void createPphMedicineForm() throws Exception {
        int databaseSizeBeforeCreate = pphMedicineFormRepository.findAll().size();
        // Create the PphMedicineForm
        restPphMedicineFormMockMvc.perform(post("/api/pph-medicine-forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphMedicineForm)))
            .andExpect(status().isCreated());

        // Validate the PphMedicineForm in the database
        List<PphMedicineForm> pphMedicineFormList = pphMedicineFormRepository.findAll();
        assertThat(pphMedicineFormList).hasSize(databaseSizeBeforeCreate + 1);
        PphMedicineForm testPphMedicineForm = pphMedicineFormList.get(pphMedicineFormList.size() - 1);
        assertThat(testPphMedicineForm.getExternalId()).isEqualTo(DEFAULT_EXTERNAL_ID);
        assertThat(testPphMedicineForm.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPphMedicineForm.isActivePuphaData()).isEqualTo(DEFAULT_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void createPphMedicineFormWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pphMedicineFormRepository.findAll().size();

        // Create the PphMedicineForm with an existing ID
        pphMedicineForm.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPphMedicineFormMockMvc.perform(post("/api/pph-medicine-forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphMedicineForm)))
            .andExpect(status().isBadRequest());

        // Validate the PphMedicineForm in the database
        List<PphMedicineForm> pphMedicineFormList = pphMedicineFormRepository.findAll();
        assertThat(pphMedicineFormList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkActivePuphaDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = pphMedicineFormRepository.findAll().size();
        // set the field null
        pphMedicineForm.setActivePuphaData(null);

        // Create the PphMedicineForm, which fails.


        restPphMedicineFormMockMvc.perform(post("/api/pph-medicine-forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphMedicineForm)))
            .andExpect(status().isBadRequest());

        List<PphMedicineForm> pphMedicineFormList = pphMedicineFormRepository.findAll();
        assertThat(pphMedicineFormList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPphMedicineForms() throws Exception {
        // Initialize the database
        pphMedicineFormRepository.saveAndFlush(pphMedicineForm);

        // Get all the pphMedicineFormList
        restPphMedicineFormMockMvc.perform(get("/api/pph-medicine-forms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pphMedicineForm.getId().intValue())))
            .andExpect(jsonPath("$.[*].externalId").value(hasItem(DEFAULT_EXTERNAL_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].activePuphaData").value(hasItem(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPphMedicineForm() throws Exception {
        // Initialize the database
        pphMedicineFormRepository.saveAndFlush(pphMedicineForm);

        // Get the pphMedicineForm
        restPphMedicineFormMockMvc.perform(get("/api/pph-medicine-forms/{id}", pphMedicineForm.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pphMedicineForm.getId().intValue()))
            .andExpect(jsonPath("$.externalId").value(DEFAULT_EXTERNAL_ID.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.activePuphaData").value(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPphMedicineForm() throws Exception {
        // Get the pphMedicineForm
        restPphMedicineFormMockMvc.perform(get("/api/pph-medicine-forms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePphMedicineForm() throws Exception {
        // Initialize the database
        pphMedicineFormRepository.saveAndFlush(pphMedicineForm);

        int databaseSizeBeforeUpdate = pphMedicineFormRepository.findAll().size();

        // Update the pphMedicineForm
        PphMedicineForm updatedPphMedicineForm = pphMedicineFormRepository.findById(pphMedicineForm.getId()).get();
        // Disconnect from session so that the updates on updatedPphMedicineForm are not directly saved in db
        em.detach(updatedPphMedicineForm);
        updatedPphMedicineForm
            .externalId(UPDATED_EXTERNAL_ID)
            .name(UPDATED_NAME)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);

        restPphMedicineFormMockMvc.perform(put("/api/pph-medicine-forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPphMedicineForm)))
            .andExpect(status().isOk());

        // Validate the PphMedicineForm in the database
        List<PphMedicineForm> pphMedicineFormList = pphMedicineFormRepository.findAll();
        assertThat(pphMedicineFormList).hasSize(databaseSizeBeforeUpdate);
        PphMedicineForm testPphMedicineForm = pphMedicineFormList.get(pphMedicineFormList.size() - 1);
        assertThat(testPphMedicineForm.getExternalId()).isEqualTo(UPDATED_EXTERNAL_ID);
        assertThat(testPphMedicineForm.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPphMedicineForm.isActivePuphaData()).isEqualTo(UPDATED_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingPphMedicineForm() throws Exception {
        int databaseSizeBeforeUpdate = pphMedicineFormRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPphMedicineFormMockMvc.perform(put("/api/pph-medicine-forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphMedicineForm)))
            .andExpect(status().isBadRequest());

        // Validate the PphMedicineForm in the database
        List<PphMedicineForm> pphMedicineFormList = pphMedicineFormRepository.findAll();
        assertThat(pphMedicineFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePphMedicineForm() throws Exception {
        // Initialize the database
        pphMedicineFormRepository.saveAndFlush(pphMedicineForm);

        int databaseSizeBeforeDelete = pphMedicineFormRepository.findAll().size();

        // Delete the pphMedicineForm
        restPphMedicineFormMockMvc.perform(delete("/api/pph-medicine-forms/{id}", pphMedicineForm.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PphMedicineForm> pphMedicineFormList = pphMedicineFormRepository.findAll();
        assertThat(pphMedicineFormList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
