package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.PphSpecBudget;
import hu.paninform.startmedsol.repository.PphSpecBudgetRepository;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PphSpecBudgetResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PphSpecBudgetResourceIT {

    private static final String DEFAULT_INDICATION = "AAAAAAAAAA";
    private static final String UPDATED_INDICATION = "BBBBBBBBBB";

    private static final String DEFAULT_DIAGNOSIS_LIST = "AAAAAAAAAA";
    private static final String UPDATED_DIAGNOSIS_LIST = "BBBBBBBBBB";

    private static final Instant DEFAULT_VALID_FROM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_VALID_FROM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_VALID_TO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_VALID_TO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_ACTIVE_PUPHA_DATA = false;
    private static final Boolean UPDATED_ACTIVE_PUPHA_DATA = true;

    @Autowired
    private PphSpecBudgetRepository pphSpecBudgetRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPphSpecBudgetMockMvc;

    private PphSpecBudget pphSpecBudget;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphSpecBudget createEntity(EntityManager em) {
        PphSpecBudget pphSpecBudget = new PphSpecBudget()
            .indication(DEFAULT_INDICATION)
            .diagnosisList(DEFAULT_DIAGNOSIS_LIST)
            .validFrom(DEFAULT_VALID_FROM)
            .validTo(DEFAULT_VALID_TO)
            .activePuphaData(DEFAULT_ACTIVE_PUPHA_DATA);
        return pphSpecBudget;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphSpecBudget createUpdatedEntity(EntityManager em) {
        PphSpecBudget pphSpecBudget = new PphSpecBudget()
            .indication(UPDATED_INDICATION)
            .diagnosisList(UPDATED_DIAGNOSIS_LIST)
            .validFrom(UPDATED_VALID_FROM)
            .validTo(UPDATED_VALID_TO)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);
        return pphSpecBudget;
    }

    @BeforeEach
    public void initTest() {
        pphSpecBudget = createEntity(em);
    }

    @Test
    @Transactional
    public void createPphSpecBudget() throws Exception {
        int databaseSizeBeforeCreate = pphSpecBudgetRepository.findAll().size();
        // Create the PphSpecBudget
        restPphSpecBudgetMockMvc.perform(post("/api/pph-spec-budgets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphSpecBudget)))
            .andExpect(status().isCreated());

        // Validate the PphSpecBudget in the database
        List<PphSpecBudget> pphSpecBudgetList = pphSpecBudgetRepository.findAll();
        assertThat(pphSpecBudgetList).hasSize(databaseSizeBeforeCreate + 1);
        PphSpecBudget testPphSpecBudget = pphSpecBudgetList.get(pphSpecBudgetList.size() - 1);
        assertThat(testPphSpecBudget.getIndication()).isEqualTo(DEFAULT_INDICATION);
        assertThat(testPphSpecBudget.getDiagnosisList()).isEqualTo(DEFAULT_DIAGNOSIS_LIST);
        assertThat(testPphSpecBudget.getValidFrom()).isEqualTo(DEFAULT_VALID_FROM);
        assertThat(testPphSpecBudget.getValidTo()).isEqualTo(DEFAULT_VALID_TO);
        assertThat(testPphSpecBudget.isActivePuphaData()).isEqualTo(DEFAULT_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void createPphSpecBudgetWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pphSpecBudgetRepository.findAll().size();

        // Create the PphSpecBudget with an existing ID
        pphSpecBudget.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPphSpecBudgetMockMvc.perform(post("/api/pph-spec-budgets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphSpecBudget)))
            .andExpect(status().isBadRequest());

        // Validate the PphSpecBudget in the database
        List<PphSpecBudget> pphSpecBudgetList = pphSpecBudgetRepository.findAll();
        assertThat(pphSpecBudgetList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkActivePuphaDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = pphSpecBudgetRepository.findAll().size();
        // set the field null
        pphSpecBudget.setActivePuphaData(null);

        // Create the PphSpecBudget, which fails.


        restPphSpecBudgetMockMvc.perform(post("/api/pph-spec-budgets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphSpecBudget)))
            .andExpect(status().isBadRequest());

        List<PphSpecBudget> pphSpecBudgetList = pphSpecBudgetRepository.findAll();
        assertThat(pphSpecBudgetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPphSpecBudgets() throws Exception {
        // Initialize the database
        pphSpecBudgetRepository.saveAndFlush(pphSpecBudget);

        // Get all the pphSpecBudgetList
        restPphSpecBudgetMockMvc.perform(get("/api/pph-spec-budgets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pphSpecBudget.getId().intValue())))
            .andExpect(jsonPath("$.[*].indication").value(hasItem(DEFAULT_INDICATION)))
            .andExpect(jsonPath("$.[*].diagnosisList").value(hasItem(DEFAULT_DIAGNOSIS_LIST)))
            .andExpect(jsonPath("$.[*].validFrom").value(hasItem(DEFAULT_VALID_FROM.toString())))
            .andExpect(jsonPath("$.[*].validTo").value(hasItem(DEFAULT_VALID_TO.toString())))
            .andExpect(jsonPath("$.[*].activePuphaData").value(hasItem(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPphSpecBudget() throws Exception {
        // Initialize the database
        pphSpecBudgetRepository.saveAndFlush(pphSpecBudget);

        // Get the pphSpecBudget
        restPphSpecBudgetMockMvc.perform(get("/api/pph-spec-budgets/{id}", pphSpecBudget.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pphSpecBudget.getId().intValue()))
            .andExpect(jsonPath("$.indication").value(DEFAULT_INDICATION))
            .andExpect(jsonPath("$.diagnosisList").value(DEFAULT_DIAGNOSIS_LIST))
            .andExpect(jsonPath("$.validFrom").value(DEFAULT_VALID_FROM.toString()))
            .andExpect(jsonPath("$.validTo").value(DEFAULT_VALID_TO.toString()))
            .andExpect(jsonPath("$.activePuphaData").value(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPphSpecBudget() throws Exception {
        // Get the pphSpecBudget
        restPphSpecBudgetMockMvc.perform(get("/api/pph-spec-budgets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePphSpecBudget() throws Exception {
        // Initialize the database
        pphSpecBudgetRepository.saveAndFlush(pphSpecBudget);

        int databaseSizeBeforeUpdate = pphSpecBudgetRepository.findAll().size();

        // Update the pphSpecBudget
        PphSpecBudget updatedPphSpecBudget = pphSpecBudgetRepository.findById(pphSpecBudget.getId()).get();
        // Disconnect from session so that the updates on updatedPphSpecBudget are not directly saved in db
        em.detach(updatedPphSpecBudget);
        updatedPphSpecBudget
            .indication(UPDATED_INDICATION)
            .diagnosisList(UPDATED_DIAGNOSIS_LIST)
            .validFrom(UPDATED_VALID_FROM)
            .validTo(UPDATED_VALID_TO)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);

        restPphSpecBudgetMockMvc.perform(put("/api/pph-spec-budgets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPphSpecBudget)))
            .andExpect(status().isOk());

        // Validate the PphSpecBudget in the database
        List<PphSpecBudget> pphSpecBudgetList = pphSpecBudgetRepository.findAll();
        assertThat(pphSpecBudgetList).hasSize(databaseSizeBeforeUpdate);
        PphSpecBudget testPphSpecBudget = pphSpecBudgetList.get(pphSpecBudgetList.size() - 1);
        assertThat(testPphSpecBudget.getIndication()).isEqualTo(UPDATED_INDICATION);
        assertThat(testPphSpecBudget.getDiagnosisList()).isEqualTo(UPDATED_DIAGNOSIS_LIST);
        assertThat(testPphSpecBudget.getValidFrom()).isEqualTo(UPDATED_VALID_FROM);
        assertThat(testPphSpecBudget.getValidTo()).isEqualTo(UPDATED_VALID_TO);
        assertThat(testPphSpecBudget.isActivePuphaData()).isEqualTo(UPDATED_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingPphSpecBudget() throws Exception {
        int databaseSizeBeforeUpdate = pphSpecBudgetRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPphSpecBudgetMockMvc.perform(put("/api/pph-spec-budgets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphSpecBudget)))
            .andExpect(status().isBadRequest());

        // Validate the PphSpecBudget in the database
        List<PphSpecBudget> pphSpecBudgetList = pphSpecBudgetRepository.findAll();
        assertThat(pphSpecBudgetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePphSpecBudget() throws Exception {
        // Initialize the database
        pphSpecBudgetRepository.saveAndFlush(pphSpecBudget);

        int databaseSizeBeforeDelete = pphSpecBudgetRepository.findAll().size();

        // Delete the pphSpecBudget
        restPphSpecBudgetMockMvc.perform(delete("/api/pph-spec-budgets/{id}", pphSpecBudget.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PphSpecBudget> pphSpecBudgetList = pphSpecBudgetRepository.findAll();
        assertThat(pphSpecBudgetList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
