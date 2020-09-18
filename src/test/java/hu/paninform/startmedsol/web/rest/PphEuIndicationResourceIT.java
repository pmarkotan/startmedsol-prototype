package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.PphEuIndication;
import hu.paninform.startmedsol.repository.PphEuIndicationRepository;

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
 * Integration tests for the {@link PphEuIndicationResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PphEuIndicationResourceIT {

    private static final Integer DEFAULT_EXTERNAL_ID = 1;
    private static final Integer UPDATED_EXTERNAL_ID = 2;

    private static final Integer DEFAULT_INDICATION_NO = 1;
    private static final Integer UPDATED_INDICATION_NO = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE_PUPHA_DATA = false;
    private static final Boolean UPDATED_ACTIVE_PUPHA_DATA = true;

    @Autowired
    private PphEuIndicationRepository pphEuIndicationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPphEuIndicationMockMvc;

    private PphEuIndication pphEuIndication;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphEuIndication createEntity(EntityManager em) {
        PphEuIndication pphEuIndication = new PphEuIndication()
            .externalId(DEFAULT_EXTERNAL_ID)
            .indicationNo(DEFAULT_INDICATION_NO)
            .description(DEFAULT_DESCRIPTION)
            .activePuphaData(DEFAULT_ACTIVE_PUPHA_DATA);
        return pphEuIndication;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphEuIndication createUpdatedEntity(EntityManager em) {
        PphEuIndication pphEuIndication = new PphEuIndication()
            .externalId(UPDATED_EXTERNAL_ID)
            .indicationNo(UPDATED_INDICATION_NO)
            .description(UPDATED_DESCRIPTION)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);
        return pphEuIndication;
    }

    @BeforeEach
    public void initTest() {
        pphEuIndication = createEntity(em);
    }

    @Test
    @Transactional
    public void createPphEuIndication() throws Exception {
        int databaseSizeBeforeCreate = pphEuIndicationRepository.findAll().size();
        // Create the PphEuIndication
        restPphEuIndicationMockMvc.perform(post("/api/pph-eu-indications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphEuIndication)))
            .andExpect(status().isCreated());

        // Validate the PphEuIndication in the database
        List<PphEuIndication> pphEuIndicationList = pphEuIndicationRepository.findAll();
        assertThat(pphEuIndicationList).hasSize(databaseSizeBeforeCreate + 1);
        PphEuIndication testPphEuIndication = pphEuIndicationList.get(pphEuIndicationList.size() - 1);
        assertThat(testPphEuIndication.getExternalId()).isEqualTo(DEFAULT_EXTERNAL_ID);
        assertThat(testPphEuIndication.getIndicationNo()).isEqualTo(DEFAULT_INDICATION_NO);
        assertThat(testPphEuIndication.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPphEuIndication.isActivePuphaData()).isEqualTo(DEFAULT_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void createPphEuIndicationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pphEuIndicationRepository.findAll().size();

        // Create the PphEuIndication with an existing ID
        pphEuIndication.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPphEuIndicationMockMvc.perform(post("/api/pph-eu-indications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphEuIndication)))
            .andExpect(status().isBadRequest());

        // Validate the PphEuIndication in the database
        List<PphEuIndication> pphEuIndicationList = pphEuIndicationRepository.findAll();
        assertThat(pphEuIndicationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkActivePuphaDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = pphEuIndicationRepository.findAll().size();
        // set the field null
        pphEuIndication.setActivePuphaData(null);

        // Create the PphEuIndication, which fails.


        restPphEuIndicationMockMvc.perform(post("/api/pph-eu-indications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphEuIndication)))
            .andExpect(status().isBadRequest());

        List<PphEuIndication> pphEuIndicationList = pphEuIndicationRepository.findAll();
        assertThat(pphEuIndicationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPphEuIndications() throws Exception {
        // Initialize the database
        pphEuIndicationRepository.saveAndFlush(pphEuIndication);

        // Get all the pphEuIndicationList
        restPphEuIndicationMockMvc.perform(get("/api/pph-eu-indications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pphEuIndication.getId().intValue())))
            .andExpect(jsonPath("$.[*].externalId").value(hasItem(DEFAULT_EXTERNAL_ID)))
            .andExpect(jsonPath("$.[*].indicationNo").value(hasItem(DEFAULT_INDICATION_NO)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].activePuphaData").value(hasItem(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPphEuIndication() throws Exception {
        // Initialize the database
        pphEuIndicationRepository.saveAndFlush(pphEuIndication);

        // Get the pphEuIndication
        restPphEuIndicationMockMvc.perform(get("/api/pph-eu-indications/{id}", pphEuIndication.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pphEuIndication.getId().intValue()))
            .andExpect(jsonPath("$.externalId").value(DEFAULT_EXTERNAL_ID))
            .andExpect(jsonPath("$.indicationNo").value(DEFAULT_INDICATION_NO))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.activePuphaData").value(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPphEuIndication() throws Exception {
        // Get the pphEuIndication
        restPphEuIndicationMockMvc.perform(get("/api/pph-eu-indications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePphEuIndication() throws Exception {
        // Initialize the database
        pphEuIndicationRepository.saveAndFlush(pphEuIndication);

        int databaseSizeBeforeUpdate = pphEuIndicationRepository.findAll().size();

        // Update the pphEuIndication
        PphEuIndication updatedPphEuIndication = pphEuIndicationRepository.findById(pphEuIndication.getId()).get();
        // Disconnect from session so that the updates on updatedPphEuIndication are not directly saved in db
        em.detach(updatedPphEuIndication);
        updatedPphEuIndication
            .externalId(UPDATED_EXTERNAL_ID)
            .indicationNo(UPDATED_INDICATION_NO)
            .description(UPDATED_DESCRIPTION)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);

        restPphEuIndicationMockMvc.perform(put("/api/pph-eu-indications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPphEuIndication)))
            .andExpect(status().isOk());

        // Validate the PphEuIndication in the database
        List<PphEuIndication> pphEuIndicationList = pphEuIndicationRepository.findAll();
        assertThat(pphEuIndicationList).hasSize(databaseSizeBeforeUpdate);
        PphEuIndication testPphEuIndication = pphEuIndicationList.get(pphEuIndicationList.size() - 1);
        assertThat(testPphEuIndication.getExternalId()).isEqualTo(UPDATED_EXTERNAL_ID);
        assertThat(testPphEuIndication.getIndicationNo()).isEqualTo(UPDATED_INDICATION_NO);
        assertThat(testPphEuIndication.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPphEuIndication.isActivePuphaData()).isEqualTo(UPDATED_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingPphEuIndication() throws Exception {
        int databaseSizeBeforeUpdate = pphEuIndicationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPphEuIndicationMockMvc.perform(put("/api/pph-eu-indications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphEuIndication)))
            .andExpect(status().isBadRequest());

        // Validate the PphEuIndication in the database
        List<PphEuIndication> pphEuIndicationList = pphEuIndicationRepository.findAll();
        assertThat(pphEuIndicationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePphEuIndication() throws Exception {
        // Initialize the database
        pphEuIndicationRepository.saveAndFlush(pphEuIndication);

        int databaseSizeBeforeDelete = pphEuIndicationRepository.findAll().size();

        // Delete the pphEuIndication
        restPphEuIndicationMockMvc.perform(delete("/api/pph-eu-indications/{id}", pphEuIndication.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PphEuIndication> pphEuIndicationList = pphEuIndicationRepository.findAll();
        assertThat(pphEuIndicationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
