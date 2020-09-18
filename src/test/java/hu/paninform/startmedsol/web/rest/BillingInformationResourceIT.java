package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.BillingInformation;
import hu.paninform.startmedsol.repository.BillingInformationRepository;
import hu.paninform.startmedsol.service.BillingInformationService;
import hu.paninform.startmedsol.service.dto.BillingInformationDTO;
import hu.paninform.startmedsol.service.mapper.BillingInformationMapper;

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
 * Integration tests for the {@link BillingInformationResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BillingInformationResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TAXNUMBER = "10215100-2-74";
    private static final String UPDATED_TAXNUMBER = "10049152-5-14";

    @Autowired
    private BillingInformationRepository billingInformationRepository;

    @Autowired
    private BillingInformationMapper billingInformationMapper;

    @Autowired
    private BillingInformationService billingInformationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBillingInformationMockMvc;

    private BillingInformation billingInformation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BillingInformation createEntity(EntityManager em) {
        BillingInformation billingInformation = new BillingInformation()
            .name(DEFAULT_NAME)
            .taxnumber(DEFAULT_TAXNUMBER);
        return billingInformation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BillingInformation createUpdatedEntity(EntityManager em) {
        BillingInformation billingInformation = new BillingInformation()
            .name(UPDATED_NAME)
            .taxnumber(UPDATED_TAXNUMBER);
        return billingInformation;
    }

    @BeforeEach
    public void initTest() {
        billingInformation = createEntity(em);
    }

    @Test
    @Transactional
    public void createBillingInformation() throws Exception {
        int databaseSizeBeforeCreate = billingInformationRepository.findAll().size();
        // Create the BillingInformation
        BillingInformationDTO billingInformationDTO = billingInformationMapper.toDto(billingInformation);
        restBillingInformationMockMvc.perform(post("/api/billing-informations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billingInformationDTO)))
            .andExpect(status().isCreated());

        // Validate the BillingInformation in the database
        List<BillingInformation> billingInformationList = billingInformationRepository.findAll();
        assertThat(billingInformationList).hasSize(databaseSizeBeforeCreate + 1);
        BillingInformation testBillingInformation = billingInformationList.get(billingInformationList.size() - 1);
        assertThat(testBillingInformation.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBillingInformation.getTaxnumber()).isEqualTo(DEFAULT_TAXNUMBER);
    }

    @Test
    @Transactional
    public void createBillingInformationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = billingInformationRepository.findAll().size();

        // Create the BillingInformation with an existing ID
        billingInformation.setId(1L);
        BillingInformationDTO billingInformationDTO = billingInformationMapper.toDto(billingInformation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBillingInformationMockMvc.perform(post("/api/billing-informations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billingInformationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BillingInformation in the database
        List<BillingInformation> billingInformationList = billingInformationRepository.findAll();
        assertThat(billingInformationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = billingInformationRepository.findAll().size();
        // set the field null
        billingInformation.setName(null);

        // Create the BillingInformation, which fails.
        BillingInformationDTO billingInformationDTO = billingInformationMapper.toDto(billingInformation);


        restBillingInformationMockMvc.perform(post("/api/billing-informations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billingInformationDTO)))
            .andExpect(status().isBadRequest());

        List<BillingInformation> billingInformationList = billingInformationRepository.findAll();
        assertThat(billingInformationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBillingInformations() throws Exception {
        // Initialize the database
        billingInformationRepository.saveAndFlush(billingInformation);

        // Get all the billingInformationList
        restBillingInformationMockMvc.perform(get("/api/billing-informations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(billingInformation.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].taxnumber").value(hasItem(DEFAULT_TAXNUMBER)));
    }
    
    @Test
    @Transactional
    public void getBillingInformation() throws Exception {
        // Initialize the database
        billingInformationRepository.saveAndFlush(billingInformation);

        // Get the billingInformation
        restBillingInformationMockMvc.perform(get("/api/billing-informations/{id}", billingInformation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(billingInformation.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.taxnumber").value(DEFAULT_TAXNUMBER));
    }
    @Test
    @Transactional
    public void getNonExistingBillingInformation() throws Exception {
        // Get the billingInformation
        restBillingInformationMockMvc.perform(get("/api/billing-informations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBillingInformation() throws Exception {
        // Initialize the database
        billingInformationRepository.saveAndFlush(billingInformation);

        int databaseSizeBeforeUpdate = billingInformationRepository.findAll().size();

        // Update the billingInformation
        BillingInformation updatedBillingInformation = billingInformationRepository.findById(billingInformation.getId()).get();
        // Disconnect from session so that the updates on updatedBillingInformation are not directly saved in db
        em.detach(updatedBillingInformation);
        updatedBillingInformation
            .name(UPDATED_NAME)
            .taxnumber(UPDATED_TAXNUMBER);
        BillingInformationDTO billingInformationDTO = billingInformationMapper.toDto(updatedBillingInformation);

        restBillingInformationMockMvc.perform(put("/api/billing-informations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billingInformationDTO)))
            .andExpect(status().isOk());

        // Validate the BillingInformation in the database
        List<BillingInformation> billingInformationList = billingInformationRepository.findAll();
        assertThat(billingInformationList).hasSize(databaseSizeBeforeUpdate);
        BillingInformation testBillingInformation = billingInformationList.get(billingInformationList.size() - 1);
        assertThat(testBillingInformation.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBillingInformation.getTaxnumber()).isEqualTo(UPDATED_TAXNUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingBillingInformation() throws Exception {
        int databaseSizeBeforeUpdate = billingInformationRepository.findAll().size();

        // Create the BillingInformation
        BillingInformationDTO billingInformationDTO = billingInformationMapper.toDto(billingInformation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBillingInformationMockMvc.perform(put("/api/billing-informations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billingInformationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BillingInformation in the database
        List<BillingInformation> billingInformationList = billingInformationRepository.findAll();
        assertThat(billingInformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBillingInformation() throws Exception {
        // Initialize the database
        billingInformationRepository.saveAndFlush(billingInformation);

        int databaseSizeBeforeDelete = billingInformationRepository.findAll().size();

        // Delete the billingInformation
        restBillingInformationMockMvc.perform(delete("/api/billing-informations/{id}", billingInformation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BillingInformation> billingInformationList = billingInformationRepository.findAll();
        assertThat(billingInformationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
