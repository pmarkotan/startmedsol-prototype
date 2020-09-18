package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.ErrorRecord;
import hu.paninform.startmedsol.repository.ErrorRecordRepository;
import hu.paninform.startmedsol.service.ErrorRecordService;
import hu.paninform.startmedsol.service.dto.ErrorRecordDTO;
import hu.paninform.startmedsol.service.mapper.ErrorRecordMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ErrorRecordResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ErrorRecordResourceIT {

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    @Autowired
    private ErrorRecordRepository errorRecordRepository;

    @Autowired
    private ErrorRecordMapper errorRecordMapper;

    @Autowired
    private ErrorRecordService errorRecordService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restErrorRecordMockMvc;

    private ErrorRecord errorRecord;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ErrorRecord createEntity(EntityManager em) {
        ErrorRecord errorRecord = new ErrorRecord()
            .createDate(DEFAULT_CREATE_DATE)
            .content(DEFAULT_CONTENT);
        return errorRecord;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ErrorRecord createUpdatedEntity(EntityManager em) {
        ErrorRecord errorRecord = new ErrorRecord()
            .createDate(UPDATED_CREATE_DATE)
            .content(UPDATED_CONTENT);
        return errorRecord;
    }

    @BeforeEach
    public void initTest() {
        errorRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createErrorRecord() throws Exception {
        int databaseSizeBeforeCreate = errorRecordRepository.findAll().size();
        // Create the ErrorRecord
        ErrorRecordDTO errorRecordDTO = errorRecordMapper.toDto(errorRecord);
        restErrorRecordMockMvc.perform(post("/api/error-records")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(errorRecordDTO)))
            .andExpect(status().isCreated());

        // Validate the ErrorRecord in the database
        List<ErrorRecord> errorRecordList = errorRecordRepository.findAll();
        assertThat(errorRecordList).hasSize(databaseSizeBeforeCreate + 1);
        ErrorRecord testErrorRecord = errorRecordList.get(errorRecordList.size() - 1);
        assertThat(testErrorRecord.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testErrorRecord.getContent()).isEqualTo(DEFAULT_CONTENT);
    }

    @Test
    @Transactional
    public void createErrorRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = errorRecordRepository.findAll().size();

        // Create the ErrorRecord with an existing ID
        errorRecord.setId(1L);
        ErrorRecordDTO errorRecordDTO = errorRecordMapper.toDto(errorRecord);

        // An entity with an existing ID cannot be created, so this API call must fail
        restErrorRecordMockMvc.perform(post("/api/error-records")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(errorRecordDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ErrorRecord in the database
        List<ErrorRecord> errorRecordList = errorRecordRepository.findAll();
        assertThat(errorRecordList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = errorRecordRepository.findAll().size();
        // set the field null
        errorRecord.setCreateDate(null);

        // Create the ErrorRecord, which fails.
        ErrorRecordDTO errorRecordDTO = errorRecordMapper.toDto(errorRecord);


        restErrorRecordMockMvc.perform(post("/api/error-records")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(errorRecordDTO)))
            .andExpect(status().isBadRequest());

        List<ErrorRecord> errorRecordList = errorRecordRepository.findAll();
        assertThat(errorRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllErrorRecords() throws Exception {
        // Initialize the database
        errorRecordRepository.saveAndFlush(errorRecord);

        // Get all the errorRecordList
        restErrorRecordMockMvc.perform(get("/api/error-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(errorRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())));
    }
    
    @Test
    @Transactional
    public void getErrorRecord() throws Exception {
        // Initialize the database
        errorRecordRepository.saveAndFlush(errorRecord);

        // Get the errorRecord
        restErrorRecordMockMvc.perform(get("/api/error-records/{id}", errorRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(errorRecord.getId().intValue()))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingErrorRecord() throws Exception {
        // Get the errorRecord
        restErrorRecordMockMvc.perform(get("/api/error-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateErrorRecord() throws Exception {
        // Initialize the database
        errorRecordRepository.saveAndFlush(errorRecord);

        int databaseSizeBeforeUpdate = errorRecordRepository.findAll().size();

        // Update the errorRecord
        ErrorRecord updatedErrorRecord = errorRecordRepository.findById(errorRecord.getId()).get();
        // Disconnect from session so that the updates on updatedErrorRecord are not directly saved in db
        em.detach(updatedErrorRecord);
        updatedErrorRecord
            .createDate(UPDATED_CREATE_DATE)
            .content(UPDATED_CONTENT);
        ErrorRecordDTO errorRecordDTO = errorRecordMapper.toDto(updatedErrorRecord);

        restErrorRecordMockMvc.perform(put("/api/error-records")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(errorRecordDTO)))
            .andExpect(status().isOk());

        // Validate the ErrorRecord in the database
        List<ErrorRecord> errorRecordList = errorRecordRepository.findAll();
        assertThat(errorRecordList).hasSize(databaseSizeBeforeUpdate);
        ErrorRecord testErrorRecord = errorRecordList.get(errorRecordList.size() - 1);
        assertThat(testErrorRecord.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testErrorRecord.getContent()).isEqualTo(UPDATED_CONTENT);
    }

    @Test
    @Transactional
    public void updateNonExistingErrorRecord() throws Exception {
        int databaseSizeBeforeUpdate = errorRecordRepository.findAll().size();

        // Create the ErrorRecord
        ErrorRecordDTO errorRecordDTO = errorRecordMapper.toDto(errorRecord);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restErrorRecordMockMvc.perform(put("/api/error-records")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(errorRecordDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ErrorRecord in the database
        List<ErrorRecord> errorRecordList = errorRecordRepository.findAll();
        assertThat(errorRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteErrorRecord() throws Exception {
        // Initialize the database
        errorRecordRepository.saveAndFlush(errorRecord);

        int databaseSizeBeforeDelete = errorRecordRepository.findAll().size();

        // Delete the errorRecord
        restErrorRecordMockMvc.perform(delete("/api/error-records/{id}", errorRecord.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ErrorRecord> errorRecordList = errorRecordRepository.findAll();
        assertThat(errorRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
