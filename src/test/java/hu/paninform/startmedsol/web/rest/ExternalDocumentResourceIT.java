package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.ExternalDocument;
import hu.paninform.startmedsol.repository.ExternalDocumentRepository;
import hu.paninform.startmedsol.service.ExternalDocumentService;
import hu.paninform.startmedsol.service.dto.ExternalDocumentDTO;
import hu.paninform.startmedsol.service.mapper.ExternalDocumentMapper;

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
 * Integration tests for the {@link ExternalDocumentResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ExternalDocumentResourceIT {

    private static final String DEFAULT_DOCUMENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPLOADED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPLOADED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ExternalDocumentRepository externalDocumentRepository;

    @Autowired
    private ExternalDocumentMapper externalDocumentMapper;

    @Autowired
    private ExternalDocumentService externalDocumentService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restExternalDocumentMockMvc;

    private ExternalDocument externalDocument;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExternalDocument createEntity(EntityManager em) {
        ExternalDocument externalDocument = new ExternalDocument()
            .documentId(DEFAULT_DOCUMENT_ID)
            .name(DEFAULT_NAME)
            .created(DEFAULT_CREATED)
            .uploaded(DEFAULT_UPLOADED);
        return externalDocument;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExternalDocument createUpdatedEntity(EntityManager em) {
        ExternalDocument externalDocument = new ExternalDocument()
            .documentId(UPDATED_DOCUMENT_ID)
            .name(UPDATED_NAME)
            .created(UPDATED_CREATED)
            .uploaded(UPDATED_UPLOADED);
        return externalDocument;
    }

    @BeforeEach
    public void initTest() {
        externalDocument = createEntity(em);
    }

    @Test
    @Transactional
    public void createExternalDocument() throws Exception {
        int databaseSizeBeforeCreate = externalDocumentRepository.findAll().size();
        // Create the ExternalDocument
        ExternalDocumentDTO externalDocumentDTO = externalDocumentMapper.toDto(externalDocument);
        restExternalDocumentMockMvc.perform(post("/api/external-documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(externalDocumentDTO)))
            .andExpect(status().isCreated());

        // Validate the ExternalDocument in the database
        List<ExternalDocument> externalDocumentList = externalDocumentRepository.findAll();
        assertThat(externalDocumentList).hasSize(databaseSizeBeforeCreate + 1);
        ExternalDocument testExternalDocument = externalDocumentList.get(externalDocumentList.size() - 1);
        assertThat(testExternalDocument.getDocumentId()).isEqualTo(DEFAULT_DOCUMENT_ID);
        assertThat(testExternalDocument.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testExternalDocument.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testExternalDocument.getUploaded()).isEqualTo(DEFAULT_UPLOADED);
    }

    @Test
    @Transactional
    public void createExternalDocumentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = externalDocumentRepository.findAll().size();

        // Create the ExternalDocument with an existing ID
        externalDocument.setId(1L);
        ExternalDocumentDTO externalDocumentDTO = externalDocumentMapper.toDto(externalDocument);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExternalDocumentMockMvc.perform(post("/api/external-documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(externalDocumentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ExternalDocument in the database
        List<ExternalDocument> externalDocumentList = externalDocumentRepository.findAll();
        assertThat(externalDocumentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDocumentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = externalDocumentRepository.findAll().size();
        // set the field null
        externalDocument.setDocumentId(null);

        // Create the ExternalDocument, which fails.
        ExternalDocumentDTO externalDocumentDTO = externalDocumentMapper.toDto(externalDocument);


        restExternalDocumentMockMvc.perform(post("/api/external-documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(externalDocumentDTO)))
            .andExpect(status().isBadRequest());

        List<ExternalDocument> externalDocumentList = externalDocumentRepository.findAll();
        assertThat(externalDocumentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = externalDocumentRepository.findAll().size();
        // set the field null
        externalDocument.setName(null);

        // Create the ExternalDocument, which fails.
        ExternalDocumentDTO externalDocumentDTO = externalDocumentMapper.toDto(externalDocument);


        restExternalDocumentMockMvc.perform(post("/api/external-documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(externalDocumentDTO)))
            .andExpect(status().isBadRequest());

        List<ExternalDocument> externalDocumentList = externalDocumentRepository.findAll();
        assertThat(externalDocumentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllExternalDocuments() throws Exception {
        // Initialize the database
        externalDocumentRepository.saveAndFlush(externalDocument);

        // Get all the externalDocumentList
        restExternalDocumentMockMvc.perform(get("/api/external-documents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(externalDocument.getId().intValue())))
            .andExpect(jsonPath("$.[*].documentId").value(hasItem(DEFAULT_DOCUMENT_ID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())))
            .andExpect(jsonPath("$.[*].uploaded").value(hasItem(DEFAULT_UPLOADED.toString())));
    }
    
    @Test
    @Transactional
    public void getExternalDocument() throws Exception {
        // Initialize the database
        externalDocumentRepository.saveAndFlush(externalDocument);

        // Get the externalDocument
        restExternalDocumentMockMvc.perform(get("/api/external-documents/{id}", externalDocument.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(externalDocument.getId().intValue()))
            .andExpect(jsonPath("$.documentId").value(DEFAULT_DOCUMENT_ID))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()))
            .andExpect(jsonPath("$.uploaded").value(DEFAULT_UPLOADED.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingExternalDocument() throws Exception {
        // Get the externalDocument
        restExternalDocumentMockMvc.perform(get("/api/external-documents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExternalDocument() throws Exception {
        // Initialize the database
        externalDocumentRepository.saveAndFlush(externalDocument);

        int databaseSizeBeforeUpdate = externalDocumentRepository.findAll().size();

        // Update the externalDocument
        ExternalDocument updatedExternalDocument = externalDocumentRepository.findById(externalDocument.getId()).get();
        // Disconnect from session so that the updates on updatedExternalDocument are not directly saved in db
        em.detach(updatedExternalDocument);
        updatedExternalDocument
            .documentId(UPDATED_DOCUMENT_ID)
            .name(UPDATED_NAME)
            .created(UPDATED_CREATED)
            .uploaded(UPDATED_UPLOADED);
        ExternalDocumentDTO externalDocumentDTO = externalDocumentMapper.toDto(updatedExternalDocument);

        restExternalDocumentMockMvc.perform(put("/api/external-documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(externalDocumentDTO)))
            .andExpect(status().isOk());

        // Validate the ExternalDocument in the database
        List<ExternalDocument> externalDocumentList = externalDocumentRepository.findAll();
        assertThat(externalDocumentList).hasSize(databaseSizeBeforeUpdate);
        ExternalDocument testExternalDocument = externalDocumentList.get(externalDocumentList.size() - 1);
        assertThat(testExternalDocument.getDocumentId()).isEqualTo(UPDATED_DOCUMENT_ID);
        assertThat(testExternalDocument.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testExternalDocument.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testExternalDocument.getUploaded()).isEqualTo(UPDATED_UPLOADED);
    }

    @Test
    @Transactional
    public void updateNonExistingExternalDocument() throws Exception {
        int databaseSizeBeforeUpdate = externalDocumentRepository.findAll().size();

        // Create the ExternalDocument
        ExternalDocumentDTO externalDocumentDTO = externalDocumentMapper.toDto(externalDocument);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExternalDocumentMockMvc.perform(put("/api/external-documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(externalDocumentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ExternalDocument in the database
        List<ExternalDocument> externalDocumentList = externalDocumentRepository.findAll();
        assertThat(externalDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExternalDocument() throws Exception {
        // Initialize the database
        externalDocumentRepository.saveAndFlush(externalDocument);

        int databaseSizeBeforeDelete = externalDocumentRepository.findAll().size();

        // Delete the externalDocument
        restExternalDocumentMockMvc.perform(delete("/api/external-documents/{id}", externalDocument.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ExternalDocument> externalDocumentList = externalDocumentRepository.findAll();
        assertThat(externalDocumentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
