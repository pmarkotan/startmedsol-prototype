package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.PphPuphaVersion;
import hu.paninform.startmedsol.repository.PphPuphaVersionRepository;

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

import hu.paninform.startmedsol.domain.enumeration.PuphaDataType;
import hu.paninform.startmedsol.domain.enumeration.PuphaDataStatus;
/**
 * Integration tests for the {@link PphPuphaVersionResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PphPuphaVersionResourceIT {

    private static final Integer DEFAULT_EXTERNAL_ID = 1;
    private static final Integer UPDATED_EXTERNAL_ID = 2;

    private static final Instant DEFAULT_VALID_FROM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_VALID_FROM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LOAD_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LOAD_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_FILE_VERSION = 1;
    private static final Integer UPDATED_FILE_VERSION = 2;

    private static final Instant DEFAULT_MODIFICATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFICATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final PuphaDataType DEFAULT_PUPHA_DATA_TYPE = PuphaDataType.MEDICINE;
    private static final PuphaDataType UPDATED_PUPHA_DATA_TYPE = PuphaDataType.MEDICAL_DEVICES;

    private static final PuphaDataStatus DEFAULT_PUPHA_DATA_STATUS = PuphaDataStatus.UNDER_FIXING;
    private static final PuphaDataStatus UPDATED_PUPHA_DATA_STATUS = PuphaDataStatus.CLOSED;

    private static final Boolean DEFAULT_ACTIVE_PUPHA_DATA = false;
    private static final Boolean UPDATED_ACTIVE_PUPHA_DATA = true;

    @Autowired
    private PphPuphaVersionRepository pphPuphaVersionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPphPuphaVersionMockMvc;

    private PphPuphaVersion pphPuphaVersion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphPuphaVersion createEntity(EntityManager em) {
        PphPuphaVersion pphPuphaVersion = new PphPuphaVersion()
            .externalId(DEFAULT_EXTERNAL_ID)
            .validFrom(DEFAULT_VALID_FROM)
            .loadDate(DEFAULT_LOAD_DATE)
            .fileVersion(DEFAULT_FILE_VERSION)
            .modificationDate(DEFAULT_MODIFICATION_DATE)
            .puphaDataType(DEFAULT_PUPHA_DATA_TYPE)
            .puphaDataStatus(DEFAULT_PUPHA_DATA_STATUS)
            .activePuphaData(DEFAULT_ACTIVE_PUPHA_DATA);
        return pphPuphaVersion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphPuphaVersion createUpdatedEntity(EntityManager em) {
        PphPuphaVersion pphPuphaVersion = new PphPuphaVersion()
            .externalId(UPDATED_EXTERNAL_ID)
            .validFrom(UPDATED_VALID_FROM)
            .loadDate(UPDATED_LOAD_DATE)
            .fileVersion(UPDATED_FILE_VERSION)
            .modificationDate(UPDATED_MODIFICATION_DATE)
            .puphaDataType(UPDATED_PUPHA_DATA_TYPE)
            .puphaDataStatus(UPDATED_PUPHA_DATA_STATUS)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);
        return pphPuphaVersion;
    }

    @BeforeEach
    public void initTest() {
        pphPuphaVersion = createEntity(em);
    }

    @Test
    @Transactional
    public void createPphPuphaVersion() throws Exception {
        int databaseSizeBeforeCreate = pphPuphaVersionRepository.findAll().size();
        // Create the PphPuphaVersion
        restPphPuphaVersionMockMvc.perform(post("/api/pph-pupha-versions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphPuphaVersion)))
            .andExpect(status().isCreated());

        // Validate the PphPuphaVersion in the database
        List<PphPuphaVersion> pphPuphaVersionList = pphPuphaVersionRepository.findAll();
        assertThat(pphPuphaVersionList).hasSize(databaseSizeBeforeCreate + 1);
        PphPuphaVersion testPphPuphaVersion = pphPuphaVersionList.get(pphPuphaVersionList.size() - 1);
        assertThat(testPphPuphaVersion.getExternalId()).isEqualTo(DEFAULT_EXTERNAL_ID);
        assertThat(testPphPuphaVersion.getValidFrom()).isEqualTo(DEFAULT_VALID_FROM);
        assertThat(testPphPuphaVersion.getLoadDate()).isEqualTo(DEFAULT_LOAD_DATE);
        assertThat(testPphPuphaVersion.getFileVersion()).isEqualTo(DEFAULT_FILE_VERSION);
        assertThat(testPphPuphaVersion.getModificationDate()).isEqualTo(DEFAULT_MODIFICATION_DATE);
        assertThat(testPphPuphaVersion.getPuphaDataType()).isEqualTo(DEFAULT_PUPHA_DATA_TYPE);
        assertThat(testPphPuphaVersion.getPuphaDataStatus()).isEqualTo(DEFAULT_PUPHA_DATA_STATUS);
        assertThat(testPphPuphaVersion.isActivePuphaData()).isEqualTo(DEFAULT_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void createPphPuphaVersionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pphPuphaVersionRepository.findAll().size();

        // Create the PphPuphaVersion with an existing ID
        pphPuphaVersion.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPphPuphaVersionMockMvc.perform(post("/api/pph-pupha-versions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphPuphaVersion)))
            .andExpect(status().isBadRequest());

        // Validate the PphPuphaVersion in the database
        List<PphPuphaVersion> pphPuphaVersionList = pphPuphaVersionRepository.findAll();
        assertThat(pphPuphaVersionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkActivePuphaDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = pphPuphaVersionRepository.findAll().size();
        // set the field null
        pphPuphaVersion.setActivePuphaData(null);

        // Create the PphPuphaVersion, which fails.


        restPphPuphaVersionMockMvc.perform(post("/api/pph-pupha-versions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphPuphaVersion)))
            .andExpect(status().isBadRequest());

        List<PphPuphaVersion> pphPuphaVersionList = pphPuphaVersionRepository.findAll();
        assertThat(pphPuphaVersionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPphPuphaVersions() throws Exception {
        // Initialize the database
        pphPuphaVersionRepository.saveAndFlush(pphPuphaVersion);

        // Get all the pphPuphaVersionList
        restPphPuphaVersionMockMvc.perform(get("/api/pph-pupha-versions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pphPuphaVersion.getId().intValue())))
            .andExpect(jsonPath("$.[*].externalId").value(hasItem(DEFAULT_EXTERNAL_ID)))
            .andExpect(jsonPath("$.[*].validFrom").value(hasItem(DEFAULT_VALID_FROM.toString())))
            .andExpect(jsonPath("$.[*].loadDate").value(hasItem(DEFAULT_LOAD_DATE.toString())))
            .andExpect(jsonPath("$.[*].fileVersion").value(hasItem(DEFAULT_FILE_VERSION)))
            .andExpect(jsonPath("$.[*].modificationDate").value(hasItem(DEFAULT_MODIFICATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].puphaDataType").value(hasItem(DEFAULT_PUPHA_DATA_TYPE.toString())))
            .andExpect(jsonPath("$.[*].puphaDataStatus").value(hasItem(DEFAULT_PUPHA_DATA_STATUS.toString())))
            .andExpect(jsonPath("$.[*].activePuphaData").value(hasItem(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPphPuphaVersion() throws Exception {
        // Initialize the database
        pphPuphaVersionRepository.saveAndFlush(pphPuphaVersion);

        // Get the pphPuphaVersion
        restPphPuphaVersionMockMvc.perform(get("/api/pph-pupha-versions/{id}", pphPuphaVersion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pphPuphaVersion.getId().intValue()))
            .andExpect(jsonPath("$.externalId").value(DEFAULT_EXTERNAL_ID))
            .andExpect(jsonPath("$.validFrom").value(DEFAULT_VALID_FROM.toString()))
            .andExpect(jsonPath("$.loadDate").value(DEFAULT_LOAD_DATE.toString()))
            .andExpect(jsonPath("$.fileVersion").value(DEFAULT_FILE_VERSION))
            .andExpect(jsonPath("$.modificationDate").value(DEFAULT_MODIFICATION_DATE.toString()))
            .andExpect(jsonPath("$.puphaDataType").value(DEFAULT_PUPHA_DATA_TYPE.toString()))
            .andExpect(jsonPath("$.puphaDataStatus").value(DEFAULT_PUPHA_DATA_STATUS.toString()))
            .andExpect(jsonPath("$.activePuphaData").value(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPphPuphaVersion() throws Exception {
        // Get the pphPuphaVersion
        restPphPuphaVersionMockMvc.perform(get("/api/pph-pupha-versions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePphPuphaVersion() throws Exception {
        // Initialize the database
        pphPuphaVersionRepository.saveAndFlush(pphPuphaVersion);

        int databaseSizeBeforeUpdate = pphPuphaVersionRepository.findAll().size();

        // Update the pphPuphaVersion
        PphPuphaVersion updatedPphPuphaVersion = pphPuphaVersionRepository.findById(pphPuphaVersion.getId()).get();
        // Disconnect from session so that the updates on updatedPphPuphaVersion are not directly saved in db
        em.detach(updatedPphPuphaVersion);
        updatedPphPuphaVersion
            .externalId(UPDATED_EXTERNAL_ID)
            .validFrom(UPDATED_VALID_FROM)
            .loadDate(UPDATED_LOAD_DATE)
            .fileVersion(UPDATED_FILE_VERSION)
            .modificationDate(UPDATED_MODIFICATION_DATE)
            .puphaDataType(UPDATED_PUPHA_DATA_TYPE)
            .puphaDataStatus(UPDATED_PUPHA_DATA_STATUS)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);

        restPphPuphaVersionMockMvc.perform(put("/api/pph-pupha-versions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPphPuphaVersion)))
            .andExpect(status().isOk());

        // Validate the PphPuphaVersion in the database
        List<PphPuphaVersion> pphPuphaVersionList = pphPuphaVersionRepository.findAll();
        assertThat(pphPuphaVersionList).hasSize(databaseSizeBeforeUpdate);
        PphPuphaVersion testPphPuphaVersion = pphPuphaVersionList.get(pphPuphaVersionList.size() - 1);
        assertThat(testPphPuphaVersion.getExternalId()).isEqualTo(UPDATED_EXTERNAL_ID);
        assertThat(testPphPuphaVersion.getValidFrom()).isEqualTo(UPDATED_VALID_FROM);
        assertThat(testPphPuphaVersion.getLoadDate()).isEqualTo(UPDATED_LOAD_DATE);
        assertThat(testPphPuphaVersion.getFileVersion()).isEqualTo(UPDATED_FILE_VERSION);
        assertThat(testPphPuphaVersion.getModificationDate()).isEqualTo(UPDATED_MODIFICATION_DATE);
        assertThat(testPphPuphaVersion.getPuphaDataType()).isEqualTo(UPDATED_PUPHA_DATA_TYPE);
        assertThat(testPphPuphaVersion.getPuphaDataStatus()).isEqualTo(UPDATED_PUPHA_DATA_STATUS);
        assertThat(testPphPuphaVersion.isActivePuphaData()).isEqualTo(UPDATED_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingPphPuphaVersion() throws Exception {
        int databaseSizeBeforeUpdate = pphPuphaVersionRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPphPuphaVersionMockMvc.perform(put("/api/pph-pupha-versions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphPuphaVersion)))
            .andExpect(status().isBadRequest());

        // Validate the PphPuphaVersion in the database
        List<PphPuphaVersion> pphPuphaVersionList = pphPuphaVersionRepository.findAll();
        assertThat(pphPuphaVersionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePphPuphaVersion() throws Exception {
        // Initialize the database
        pphPuphaVersionRepository.saveAndFlush(pphPuphaVersion);

        int databaseSizeBeforeDelete = pphPuphaVersionRepository.findAll().size();

        // Delete the pphPuphaVersion
        restPphPuphaVersionMockMvc.perform(delete("/api/pph-pupha-versions/{id}", pphPuphaVersion.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PphPuphaVersion> pphPuphaVersionList = pphPuphaVersionRepository.findAll();
        assertThat(pphPuphaVersionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
