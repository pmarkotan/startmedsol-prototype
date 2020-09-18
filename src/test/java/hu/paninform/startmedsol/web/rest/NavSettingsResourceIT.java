package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.NavSettings;
import hu.paninform.startmedsol.repository.NavSettingsRepository;
import hu.paninform.startmedsol.service.NavSettingsService;
import hu.paninform.startmedsol.service.dto.NavSettingsDTO;
import hu.paninform.startmedsol.service.mapper.NavSettingsMapper;

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
 * Integration tests for the {@link NavSettingsResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NavSettingsResourceIT {

    private static final String DEFAULT_TECHNICAL_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TECHNICAL_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TECHNICAL_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_TECHNICAL_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_SIGNING_KEY = "AAAAAAAAAA";
    private static final String UPDATED_SIGNING_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_REPLACEMENT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_REPLACEMENT_KEY = "BBBBBBBBBB";

    @Autowired
    private NavSettingsRepository navSettingsRepository;

    @Autowired
    private NavSettingsMapper navSettingsMapper;

    @Autowired
    private NavSettingsService navSettingsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNavSettingsMockMvc;

    private NavSettings navSettings;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NavSettings createEntity(EntityManager em) {
        NavSettings navSettings = new NavSettings()
            .technicalUserName(DEFAULT_TECHNICAL_USER_NAME)
            .technicalPassword(DEFAULT_TECHNICAL_PASSWORD)
            .signingKey(DEFAULT_SIGNING_KEY)
            .replacementKey(DEFAULT_REPLACEMENT_KEY);
        return navSettings;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NavSettings createUpdatedEntity(EntityManager em) {
        NavSettings navSettings = new NavSettings()
            .technicalUserName(UPDATED_TECHNICAL_USER_NAME)
            .technicalPassword(UPDATED_TECHNICAL_PASSWORD)
            .signingKey(UPDATED_SIGNING_KEY)
            .replacementKey(UPDATED_REPLACEMENT_KEY);
        return navSettings;
    }

    @BeforeEach
    public void initTest() {
        navSettings = createEntity(em);
    }

    @Test
    @Transactional
    public void createNavSettings() throws Exception {
        int databaseSizeBeforeCreate = navSettingsRepository.findAll().size();
        // Create the NavSettings
        NavSettingsDTO navSettingsDTO = navSettingsMapper.toDto(navSettings);
        restNavSettingsMockMvc.perform(post("/api/nav-settings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(navSettingsDTO)))
            .andExpect(status().isCreated());

        // Validate the NavSettings in the database
        List<NavSettings> navSettingsList = navSettingsRepository.findAll();
        assertThat(navSettingsList).hasSize(databaseSizeBeforeCreate + 1);
        NavSettings testNavSettings = navSettingsList.get(navSettingsList.size() - 1);
        assertThat(testNavSettings.getTechnicalUserName()).isEqualTo(DEFAULT_TECHNICAL_USER_NAME);
        assertThat(testNavSettings.getTechnicalPassword()).isEqualTo(DEFAULT_TECHNICAL_PASSWORD);
        assertThat(testNavSettings.getSigningKey()).isEqualTo(DEFAULT_SIGNING_KEY);
        assertThat(testNavSettings.getReplacementKey()).isEqualTo(DEFAULT_REPLACEMENT_KEY);
    }

    @Test
    @Transactional
    public void createNavSettingsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = navSettingsRepository.findAll().size();

        // Create the NavSettings with an existing ID
        navSettings.setId(1L);
        NavSettingsDTO navSettingsDTO = navSettingsMapper.toDto(navSettings);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNavSettingsMockMvc.perform(post("/api/nav-settings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(navSettingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NavSettings in the database
        List<NavSettings> navSettingsList = navSettingsRepository.findAll();
        assertThat(navSettingsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTechnicalUserNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = navSettingsRepository.findAll().size();
        // set the field null
        navSettings.setTechnicalUserName(null);

        // Create the NavSettings, which fails.
        NavSettingsDTO navSettingsDTO = navSettingsMapper.toDto(navSettings);


        restNavSettingsMockMvc.perform(post("/api/nav-settings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(navSettingsDTO)))
            .andExpect(status().isBadRequest());

        List<NavSettings> navSettingsList = navSettingsRepository.findAll();
        assertThat(navSettingsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTechnicalPasswordIsRequired() throws Exception {
        int databaseSizeBeforeTest = navSettingsRepository.findAll().size();
        // set the field null
        navSettings.setTechnicalPassword(null);

        // Create the NavSettings, which fails.
        NavSettingsDTO navSettingsDTO = navSettingsMapper.toDto(navSettings);


        restNavSettingsMockMvc.perform(post("/api/nav-settings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(navSettingsDTO)))
            .andExpect(status().isBadRequest());

        List<NavSettings> navSettingsList = navSettingsRepository.findAll();
        assertThat(navSettingsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSigningKeyIsRequired() throws Exception {
        int databaseSizeBeforeTest = navSettingsRepository.findAll().size();
        // set the field null
        navSettings.setSigningKey(null);

        // Create the NavSettings, which fails.
        NavSettingsDTO navSettingsDTO = navSettingsMapper.toDto(navSettings);


        restNavSettingsMockMvc.perform(post("/api/nav-settings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(navSettingsDTO)))
            .andExpect(status().isBadRequest());

        List<NavSettings> navSettingsList = navSettingsRepository.findAll();
        assertThat(navSettingsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReplacementKeyIsRequired() throws Exception {
        int databaseSizeBeforeTest = navSettingsRepository.findAll().size();
        // set the field null
        navSettings.setReplacementKey(null);

        // Create the NavSettings, which fails.
        NavSettingsDTO navSettingsDTO = navSettingsMapper.toDto(navSettings);


        restNavSettingsMockMvc.perform(post("/api/nav-settings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(navSettingsDTO)))
            .andExpect(status().isBadRequest());

        List<NavSettings> navSettingsList = navSettingsRepository.findAll();
        assertThat(navSettingsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNavSettings() throws Exception {
        // Initialize the database
        navSettingsRepository.saveAndFlush(navSettings);

        // Get all the navSettingsList
        restNavSettingsMockMvc.perform(get("/api/nav-settings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(navSettings.getId().intValue())))
            .andExpect(jsonPath("$.[*].technicalUserName").value(hasItem(DEFAULT_TECHNICAL_USER_NAME)))
            .andExpect(jsonPath("$.[*].technicalPassword").value(hasItem(DEFAULT_TECHNICAL_PASSWORD)))
            .andExpect(jsonPath("$.[*].signingKey").value(hasItem(DEFAULT_SIGNING_KEY)))
            .andExpect(jsonPath("$.[*].replacementKey").value(hasItem(DEFAULT_REPLACEMENT_KEY)));
    }
    
    @Test
    @Transactional
    public void getNavSettings() throws Exception {
        // Initialize the database
        navSettingsRepository.saveAndFlush(navSettings);

        // Get the navSettings
        restNavSettingsMockMvc.perform(get("/api/nav-settings/{id}", navSettings.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(navSettings.getId().intValue()))
            .andExpect(jsonPath("$.technicalUserName").value(DEFAULT_TECHNICAL_USER_NAME))
            .andExpect(jsonPath("$.technicalPassword").value(DEFAULT_TECHNICAL_PASSWORD))
            .andExpect(jsonPath("$.signingKey").value(DEFAULT_SIGNING_KEY))
            .andExpect(jsonPath("$.replacementKey").value(DEFAULT_REPLACEMENT_KEY));
    }
    @Test
    @Transactional
    public void getNonExistingNavSettings() throws Exception {
        // Get the navSettings
        restNavSettingsMockMvc.perform(get("/api/nav-settings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNavSettings() throws Exception {
        // Initialize the database
        navSettingsRepository.saveAndFlush(navSettings);

        int databaseSizeBeforeUpdate = navSettingsRepository.findAll().size();

        // Update the navSettings
        NavSettings updatedNavSettings = navSettingsRepository.findById(navSettings.getId()).get();
        // Disconnect from session so that the updates on updatedNavSettings are not directly saved in db
        em.detach(updatedNavSettings);
        updatedNavSettings
            .technicalUserName(UPDATED_TECHNICAL_USER_NAME)
            .technicalPassword(UPDATED_TECHNICAL_PASSWORD)
            .signingKey(UPDATED_SIGNING_KEY)
            .replacementKey(UPDATED_REPLACEMENT_KEY);
        NavSettingsDTO navSettingsDTO = navSettingsMapper.toDto(updatedNavSettings);

        restNavSettingsMockMvc.perform(put("/api/nav-settings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(navSettingsDTO)))
            .andExpect(status().isOk());

        // Validate the NavSettings in the database
        List<NavSettings> navSettingsList = navSettingsRepository.findAll();
        assertThat(navSettingsList).hasSize(databaseSizeBeforeUpdate);
        NavSettings testNavSettings = navSettingsList.get(navSettingsList.size() - 1);
        assertThat(testNavSettings.getTechnicalUserName()).isEqualTo(UPDATED_TECHNICAL_USER_NAME);
        assertThat(testNavSettings.getTechnicalPassword()).isEqualTo(UPDATED_TECHNICAL_PASSWORD);
        assertThat(testNavSettings.getSigningKey()).isEqualTo(UPDATED_SIGNING_KEY);
        assertThat(testNavSettings.getReplacementKey()).isEqualTo(UPDATED_REPLACEMENT_KEY);
    }

    @Test
    @Transactional
    public void updateNonExistingNavSettings() throws Exception {
        int databaseSizeBeforeUpdate = navSettingsRepository.findAll().size();

        // Create the NavSettings
        NavSettingsDTO navSettingsDTO = navSettingsMapper.toDto(navSettings);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNavSettingsMockMvc.perform(put("/api/nav-settings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(navSettingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NavSettings in the database
        List<NavSettings> navSettingsList = navSettingsRepository.findAll();
        assertThat(navSettingsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNavSettings() throws Exception {
        // Initialize the database
        navSettingsRepository.saveAndFlush(navSettings);

        int databaseSizeBeforeDelete = navSettingsRepository.findAll().size();

        // Delete the navSettings
        restNavSettingsMockMvc.perform(delete("/api/nav-settings/{id}", navSettings.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NavSettings> navSettingsList = navSettingsRepository.findAll();
        assertThat(navSettingsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
