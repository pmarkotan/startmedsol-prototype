package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.PphSubsidy;
import hu.paninform.startmedsol.repository.PphSubsidyRepository;

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

import hu.paninform.startmedsol.domain.enumeration.SubsidyCategory;
import hu.paninform.startmedsol.domain.enumeration.SubsidyType;
/**
 * Integration tests for the {@link PphSubsidyResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PphSubsidyResourceIT {

    private static final Double DEFAULT_PERCENT = 1D;
    private static final Double UPDATED_PERCENT = 2D;

    private static final Double DEFAULT_REFERENCE_DAILY_COST = 1D;
    private static final Double UPDATED_REFERENCE_DAILY_COST = 2D;

    private static final Double DEFAULT_NET_SUBSIDY = 1D;
    private static final Double UPDATED_NET_SUBSIDY = 2D;

    private static final Double DEFAULT_GROSS_SUBSIDY = 1D;
    private static final Double UPDATED_GROSS_SUBSIDY = 2D;

    private static final Double DEFAULT_CONSUMER_PRICE = 1D;
    private static final Double UPDATED_CONSUMER_PRICE = 2D;

    private static final Double DEFAULT_DAILY_COST_ON_CONS_PRICE = 1D;
    private static final Double UPDATED_DAILY_COST_ON_CONS_PRICE = 2D;

    private static final Integer DEFAULT_DURATION = 1;
    private static final Integer UPDATED_DURATION = 2;

    private static final Integer DEFAULT_QUANTITY_ALLOWED = 1;
    private static final Integer UPDATED_QUANTITY_ALLOWED = 2;

    private static final String DEFAULT_EUEM_PONTOK = "AAAAAAAAAA";
    private static final String UPDATED_EUEM_PONTOK = "BBBBBBBBBB";

    private static final SubsidyCategory DEFAULT_SUBSIDY_CATEGORY = SubsidyCategory.EU50;
    private static final SubsidyCategory UPDATED_SUBSIDY_CATEGORY = SubsidyCategory.EU70;

    private static final SubsidyType DEFAULT_SUBSIDY_TYPE = SubsidyType.NOMIN;
    private static final SubsidyType UPDATED_SUBSIDY_TYPE = SubsidyType.HFIX;

    private static final Boolean DEFAULT_ACTIVE_PUPHA_DATA = false;
    private static final Boolean UPDATED_ACTIVE_PUPHA_DATA = true;

    @Autowired
    private PphSubsidyRepository pphSubsidyRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPphSubsidyMockMvc;

    private PphSubsidy pphSubsidy;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphSubsidy createEntity(EntityManager em) {
        PphSubsidy pphSubsidy = new PphSubsidy()
            .percent(DEFAULT_PERCENT)
            .referenceDailyCost(DEFAULT_REFERENCE_DAILY_COST)
            .netSubsidy(DEFAULT_NET_SUBSIDY)
            .grossSubsidy(DEFAULT_GROSS_SUBSIDY)
            .consumerPrice(DEFAULT_CONSUMER_PRICE)
            .dailyCostOnConsPrice(DEFAULT_DAILY_COST_ON_CONS_PRICE)
            .duration(DEFAULT_DURATION)
            .quantityAllowed(DEFAULT_QUANTITY_ALLOWED)
            .euemPontok(DEFAULT_EUEM_PONTOK)
            .subsidyCategory(DEFAULT_SUBSIDY_CATEGORY)
            .subsidyType(DEFAULT_SUBSIDY_TYPE)
            .activePuphaData(DEFAULT_ACTIVE_PUPHA_DATA);
        return pphSubsidy;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphSubsidy createUpdatedEntity(EntityManager em) {
        PphSubsidy pphSubsidy = new PphSubsidy()
            .percent(UPDATED_PERCENT)
            .referenceDailyCost(UPDATED_REFERENCE_DAILY_COST)
            .netSubsidy(UPDATED_NET_SUBSIDY)
            .grossSubsidy(UPDATED_GROSS_SUBSIDY)
            .consumerPrice(UPDATED_CONSUMER_PRICE)
            .dailyCostOnConsPrice(UPDATED_DAILY_COST_ON_CONS_PRICE)
            .duration(UPDATED_DURATION)
            .quantityAllowed(UPDATED_QUANTITY_ALLOWED)
            .euemPontok(UPDATED_EUEM_PONTOK)
            .subsidyCategory(UPDATED_SUBSIDY_CATEGORY)
            .subsidyType(UPDATED_SUBSIDY_TYPE)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);
        return pphSubsidy;
    }

    @BeforeEach
    public void initTest() {
        pphSubsidy = createEntity(em);
    }

    @Test
    @Transactional
    public void createPphSubsidy() throws Exception {
        int databaseSizeBeforeCreate = pphSubsidyRepository.findAll().size();
        // Create the PphSubsidy
        restPphSubsidyMockMvc.perform(post("/api/pph-subsidies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphSubsidy)))
            .andExpect(status().isCreated());

        // Validate the PphSubsidy in the database
        List<PphSubsidy> pphSubsidyList = pphSubsidyRepository.findAll();
        assertThat(pphSubsidyList).hasSize(databaseSizeBeforeCreate + 1);
        PphSubsidy testPphSubsidy = pphSubsidyList.get(pphSubsidyList.size() - 1);
        assertThat(testPphSubsidy.getPercent()).isEqualTo(DEFAULT_PERCENT);
        assertThat(testPphSubsidy.getReferenceDailyCost()).isEqualTo(DEFAULT_REFERENCE_DAILY_COST);
        assertThat(testPphSubsidy.getNetSubsidy()).isEqualTo(DEFAULT_NET_SUBSIDY);
        assertThat(testPphSubsidy.getGrossSubsidy()).isEqualTo(DEFAULT_GROSS_SUBSIDY);
        assertThat(testPphSubsidy.getConsumerPrice()).isEqualTo(DEFAULT_CONSUMER_PRICE);
        assertThat(testPphSubsidy.getDailyCostOnConsPrice()).isEqualTo(DEFAULT_DAILY_COST_ON_CONS_PRICE);
        assertThat(testPphSubsidy.getDuration()).isEqualTo(DEFAULT_DURATION);
        assertThat(testPphSubsidy.getQuantityAllowed()).isEqualTo(DEFAULT_QUANTITY_ALLOWED);
        assertThat(testPphSubsidy.getEuemPontok()).isEqualTo(DEFAULT_EUEM_PONTOK);
        assertThat(testPphSubsidy.getSubsidyCategory()).isEqualTo(DEFAULT_SUBSIDY_CATEGORY);
        assertThat(testPphSubsidy.getSubsidyType()).isEqualTo(DEFAULT_SUBSIDY_TYPE);
        assertThat(testPphSubsidy.isActivePuphaData()).isEqualTo(DEFAULT_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void createPphSubsidyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pphSubsidyRepository.findAll().size();

        // Create the PphSubsidy with an existing ID
        pphSubsidy.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPphSubsidyMockMvc.perform(post("/api/pph-subsidies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphSubsidy)))
            .andExpect(status().isBadRequest());

        // Validate the PphSubsidy in the database
        List<PphSubsidy> pphSubsidyList = pphSubsidyRepository.findAll();
        assertThat(pphSubsidyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkActivePuphaDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = pphSubsidyRepository.findAll().size();
        // set the field null
        pphSubsidy.setActivePuphaData(null);

        // Create the PphSubsidy, which fails.


        restPphSubsidyMockMvc.perform(post("/api/pph-subsidies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphSubsidy)))
            .andExpect(status().isBadRequest());

        List<PphSubsidy> pphSubsidyList = pphSubsidyRepository.findAll();
        assertThat(pphSubsidyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPphSubsidies() throws Exception {
        // Initialize the database
        pphSubsidyRepository.saveAndFlush(pphSubsidy);

        // Get all the pphSubsidyList
        restPphSubsidyMockMvc.perform(get("/api/pph-subsidies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pphSubsidy.getId().intValue())))
            .andExpect(jsonPath("$.[*].percent").value(hasItem(DEFAULT_PERCENT.doubleValue())))
            .andExpect(jsonPath("$.[*].referenceDailyCost").value(hasItem(DEFAULT_REFERENCE_DAILY_COST.doubleValue())))
            .andExpect(jsonPath("$.[*].netSubsidy").value(hasItem(DEFAULT_NET_SUBSIDY.doubleValue())))
            .andExpect(jsonPath("$.[*].grossSubsidy").value(hasItem(DEFAULT_GROSS_SUBSIDY.doubleValue())))
            .andExpect(jsonPath("$.[*].consumerPrice").value(hasItem(DEFAULT_CONSUMER_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].dailyCostOnConsPrice").value(hasItem(DEFAULT_DAILY_COST_ON_CONS_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].duration").value(hasItem(DEFAULT_DURATION)))
            .andExpect(jsonPath("$.[*].quantityAllowed").value(hasItem(DEFAULT_QUANTITY_ALLOWED)))
            .andExpect(jsonPath("$.[*].euemPontok").value(hasItem(DEFAULT_EUEM_PONTOK)))
            .andExpect(jsonPath("$.[*].subsidyCategory").value(hasItem(DEFAULT_SUBSIDY_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].subsidyType").value(hasItem(DEFAULT_SUBSIDY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].activePuphaData").value(hasItem(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPphSubsidy() throws Exception {
        // Initialize the database
        pphSubsidyRepository.saveAndFlush(pphSubsidy);

        // Get the pphSubsidy
        restPphSubsidyMockMvc.perform(get("/api/pph-subsidies/{id}", pphSubsidy.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pphSubsidy.getId().intValue()))
            .andExpect(jsonPath("$.percent").value(DEFAULT_PERCENT.doubleValue()))
            .andExpect(jsonPath("$.referenceDailyCost").value(DEFAULT_REFERENCE_DAILY_COST.doubleValue()))
            .andExpect(jsonPath("$.netSubsidy").value(DEFAULT_NET_SUBSIDY.doubleValue()))
            .andExpect(jsonPath("$.grossSubsidy").value(DEFAULT_GROSS_SUBSIDY.doubleValue()))
            .andExpect(jsonPath("$.consumerPrice").value(DEFAULT_CONSUMER_PRICE.doubleValue()))
            .andExpect(jsonPath("$.dailyCostOnConsPrice").value(DEFAULT_DAILY_COST_ON_CONS_PRICE.doubleValue()))
            .andExpect(jsonPath("$.duration").value(DEFAULT_DURATION))
            .andExpect(jsonPath("$.quantityAllowed").value(DEFAULT_QUANTITY_ALLOWED))
            .andExpect(jsonPath("$.euemPontok").value(DEFAULT_EUEM_PONTOK))
            .andExpect(jsonPath("$.subsidyCategory").value(DEFAULT_SUBSIDY_CATEGORY.toString()))
            .andExpect(jsonPath("$.subsidyType").value(DEFAULT_SUBSIDY_TYPE.toString()))
            .andExpect(jsonPath("$.activePuphaData").value(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPphSubsidy() throws Exception {
        // Get the pphSubsidy
        restPphSubsidyMockMvc.perform(get("/api/pph-subsidies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePphSubsidy() throws Exception {
        // Initialize the database
        pphSubsidyRepository.saveAndFlush(pphSubsidy);

        int databaseSizeBeforeUpdate = pphSubsidyRepository.findAll().size();

        // Update the pphSubsidy
        PphSubsidy updatedPphSubsidy = pphSubsidyRepository.findById(pphSubsidy.getId()).get();
        // Disconnect from session so that the updates on updatedPphSubsidy are not directly saved in db
        em.detach(updatedPphSubsidy);
        updatedPphSubsidy
            .percent(UPDATED_PERCENT)
            .referenceDailyCost(UPDATED_REFERENCE_DAILY_COST)
            .netSubsidy(UPDATED_NET_SUBSIDY)
            .grossSubsidy(UPDATED_GROSS_SUBSIDY)
            .consumerPrice(UPDATED_CONSUMER_PRICE)
            .dailyCostOnConsPrice(UPDATED_DAILY_COST_ON_CONS_PRICE)
            .duration(UPDATED_DURATION)
            .quantityAllowed(UPDATED_QUANTITY_ALLOWED)
            .euemPontok(UPDATED_EUEM_PONTOK)
            .subsidyCategory(UPDATED_SUBSIDY_CATEGORY)
            .subsidyType(UPDATED_SUBSIDY_TYPE)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);

        restPphSubsidyMockMvc.perform(put("/api/pph-subsidies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPphSubsidy)))
            .andExpect(status().isOk());

        // Validate the PphSubsidy in the database
        List<PphSubsidy> pphSubsidyList = pphSubsidyRepository.findAll();
        assertThat(pphSubsidyList).hasSize(databaseSizeBeforeUpdate);
        PphSubsidy testPphSubsidy = pphSubsidyList.get(pphSubsidyList.size() - 1);
        assertThat(testPphSubsidy.getPercent()).isEqualTo(UPDATED_PERCENT);
        assertThat(testPphSubsidy.getReferenceDailyCost()).isEqualTo(UPDATED_REFERENCE_DAILY_COST);
        assertThat(testPphSubsidy.getNetSubsidy()).isEqualTo(UPDATED_NET_SUBSIDY);
        assertThat(testPphSubsidy.getGrossSubsidy()).isEqualTo(UPDATED_GROSS_SUBSIDY);
        assertThat(testPphSubsidy.getConsumerPrice()).isEqualTo(UPDATED_CONSUMER_PRICE);
        assertThat(testPphSubsidy.getDailyCostOnConsPrice()).isEqualTo(UPDATED_DAILY_COST_ON_CONS_PRICE);
        assertThat(testPphSubsidy.getDuration()).isEqualTo(UPDATED_DURATION);
        assertThat(testPphSubsidy.getQuantityAllowed()).isEqualTo(UPDATED_QUANTITY_ALLOWED);
        assertThat(testPphSubsidy.getEuemPontok()).isEqualTo(UPDATED_EUEM_PONTOK);
        assertThat(testPphSubsidy.getSubsidyCategory()).isEqualTo(UPDATED_SUBSIDY_CATEGORY);
        assertThat(testPphSubsidy.getSubsidyType()).isEqualTo(UPDATED_SUBSIDY_TYPE);
        assertThat(testPphSubsidy.isActivePuphaData()).isEqualTo(UPDATED_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingPphSubsidy() throws Exception {
        int databaseSizeBeforeUpdate = pphSubsidyRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPphSubsidyMockMvc.perform(put("/api/pph-subsidies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphSubsidy)))
            .andExpect(status().isBadRequest());

        // Validate the PphSubsidy in the database
        List<PphSubsidy> pphSubsidyList = pphSubsidyRepository.findAll();
        assertThat(pphSubsidyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePphSubsidy() throws Exception {
        // Initialize the database
        pphSubsidyRepository.saveAndFlush(pphSubsidy);

        int databaseSizeBeforeDelete = pphSubsidyRepository.findAll().size();

        // Delete the pphSubsidy
        restPphSubsidyMockMvc.perform(delete("/api/pph-subsidies/{id}", pphSubsidy.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PphSubsidy> pphSubsidyList = pphSubsidyRepository.findAll();
        assertThat(pphSubsidyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
