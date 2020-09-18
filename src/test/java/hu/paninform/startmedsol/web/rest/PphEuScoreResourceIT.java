package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.PphEuScore;
import hu.paninform.startmedsol.repository.PphEuScoreRepository;

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
import hu.paninform.startmedsol.domain.enumeration.IndicationType;
/**
 * Integration tests for the {@link PphEuScoreResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PphEuScoreResourceIT {

    private static final Integer DEFAULT_EXTERNAL_ID = 1;
    private static final Integer UPDATED_EXTERNAL_ID = 2;

    private static final SubsidyCategory DEFAULT_SUBSIDY_CATEGORY = SubsidyCategory.EU50;
    private static final SubsidyCategory UPDATED_SUBSIDY_CATEGORY = SubsidyCategory.EU70;

    private static final Integer DEFAULT_INDICATION_CODE_1 = 1;
    private static final Integer UPDATED_INDICATION_CODE_1 = 2;

    private static final String DEFAULT_INDICATION_CODE_2 = "AAAAAAAAAA";
    private static final String UPDATED_INDICATION_CODE_2 = "BBBBBBBBBB";

    private static final IndicationType DEFAULT_INDICATION_TYPE = IndicationType.G_MEDICINE;
    private static final IndicationType UPDATED_INDICATION_TYPE = IndicationType.S_GYSE;

    private static final String DEFAULT_PRESCR_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_PRESCR_COMMENT = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE_PUPHA_DATA = false;
    private static final Boolean UPDATED_ACTIVE_PUPHA_DATA = true;

    @Autowired
    private PphEuScoreRepository pphEuScoreRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPphEuScoreMockMvc;

    private PphEuScore pphEuScore;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphEuScore createEntity(EntityManager em) {
        PphEuScore pphEuScore = new PphEuScore()
            .externalId(DEFAULT_EXTERNAL_ID)
            .subsidyCategory(DEFAULT_SUBSIDY_CATEGORY)
            .indicationCode1(DEFAULT_INDICATION_CODE_1)
            .indicationCode2(DEFAULT_INDICATION_CODE_2)
            .indicationType(DEFAULT_INDICATION_TYPE)
            .prescrComment(DEFAULT_PRESCR_COMMENT)
            .comment(DEFAULT_COMMENT)
            .activePuphaData(DEFAULT_ACTIVE_PUPHA_DATA);
        return pphEuScore;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphEuScore createUpdatedEntity(EntityManager em) {
        PphEuScore pphEuScore = new PphEuScore()
            .externalId(UPDATED_EXTERNAL_ID)
            .subsidyCategory(UPDATED_SUBSIDY_CATEGORY)
            .indicationCode1(UPDATED_INDICATION_CODE_1)
            .indicationCode2(UPDATED_INDICATION_CODE_2)
            .indicationType(UPDATED_INDICATION_TYPE)
            .prescrComment(UPDATED_PRESCR_COMMENT)
            .comment(UPDATED_COMMENT)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);
        return pphEuScore;
    }

    @BeforeEach
    public void initTest() {
        pphEuScore = createEntity(em);
    }

    @Test
    @Transactional
    public void createPphEuScore() throws Exception {
        int databaseSizeBeforeCreate = pphEuScoreRepository.findAll().size();
        // Create the PphEuScore
        restPphEuScoreMockMvc.perform(post("/api/pph-eu-scores")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphEuScore)))
            .andExpect(status().isCreated());

        // Validate the PphEuScore in the database
        List<PphEuScore> pphEuScoreList = pphEuScoreRepository.findAll();
        assertThat(pphEuScoreList).hasSize(databaseSizeBeforeCreate + 1);
        PphEuScore testPphEuScore = pphEuScoreList.get(pphEuScoreList.size() - 1);
        assertThat(testPphEuScore.getExternalId()).isEqualTo(DEFAULT_EXTERNAL_ID);
        assertThat(testPphEuScore.getSubsidyCategory()).isEqualTo(DEFAULT_SUBSIDY_CATEGORY);
        assertThat(testPphEuScore.getIndicationCode1()).isEqualTo(DEFAULT_INDICATION_CODE_1);
        assertThat(testPphEuScore.getIndicationCode2()).isEqualTo(DEFAULT_INDICATION_CODE_2);
        assertThat(testPphEuScore.getIndicationType()).isEqualTo(DEFAULT_INDICATION_TYPE);
        assertThat(testPphEuScore.getPrescrComment()).isEqualTo(DEFAULT_PRESCR_COMMENT);
        assertThat(testPphEuScore.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testPphEuScore.isActivePuphaData()).isEqualTo(DEFAULT_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void createPphEuScoreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pphEuScoreRepository.findAll().size();

        // Create the PphEuScore with an existing ID
        pphEuScore.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPphEuScoreMockMvc.perform(post("/api/pph-eu-scores")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphEuScore)))
            .andExpect(status().isBadRequest());

        // Validate the PphEuScore in the database
        List<PphEuScore> pphEuScoreList = pphEuScoreRepository.findAll();
        assertThat(pphEuScoreList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkActivePuphaDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = pphEuScoreRepository.findAll().size();
        // set the field null
        pphEuScore.setActivePuphaData(null);

        // Create the PphEuScore, which fails.


        restPphEuScoreMockMvc.perform(post("/api/pph-eu-scores")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphEuScore)))
            .andExpect(status().isBadRequest());

        List<PphEuScore> pphEuScoreList = pphEuScoreRepository.findAll();
        assertThat(pphEuScoreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPphEuScores() throws Exception {
        // Initialize the database
        pphEuScoreRepository.saveAndFlush(pphEuScore);

        // Get all the pphEuScoreList
        restPphEuScoreMockMvc.perform(get("/api/pph-eu-scores?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pphEuScore.getId().intValue())))
            .andExpect(jsonPath("$.[*].externalId").value(hasItem(DEFAULT_EXTERNAL_ID)))
            .andExpect(jsonPath("$.[*].subsidyCategory").value(hasItem(DEFAULT_SUBSIDY_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].indicationCode1").value(hasItem(DEFAULT_INDICATION_CODE_1)))
            .andExpect(jsonPath("$.[*].indicationCode2").value(hasItem(DEFAULT_INDICATION_CODE_2)))
            .andExpect(jsonPath("$.[*].indicationType").value(hasItem(DEFAULT_INDICATION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].prescrComment").value(hasItem(DEFAULT_PRESCR_COMMENT)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].activePuphaData").value(hasItem(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPphEuScore() throws Exception {
        // Initialize the database
        pphEuScoreRepository.saveAndFlush(pphEuScore);

        // Get the pphEuScore
        restPphEuScoreMockMvc.perform(get("/api/pph-eu-scores/{id}", pphEuScore.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pphEuScore.getId().intValue()))
            .andExpect(jsonPath("$.externalId").value(DEFAULT_EXTERNAL_ID))
            .andExpect(jsonPath("$.subsidyCategory").value(DEFAULT_SUBSIDY_CATEGORY.toString()))
            .andExpect(jsonPath("$.indicationCode1").value(DEFAULT_INDICATION_CODE_1))
            .andExpect(jsonPath("$.indicationCode2").value(DEFAULT_INDICATION_CODE_2))
            .andExpect(jsonPath("$.indicationType").value(DEFAULT_INDICATION_TYPE.toString()))
            .andExpect(jsonPath("$.prescrComment").value(DEFAULT_PRESCR_COMMENT))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.activePuphaData").value(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPphEuScore() throws Exception {
        // Get the pphEuScore
        restPphEuScoreMockMvc.perform(get("/api/pph-eu-scores/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePphEuScore() throws Exception {
        // Initialize the database
        pphEuScoreRepository.saveAndFlush(pphEuScore);

        int databaseSizeBeforeUpdate = pphEuScoreRepository.findAll().size();

        // Update the pphEuScore
        PphEuScore updatedPphEuScore = pphEuScoreRepository.findById(pphEuScore.getId()).get();
        // Disconnect from session so that the updates on updatedPphEuScore are not directly saved in db
        em.detach(updatedPphEuScore);
        updatedPphEuScore
            .externalId(UPDATED_EXTERNAL_ID)
            .subsidyCategory(UPDATED_SUBSIDY_CATEGORY)
            .indicationCode1(UPDATED_INDICATION_CODE_1)
            .indicationCode2(UPDATED_INDICATION_CODE_2)
            .indicationType(UPDATED_INDICATION_TYPE)
            .prescrComment(UPDATED_PRESCR_COMMENT)
            .comment(UPDATED_COMMENT)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);

        restPphEuScoreMockMvc.perform(put("/api/pph-eu-scores")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPphEuScore)))
            .andExpect(status().isOk());

        // Validate the PphEuScore in the database
        List<PphEuScore> pphEuScoreList = pphEuScoreRepository.findAll();
        assertThat(pphEuScoreList).hasSize(databaseSizeBeforeUpdate);
        PphEuScore testPphEuScore = pphEuScoreList.get(pphEuScoreList.size() - 1);
        assertThat(testPphEuScore.getExternalId()).isEqualTo(UPDATED_EXTERNAL_ID);
        assertThat(testPphEuScore.getSubsidyCategory()).isEqualTo(UPDATED_SUBSIDY_CATEGORY);
        assertThat(testPphEuScore.getIndicationCode1()).isEqualTo(UPDATED_INDICATION_CODE_1);
        assertThat(testPphEuScore.getIndicationCode2()).isEqualTo(UPDATED_INDICATION_CODE_2);
        assertThat(testPphEuScore.getIndicationType()).isEqualTo(UPDATED_INDICATION_TYPE);
        assertThat(testPphEuScore.getPrescrComment()).isEqualTo(UPDATED_PRESCR_COMMENT);
        assertThat(testPphEuScore.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testPphEuScore.isActivePuphaData()).isEqualTo(UPDATED_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingPphEuScore() throws Exception {
        int databaseSizeBeforeUpdate = pphEuScoreRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPphEuScoreMockMvc.perform(put("/api/pph-eu-scores")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphEuScore)))
            .andExpect(status().isBadRequest());

        // Validate the PphEuScore in the database
        List<PphEuScore> pphEuScoreList = pphEuScoreRepository.findAll();
        assertThat(pphEuScoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePphEuScore() throws Exception {
        // Initialize the database
        pphEuScoreRepository.saveAndFlush(pphEuScore);

        int databaseSizeBeforeDelete = pphEuScoreRepository.findAll().size();

        // Delete the pphEuScore
        restPphEuScoreMockMvc.perform(delete("/api/pph-eu-scores/{id}", pphEuScore.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PphEuScore> pphEuScoreList = pphEuScoreRepository.findAll();
        assertThat(pphEuScoreList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
