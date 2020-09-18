package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.MedicalService;
import hu.paninform.startmedsol.domain.Praxis;
import hu.paninform.startmedsol.domain.Provider;
import hu.paninform.startmedsol.repository.MedicalServiceRepository;
import hu.paninform.startmedsol.service.MedicalServiceService;
import hu.paninform.startmedsol.service.dto.MedicalServiceDTO;
import hu.paninform.startmedsol.service.mapper.MedicalServiceMapper;

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

import hu.paninform.startmedsol.domain.enumeration.MedicalServiceUnit;
import hu.paninform.startmedsol.domain.enumeration.TaxRate;
/**
 * Integration tests for the {@link MedicalServiceResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MedicalServiceResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_GROSS_PRICE = 1;
    private static final Integer UPDATED_GROSS_PRICE = 2;

    private static final MedicalServiceUnit DEFAULT_UNIT = MedicalServiceUnit.HOUR;
    private static final MedicalServiceUnit UPDATED_UNIT = MedicalServiceUnit.PIECE;

    private static final String DEFAULT_STATISTICAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_STATISTICAL_CODE = "BBBBBBBBBB";

    private static final TaxRate DEFAULT_TAX_RATE = TaxRate.TX_27;
    private static final TaxRate UPDATED_TAX_RATE = TaxRate.TX_27;

    @Autowired
    private MedicalServiceRepository medicalServiceRepository;

    @Autowired
    private MedicalServiceMapper medicalServiceMapper;

    @Autowired
    private MedicalServiceService medicalServiceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMedicalServiceMockMvc;

    private MedicalService medicalService;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalService createEntity(EntityManager em) {
        MedicalService medicalService = new MedicalService()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .grossPrice(DEFAULT_GROSS_PRICE)
            .unit(DEFAULT_UNIT)
            .statisticalCode(DEFAULT_STATISTICAL_CODE)
            .taxRate(DEFAULT_TAX_RATE);
        // Add required entity
        Praxis praxis;
        if (TestUtil.findAll(em, Praxis.class).isEmpty()) {
            praxis = PraxisResourceIT.createEntity(em);
            em.persist(praxis);
            em.flush();
        } else {
            praxis = TestUtil.findAll(em, Praxis.class).get(0);
        }
        medicalService.setPraxis(praxis);
        // Add required entity
        Provider provider;
        if (TestUtil.findAll(em, Provider.class).isEmpty()) {
            provider = ProviderResourceIT.createEntity(em);
            em.persist(provider);
            em.flush();
        } else {
            provider = TestUtil.findAll(em, Provider.class).get(0);
        }
        medicalService.setProvider(provider);
        return medicalService;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalService createUpdatedEntity(EntityManager em) {
        MedicalService medicalService = new MedicalService()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .grossPrice(UPDATED_GROSS_PRICE)
            .unit(UPDATED_UNIT)
            .statisticalCode(UPDATED_STATISTICAL_CODE)
            .taxRate(UPDATED_TAX_RATE);
        // Add required entity
        Praxis praxis;
        if (TestUtil.findAll(em, Praxis.class).isEmpty()) {
            praxis = PraxisResourceIT.createUpdatedEntity(em);
            em.persist(praxis);
            em.flush();
        } else {
            praxis = TestUtil.findAll(em, Praxis.class).get(0);
        }
        medicalService.setPraxis(praxis);
        // Add required entity
        Provider provider;
        if (TestUtil.findAll(em, Provider.class).isEmpty()) {
            provider = ProviderResourceIT.createUpdatedEntity(em);
            em.persist(provider);
            em.flush();
        } else {
            provider = TestUtil.findAll(em, Provider.class).get(0);
        }
        medicalService.setProvider(provider);
        return medicalService;
    }

    @BeforeEach
    public void initTest() {
        medicalService = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedicalService() throws Exception {
        int databaseSizeBeforeCreate = medicalServiceRepository.findAll().size();
        // Create the MedicalService
        MedicalServiceDTO medicalServiceDTO = medicalServiceMapper.toDto(medicalService);
        restMedicalServiceMockMvc.perform(post("/api/medical-services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalServiceDTO)))
            .andExpect(status().isCreated());

        // Validate the MedicalService in the database
        List<MedicalService> medicalServiceList = medicalServiceRepository.findAll();
        assertThat(medicalServiceList).hasSize(databaseSizeBeforeCreate + 1);
        MedicalService testMedicalService = medicalServiceList.get(medicalServiceList.size() - 1);
        assertThat(testMedicalService.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testMedicalService.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMedicalService.getGrossPrice()).isEqualTo(DEFAULT_GROSS_PRICE);
        assertThat(testMedicalService.getUnit()).isEqualTo(DEFAULT_UNIT);
        assertThat(testMedicalService.getStatisticalCode()).isEqualTo(DEFAULT_STATISTICAL_CODE);
        assertThat(testMedicalService.getTaxRate()).isEqualTo(DEFAULT_TAX_RATE);
    }

    @Test
    @Transactional
    public void createMedicalServiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medicalServiceRepository.findAll().size();

        // Create the MedicalService with an existing ID
        medicalService.setId(1L);
        MedicalServiceDTO medicalServiceDTO = medicalServiceMapper.toDto(medicalService);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedicalServiceMockMvc.perform(post("/api/medical-services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalServiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalService in the database
        List<MedicalService> medicalServiceList = medicalServiceRepository.findAll();
        assertThat(medicalServiceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicalServiceRepository.findAll().size();
        // set the field null
        medicalService.setCode(null);

        // Create the MedicalService, which fails.
        MedicalServiceDTO medicalServiceDTO = medicalServiceMapper.toDto(medicalService);


        restMedicalServiceMockMvc.perform(post("/api/medical-services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalServiceDTO)))
            .andExpect(status().isBadRequest());

        List<MedicalService> medicalServiceList = medicalServiceRepository.findAll();
        assertThat(medicalServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicalServiceRepository.findAll().size();
        // set the field null
        medicalService.setName(null);

        // Create the MedicalService, which fails.
        MedicalServiceDTO medicalServiceDTO = medicalServiceMapper.toDto(medicalService);


        restMedicalServiceMockMvc.perform(post("/api/medical-services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalServiceDTO)))
            .andExpect(status().isBadRequest());

        List<MedicalService> medicalServiceList = medicalServiceRepository.findAll();
        assertThat(medicalServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMedicalServices() throws Exception {
        // Initialize the database
        medicalServiceRepository.saveAndFlush(medicalService);

        // Get all the medicalServiceList
        restMedicalServiceMockMvc.perform(get("/api/medical-services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medicalService.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].grossPrice").value(hasItem(DEFAULT_GROSS_PRICE)))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT.toString())))
            .andExpect(jsonPath("$.[*].statisticalCode").value(hasItem(DEFAULT_STATISTICAL_CODE)))
            .andExpect(jsonPath("$.[*].taxRate").value(hasItem(DEFAULT_TAX_RATE.toString())));
    }
    
    @Test
    @Transactional
    public void getMedicalService() throws Exception {
        // Initialize the database
        medicalServiceRepository.saveAndFlush(medicalService);

        // Get the medicalService
        restMedicalServiceMockMvc.perform(get("/api/medical-services/{id}", medicalService.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(medicalService.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.grossPrice").value(DEFAULT_GROSS_PRICE))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT.toString()))
            .andExpect(jsonPath("$.statisticalCode").value(DEFAULT_STATISTICAL_CODE))
            .andExpect(jsonPath("$.taxRate").value(DEFAULT_TAX_RATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingMedicalService() throws Exception {
        // Get the medicalService
        restMedicalServiceMockMvc.perform(get("/api/medical-services/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedicalService() throws Exception {
        // Initialize the database
        medicalServiceRepository.saveAndFlush(medicalService);

        int databaseSizeBeforeUpdate = medicalServiceRepository.findAll().size();

        // Update the medicalService
        MedicalService updatedMedicalService = medicalServiceRepository.findById(medicalService.getId()).get();
        // Disconnect from session so that the updates on updatedMedicalService are not directly saved in db
        em.detach(updatedMedicalService);
        updatedMedicalService
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .grossPrice(UPDATED_GROSS_PRICE)
            .unit(UPDATED_UNIT)
            .statisticalCode(UPDATED_STATISTICAL_CODE)
            .taxRate(UPDATED_TAX_RATE);
        MedicalServiceDTO medicalServiceDTO = medicalServiceMapper.toDto(updatedMedicalService);

        restMedicalServiceMockMvc.perform(put("/api/medical-services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalServiceDTO)))
            .andExpect(status().isOk());

        // Validate the MedicalService in the database
        List<MedicalService> medicalServiceList = medicalServiceRepository.findAll();
        assertThat(medicalServiceList).hasSize(databaseSizeBeforeUpdate);
        MedicalService testMedicalService = medicalServiceList.get(medicalServiceList.size() - 1);
        assertThat(testMedicalService.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testMedicalService.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMedicalService.getGrossPrice()).isEqualTo(UPDATED_GROSS_PRICE);
        assertThat(testMedicalService.getUnit()).isEqualTo(UPDATED_UNIT);
        assertThat(testMedicalService.getStatisticalCode()).isEqualTo(UPDATED_STATISTICAL_CODE);
        assertThat(testMedicalService.getTaxRate()).isEqualTo(UPDATED_TAX_RATE);
    }

    @Test
    @Transactional
    public void updateNonExistingMedicalService() throws Exception {
        int databaseSizeBeforeUpdate = medicalServiceRepository.findAll().size();

        // Create the MedicalService
        MedicalServiceDTO medicalServiceDTO = medicalServiceMapper.toDto(medicalService);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMedicalServiceMockMvc.perform(put("/api/medical-services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalServiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalService in the database
        List<MedicalService> medicalServiceList = medicalServiceRepository.findAll();
        assertThat(medicalServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMedicalService() throws Exception {
        // Initialize the database
        medicalServiceRepository.saveAndFlush(medicalService);

        int databaseSizeBeforeDelete = medicalServiceRepository.findAll().size();

        // Delete the medicalService
        restMedicalServiceMockMvc.perform(delete("/api/medical-services/{id}", medicalService.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MedicalService> medicalServiceList = medicalServiceRepository.findAll();
        assertThat(medicalServiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
