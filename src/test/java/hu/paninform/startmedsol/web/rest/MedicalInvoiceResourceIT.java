package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.MedicalInvoice;
import hu.paninform.startmedsol.domain.MedicalCase;
import hu.paninform.startmedsol.repository.MedicalInvoiceRepository;
import hu.paninform.startmedsol.service.MedicalInvoiceService;
import hu.paninform.startmedsol.service.dto.MedicalInvoiceDTO;
import hu.paninform.startmedsol.service.mapper.MedicalInvoiceMapper;

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

import hu.paninform.startmedsol.domain.enumeration.MedicalInvoiceType;
/**
 * Integration tests for the {@link MedicalInvoiceResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MedicalInvoiceResourceIT {

    private static final Integer DEFAULT_ORDER_NUMBER = 1;
    private static final Integer UPDATED_ORDER_NUMBER = 2;

    private static final String DEFAULT_INVOICE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_INVOICE_NUMBER = "BBBBBBBBBB";

    private static final MedicalInvoiceType DEFAULT_TYPE = MedicalInvoiceType.NORMAL;
    private static final MedicalInvoiceType UPDATED_TYPE = MedicalInvoiceType.STORNO;

    private static final Integer DEFAULT_TOTAL = 1;
    private static final Integer UPDATED_TOTAL = 2;

    private static final String DEFAULT_CREATOR_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private MedicalInvoiceRepository medicalInvoiceRepository;

    @Autowired
    private MedicalInvoiceMapper medicalInvoiceMapper;

    @Autowired
    private MedicalInvoiceService medicalInvoiceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMedicalInvoiceMockMvc;

    private MedicalInvoice medicalInvoice;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalInvoice createEntity(EntityManager em) {
        MedicalInvoice medicalInvoice = new MedicalInvoice()
            .orderNumber(DEFAULT_ORDER_NUMBER)
            .invoiceNumber(DEFAULT_INVOICE_NUMBER)
            .type(DEFAULT_TYPE)
            .total(DEFAULT_TOTAL)
            .creatorUser(DEFAULT_CREATOR_USER)
            .createdAt(DEFAULT_CREATED_AT);
        // Add required entity
        MedicalCase medicalCase;
        if (TestUtil.findAll(em, MedicalCase.class).isEmpty()) {
            medicalCase = MedicalCaseResourceIT.createEntity(em);
            em.persist(medicalCase);
            em.flush();
        } else {
            medicalCase = TestUtil.findAll(em, MedicalCase.class).get(0);
        }
        medicalInvoice.setMedicalCase(medicalCase);
        return medicalInvoice;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalInvoice createUpdatedEntity(EntityManager em) {
        MedicalInvoice medicalInvoice = new MedicalInvoice()
            .orderNumber(UPDATED_ORDER_NUMBER)
            .invoiceNumber(UPDATED_INVOICE_NUMBER)
            .type(UPDATED_TYPE)
            .total(UPDATED_TOTAL)
            .creatorUser(UPDATED_CREATOR_USER)
            .createdAt(UPDATED_CREATED_AT);
        // Add required entity
        MedicalCase medicalCase;
        if (TestUtil.findAll(em, MedicalCase.class).isEmpty()) {
            medicalCase = MedicalCaseResourceIT.createUpdatedEntity(em);
            em.persist(medicalCase);
            em.flush();
        } else {
            medicalCase = TestUtil.findAll(em, MedicalCase.class).get(0);
        }
        medicalInvoice.setMedicalCase(medicalCase);
        return medicalInvoice;
    }

    @BeforeEach
    public void initTest() {
        medicalInvoice = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedicalInvoice() throws Exception {
        int databaseSizeBeforeCreate = medicalInvoiceRepository.findAll().size();
        // Create the MedicalInvoice
        MedicalInvoiceDTO medicalInvoiceDTO = medicalInvoiceMapper.toDto(medicalInvoice);
        restMedicalInvoiceMockMvc.perform(post("/api/medical-invoices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalInvoiceDTO)))
            .andExpect(status().isCreated());

        // Validate the MedicalInvoice in the database
        List<MedicalInvoice> medicalInvoiceList = medicalInvoiceRepository.findAll();
        assertThat(medicalInvoiceList).hasSize(databaseSizeBeforeCreate + 1);
        MedicalInvoice testMedicalInvoice = medicalInvoiceList.get(medicalInvoiceList.size() - 1);
        assertThat(testMedicalInvoice.getOrderNumber()).isEqualTo(DEFAULT_ORDER_NUMBER);
        assertThat(testMedicalInvoice.getInvoiceNumber()).isEqualTo(DEFAULT_INVOICE_NUMBER);
        assertThat(testMedicalInvoice.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testMedicalInvoice.getTotal()).isEqualTo(DEFAULT_TOTAL);
        assertThat(testMedicalInvoice.getCreatorUser()).isEqualTo(DEFAULT_CREATOR_USER);
        assertThat(testMedicalInvoice.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
    }

    @Test
    @Transactional
    public void createMedicalInvoiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medicalInvoiceRepository.findAll().size();

        // Create the MedicalInvoice with an existing ID
        medicalInvoice.setId(1L);
        MedicalInvoiceDTO medicalInvoiceDTO = medicalInvoiceMapper.toDto(medicalInvoice);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedicalInvoiceMockMvc.perform(post("/api/medical-invoices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalInvoiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalInvoice in the database
        List<MedicalInvoice> medicalInvoiceList = medicalInvoiceRepository.findAll();
        assertThat(medicalInvoiceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkOrderNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicalInvoiceRepository.findAll().size();
        // set the field null
        medicalInvoice.setOrderNumber(null);

        // Create the MedicalInvoice, which fails.
        MedicalInvoiceDTO medicalInvoiceDTO = medicalInvoiceMapper.toDto(medicalInvoice);


        restMedicalInvoiceMockMvc.perform(post("/api/medical-invoices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalInvoiceDTO)))
            .andExpect(status().isBadRequest());

        List<MedicalInvoice> medicalInvoiceList = medicalInvoiceRepository.findAll();
        assertThat(medicalInvoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInvoiceNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicalInvoiceRepository.findAll().size();
        // set the field null
        medicalInvoice.setInvoiceNumber(null);

        // Create the MedicalInvoice, which fails.
        MedicalInvoiceDTO medicalInvoiceDTO = medicalInvoiceMapper.toDto(medicalInvoice);


        restMedicalInvoiceMockMvc.perform(post("/api/medical-invoices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalInvoiceDTO)))
            .andExpect(status().isBadRequest());

        List<MedicalInvoice> medicalInvoiceList = medicalInvoiceRepository.findAll();
        assertThat(medicalInvoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicalInvoiceRepository.findAll().size();
        // set the field null
        medicalInvoice.setType(null);

        // Create the MedicalInvoice, which fails.
        MedicalInvoiceDTO medicalInvoiceDTO = medicalInvoiceMapper.toDto(medicalInvoice);


        restMedicalInvoiceMockMvc.perform(post("/api/medical-invoices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalInvoiceDTO)))
            .andExpect(status().isBadRequest());

        List<MedicalInvoice> medicalInvoiceList = medicalInvoiceRepository.findAll();
        assertThat(medicalInvoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicalInvoiceRepository.findAll().size();
        // set the field null
        medicalInvoice.setTotal(null);

        // Create the MedicalInvoice, which fails.
        MedicalInvoiceDTO medicalInvoiceDTO = medicalInvoiceMapper.toDto(medicalInvoice);


        restMedicalInvoiceMockMvc.perform(post("/api/medical-invoices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalInvoiceDTO)))
            .andExpect(status().isBadRequest());

        List<MedicalInvoice> medicalInvoiceList = medicalInvoiceRepository.findAll();
        assertThat(medicalInvoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatorUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicalInvoiceRepository.findAll().size();
        // set the field null
        medicalInvoice.setCreatorUser(null);

        // Create the MedicalInvoice, which fails.
        MedicalInvoiceDTO medicalInvoiceDTO = medicalInvoiceMapper.toDto(medicalInvoice);


        restMedicalInvoiceMockMvc.perform(post("/api/medical-invoices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalInvoiceDTO)))
            .andExpect(status().isBadRequest());

        List<MedicalInvoice> medicalInvoiceList = medicalInvoiceRepository.findAll();
        assertThat(medicalInvoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicalInvoiceRepository.findAll().size();
        // set the field null
        medicalInvoice.setCreatedAt(null);

        // Create the MedicalInvoice, which fails.
        MedicalInvoiceDTO medicalInvoiceDTO = medicalInvoiceMapper.toDto(medicalInvoice);


        restMedicalInvoiceMockMvc.perform(post("/api/medical-invoices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalInvoiceDTO)))
            .andExpect(status().isBadRequest());

        List<MedicalInvoice> medicalInvoiceList = medicalInvoiceRepository.findAll();
        assertThat(medicalInvoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMedicalInvoices() throws Exception {
        // Initialize the database
        medicalInvoiceRepository.saveAndFlush(medicalInvoice);

        // Get all the medicalInvoiceList
        restMedicalInvoiceMockMvc.perform(get("/api/medical-invoices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medicalInvoice.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderNumber").value(hasItem(DEFAULT_ORDER_NUMBER)))
            .andExpect(jsonPath("$.[*].invoiceNumber").value(hasItem(DEFAULT_INVOICE_NUMBER)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL)))
            .andExpect(jsonPath("$.[*].creatorUser").value(hasItem(DEFAULT_CREATOR_USER)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())));
    }
    
    @Test
    @Transactional
    public void getMedicalInvoice() throws Exception {
        // Initialize the database
        medicalInvoiceRepository.saveAndFlush(medicalInvoice);

        // Get the medicalInvoice
        restMedicalInvoiceMockMvc.perform(get("/api/medical-invoices/{id}", medicalInvoice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(medicalInvoice.getId().intValue()))
            .andExpect(jsonPath("$.orderNumber").value(DEFAULT_ORDER_NUMBER))
            .andExpect(jsonPath("$.invoiceNumber").value(DEFAULT_INVOICE_NUMBER))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL))
            .andExpect(jsonPath("$.creatorUser").value(DEFAULT_CREATOR_USER))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingMedicalInvoice() throws Exception {
        // Get the medicalInvoice
        restMedicalInvoiceMockMvc.perform(get("/api/medical-invoices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedicalInvoice() throws Exception {
        // Initialize the database
        medicalInvoiceRepository.saveAndFlush(medicalInvoice);

        int databaseSizeBeforeUpdate = medicalInvoiceRepository.findAll().size();

        // Update the medicalInvoice
        MedicalInvoice updatedMedicalInvoice = medicalInvoiceRepository.findById(medicalInvoice.getId()).get();
        // Disconnect from session so that the updates on updatedMedicalInvoice are not directly saved in db
        em.detach(updatedMedicalInvoice);
        updatedMedicalInvoice
            .orderNumber(UPDATED_ORDER_NUMBER)
            .invoiceNumber(UPDATED_INVOICE_NUMBER)
            .type(UPDATED_TYPE)
            .total(UPDATED_TOTAL)
            .creatorUser(UPDATED_CREATOR_USER)
            .createdAt(UPDATED_CREATED_AT);
        MedicalInvoiceDTO medicalInvoiceDTO = medicalInvoiceMapper.toDto(updatedMedicalInvoice);

        restMedicalInvoiceMockMvc.perform(put("/api/medical-invoices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalInvoiceDTO)))
            .andExpect(status().isOk());

        // Validate the MedicalInvoice in the database
        List<MedicalInvoice> medicalInvoiceList = medicalInvoiceRepository.findAll();
        assertThat(medicalInvoiceList).hasSize(databaseSizeBeforeUpdate);
        MedicalInvoice testMedicalInvoice = medicalInvoiceList.get(medicalInvoiceList.size() - 1);
        assertThat(testMedicalInvoice.getOrderNumber()).isEqualTo(UPDATED_ORDER_NUMBER);
        assertThat(testMedicalInvoice.getInvoiceNumber()).isEqualTo(UPDATED_INVOICE_NUMBER);
        assertThat(testMedicalInvoice.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testMedicalInvoice.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testMedicalInvoice.getCreatorUser()).isEqualTo(UPDATED_CREATOR_USER);
        assertThat(testMedicalInvoice.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingMedicalInvoice() throws Exception {
        int databaseSizeBeforeUpdate = medicalInvoiceRepository.findAll().size();

        // Create the MedicalInvoice
        MedicalInvoiceDTO medicalInvoiceDTO = medicalInvoiceMapper.toDto(medicalInvoice);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMedicalInvoiceMockMvc.perform(put("/api/medical-invoices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalInvoiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalInvoice in the database
        List<MedicalInvoice> medicalInvoiceList = medicalInvoiceRepository.findAll();
        assertThat(medicalInvoiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMedicalInvoice() throws Exception {
        // Initialize the database
        medicalInvoiceRepository.saveAndFlush(medicalInvoice);

        int databaseSizeBeforeDelete = medicalInvoiceRepository.findAll().size();

        // Delete the medicalInvoice
        restMedicalInvoiceMockMvc.perform(delete("/api/medical-invoices/{id}", medicalInvoice.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MedicalInvoice> medicalInvoiceList = medicalInvoiceRepository.findAll();
        assertThat(medicalInvoiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
