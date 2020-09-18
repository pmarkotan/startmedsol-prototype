package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.EhrDocument;
import hu.paninform.startmedsol.repository.EhrDocumentRepository;
import hu.paninform.startmedsol.service.EhrDocumentService;
import hu.paninform.startmedsol.service.dto.EhrDocumentDTO;
import hu.paninform.startmedsol.service.mapper.EhrDocumentMapper;

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
 * Integration tests for the {@link EhrDocumentResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EhrDocumentResourceIT {

    private static final String DEFAULT_EESZT_ID = "AAAAAAAAAA";
    private static final String UPDATED_EESZT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DOCUMENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_ID = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_DOCUMENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DOCTOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOCTOR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INSTITUTION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSTITUTION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PRAXIS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRAXIS_NAME = "BBBBBBBBBB";

    @Autowired
    private EhrDocumentRepository ehrDocumentRepository;

    @Autowired
    private EhrDocumentMapper ehrDocumentMapper;

    @Autowired
    private EhrDocumentService ehrDocumentService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEhrDocumentMockMvc;

    private EhrDocument ehrDocument;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EhrDocument createEntity(EntityManager em) {
        EhrDocument ehrDocument = new EhrDocument()
            .eesztId(DEFAULT_EESZT_ID)
            .documentId(DEFAULT_DOCUMENT_ID)
            .created(DEFAULT_CREATED)
            .documentType(DEFAULT_DOCUMENT_TYPE)
            .doctorName(DEFAULT_DOCTOR_NAME)
            .institutionName(DEFAULT_INSTITUTION_NAME)
            .praxisName(DEFAULT_PRAXIS_NAME);
        return ehrDocument;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EhrDocument createUpdatedEntity(EntityManager em) {
        EhrDocument ehrDocument = new EhrDocument()
            .eesztId(UPDATED_EESZT_ID)
            .documentId(UPDATED_DOCUMENT_ID)
            .created(UPDATED_CREATED)
            .documentType(UPDATED_DOCUMENT_TYPE)
            .doctorName(UPDATED_DOCTOR_NAME)
            .institutionName(UPDATED_INSTITUTION_NAME)
            .praxisName(UPDATED_PRAXIS_NAME);
        return ehrDocument;
    }

    @BeforeEach
    public void initTest() {
        ehrDocument = createEntity(em);
    }

    @Test
    @Transactional
    public void createEhrDocument() throws Exception {
        int databaseSizeBeforeCreate = ehrDocumentRepository.findAll().size();
        // Create the EhrDocument
        EhrDocumentDTO ehrDocumentDTO = ehrDocumentMapper.toDto(ehrDocument);
        restEhrDocumentMockMvc.perform(post("/api/ehr-documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ehrDocumentDTO)))
            .andExpect(status().isCreated());

        // Validate the EhrDocument in the database
        List<EhrDocument> ehrDocumentList = ehrDocumentRepository.findAll();
        assertThat(ehrDocumentList).hasSize(databaseSizeBeforeCreate + 1);
        EhrDocument testEhrDocument = ehrDocumentList.get(ehrDocumentList.size() - 1);
        assertThat(testEhrDocument.getEesztId()).isEqualTo(DEFAULT_EESZT_ID);
        assertThat(testEhrDocument.getDocumentId()).isEqualTo(DEFAULT_DOCUMENT_ID);
        assertThat(testEhrDocument.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testEhrDocument.getDocumentType()).isEqualTo(DEFAULT_DOCUMENT_TYPE);
        assertThat(testEhrDocument.getDoctorName()).isEqualTo(DEFAULT_DOCTOR_NAME);
        assertThat(testEhrDocument.getInstitutionName()).isEqualTo(DEFAULT_INSTITUTION_NAME);
        assertThat(testEhrDocument.getPraxisName()).isEqualTo(DEFAULT_PRAXIS_NAME);
    }

    @Test
    @Transactional
    public void createEhrDocumentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ehrDocumentRepository.findAll().size();

        // Create the EhrDocument with an existing ID
        ehrDocument.setId(1L);
        EhrDocumentDTO ehrDocumentDTO = ehrDocumentMapper.toDto(ehrDocument);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEhrDocumentMockMvc.perform(post("/api/ehr-documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ehrDocumentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EhrDocument in the database
        List<EhrDocument> ehrDocumentList = ehrDocumentRepository.findAll();
        assertThat(ehrDocumentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEhrDocuments() throws Exception {
        // Initialize the database
        ehrDocumentRepository.saveAndFlush(ehrDocument);

        // Get all the ehrDocumentList
        restEhrDocumentMockMvc.perform(get("/api/ehr-documents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ehrDocument.getId().intValue())))
            .andExpect(jsonPath("$.[*].eesztId").value(hasItem(DEFAULT_EESZT_ID)))
            .andExpect(jsonPath("$.[*].documentId").value(hasItem(DEFAULT_DOCUMENT_ID)))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())))
            .andExpect(jsonPath("$.[*].documentType").value(hasItem(DEFAULT_DOCUMENT_TYPE)))
            .andExpect(jsonPath("$.[*].doctorName").value(hasItem(DEFAULT_DOCTOR_NAME)))
            .andExpect(jsonPath("$.[*].institutionName").value(hasItem(DEFAULT_INSTITUTION_NAME)))
            .andExpect(jsonPath("$.[*].praxisName").value(hasItem(DEFAULT_PRAXIS_NAME)));
    }
    
    @Test
    @Transactional
    public void getEhrDocument() throws Exception {
        // Initialize the database
        ehrDocumentRepository.saveAndFlush(ehrDocument);

        // Get the ehrDocument
        restEhrDocumentMockMvc.perform(get("/api/ehr-documents/{id}", ehrDocument.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ehrDocument.getId().intValue()))
            .andExpect(jsonPath("$.eesztId").value(DEFAULT_EESZT_ID))
            .andExpect(jsonPath("$.documentId").value(DEFAULT_DOCUMENT_ID))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()))
            .andExpect(jsonPath("$.documentType").value(DEFAULT_DOCUMENT_TYPE))
            .andExpect(jsonPath("$.doctorName").value(DEFAULT_DOCTOR_NAME))
            .andExpect(jsonPath("$.institutionName").value(DEFAULT_INSTITUTION_NAME))
            .andExpect(jsonPath("$.praxisName").value(DEFAULT_PRAXIS_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingEhrDocument() throws Exception {
        // Get the ehrDocument
        restEhrDocumentMockMvc.perform(get("/api/ehr-documents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEhrDocument() throws Exception {
        // Initialize the database
        ehrDocumentRepository.saveAndFlush(ehrDocument);

        int databaseSizeBeforeUpdate = ehrDocumentRepository.findAll().size();

        // Update the ehrDocument
        EhrDocument updatedEhrDocument = ehrDocumentRepository.findById(ehrDocument.getId()).get();
        // Disconnect from session so that the updates on updatedEhrDocument are not directly saved in db
        em.detach(updatedEhrDocument);
        updatedEhrDocument
            .eesztId(UPDATED_EESZT_ID)
            .documentId(UPDATED_DOCUMENT_ID)
            .created(UPDATED_CREATED)
            .documentType(UPDATED_DOCUMENT_TYPE)
            .doctorName(UPDATED_DOCTOR_NAME)
            .institutionName(UPDATED_INSTITUTION_NAME)
            .praxisName(UPDATED_PRAXIS_NAME);
        EhrDocumentDTO ehrDocumentDTO = ehrDocumentMapper.toDto(updatedEhrDocument);

        restEhrDocumentMockMvc.perform(put("/api/ehr-documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ehrDocumentDTO)))
            .andExpect(status().isOk());

        // Validate the EhrDocument in the database
        List<EhrDocument> ehrDocumentList = ehrDocumentRepository.findAll();
        assertThat(ehrDocumentList).hasSize(databaseSizeBeforeUpdate);
        EhrDocument testEhrDocument = ehrDocumentList.get(ehrDocumentList.size() - 1);
        assertThat(testEhrDocument.getEesztId()).isEqualTo(UPDATED_EESZT_ID);
        assertThat(testEhrDocument.getDocumentId()).isEqualTo(UPDATED_DOCUMENT_ID);
        assertThat(testEhrDocument.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testEhrDocument.getDocumentType()).isEqualTo(UPDATED_DOCUMENT_TYPE);
        assertThat(testEhrDocument.getDoctorName()).isEqualTo(UPDATED_DOCTOR_NAME);
        assertThat(testEhrDocument.getInstitutionName()).isEqualTo(UPDATED_INSTITUTION_NAME);
        assertThat(testEhrDocument.getPraxisName()).isEqualTo(UPDATED_PRAXIS_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingEhrDocument() throws Exception {
        int databaseSizeBeforeUpdate = ehrDocumentRepository.findAll().size();

        // Create the EhrDocument
        EhrDocumentDTO ehrDocumentDTO = ehrDocumentMapper.toDto(ehrDocument);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEhrDocumentMockMvc.perform(put("/api/ehr-documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ehrDocumentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EhrDocument in the database
        List<EhrDocument> ehrDocumentList = ehrDocumentRepository.findAll();
        assertThat(ehrDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEhrDocument() throws Exception {
        // Initialize the database
        ehrDocumentRepository.saveAndFlush(ehrDocument);

        int databaseSizeBeforeDelete = ehrDocumentRepository.findAll().size();

        // Delete the ehrDocument
        restEhrDocumentMockMvc.perform(delete("/api/ehr-documents/{id}", ehrDocument.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EhrDocument> ehrDocumentList = ehrDocumentRepository.findAll();
        assertThat(ehrDocumentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
