package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.Statistics;
import hu.paninform.startmedsol.repository.StatisticsRepository;
import hu.paninform.startmedsol.service.StatisticsService;
import hu.paninform.startmedsol.service.dto.StatisticsDTO;
import hu.paninform.startmedsol.service.mapper.StatisticsMapper;

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

import hu.paninform.startmedsol.domain.enumeration.StatisticsType;
/**
 * Integration tests for the {@link StatisticsResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class StatisticsResourceIT {

    private static final Instant DEFAULT_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final StatisticsType DEFAULT_TPYE = StatisticsType.DAILY_MEDICAL_CASE;
    private static final StatisticsType UPDATED_TPYE = StatisticsType.DAILY_MEDICAL_CASE;

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Autowired
    private StatisticsMapper statisticsMapper;

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStatisticsMockMvc;

    private Statistics statistics;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Statistics createEntity(EntityManager em) {
        Statistics statistics = new Statistics()
            .created(DEFAULT_CREATED)
            .tpye(DEFAULT_TPYE)
            .content(DEFAULT_CONTENT)
            .description(DEFAULT_DESCRIPTION);
        return statistics;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Statistics createUpdatedEntity(EntityManager em) {
        Statistics statistics = new Statistics()
            .created(UPDATED_CREATED)
            .tpye(UPDATED_TPYE)
            .content(UPDATED_CONTENT)
            .description(UPDATED_DESCRIPTION);
        return statistics;
    }

    @BeforeEach
    public void initTest() {
        statistics = createEntity(em);
    }

    @Test
    @Transactional
    public void createStatistics() throws Exception {
        int databaseSizeBeforeCreate = statisticsRepository.findAll().size();
        // Create the Statistics
        StatisticsDTO statisticsDTO = statisticsMapper.toDto(statistics);
        restStatisticsMockMvc.perform(post("/api/statistics")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statisticsDTO)))
            .andExpect(status().isCreated());

        // Validate the Statistics in the database
        List<Statistics> statisticsList = statisticsRepository.findAll();
        assertThat(statisticsList).hasSize(databaseSizeBeforeCreate + 1);
        Statistics testStatistics = statisticsList.get(statisticsList.size() - 1);
        assertThat(testStatistics.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testStatistics.getTpye()).isEqualTo(DEFAULT_TPYE);
        assertThat(testStatistics.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testStatistics.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createStatisticsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = statisticsRepository.findAll().size();

        // Create the Statistics with an existing ID
        statistics.setId(1L);
        StatisticsDTO statisticsDTO = statisticsMapper.toDto(statistics);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStatisticsMockMvc.perform(post("/api/statistics")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statisticsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Statistics in the database
        List<Statistics> statisticsList = statisticsRepository.findAll();
        assertThat(statisticsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCreatedIsRequired() throws Exception {
        int databaseSizeBeforeTest = statisticsRepository.findAll().size();
        // set the field null
        statistics.setCreated(null);

        // Create the Statistics, which fails.
        StatisticsDTO statisticsDTO = statisticsMapper.toDto(statistics);


        restStatisticsMockMvc.perform(post("/api/statistics")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statisticsDTO)))
            .andExpect(status().isBadRequest());

        List<Statistics> statisticsList = statisticsRepository.findAll();
        assertThat(statisticsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTpyeIsRequired() throws Exception {
        int databaseSizeBeforeTest = statisticsRepository.findAll().size();
        // set the field null
        statistics.setTpye(null);

        // Create the Statistics, which fails.
        StatisticsDTO statisticsDTO = statisticsMapper.toDto(statistics);


        restStatisticsMockMvc.perform(post("/api/statistics")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statisticsDTO)))
            .andExpect(status().isBadRequest());

        List<Statistics> statisticsList = statisticsRepository.findAll();
        assertThat(statisticsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStatistics() throws Exception {
        // Initialize the database
        statisticsRepository.saveAndFlush(statistics);

        // Get all the statisticsList
        restStatisticsMockMvc.perform(get("/api/statistics?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(statistics.getId().intValue())))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())))
            .andExpect(jsonPath("$.[*].tpye").value(hasItem(DEFAULT_TPYE.toString())))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getStatistics() throws Exception {
        // Initialize the database
        statisticsRepository.saveAndFlush(statistics);

        // Get the statistics
        restStatisticsMockMvc.perform(get("/api/statistics/{id}", statistics.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(statistics.getId().intValue()))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()))
            .andExpect(jsonPath("$.tpye").value(DEFAULT_TPYE.toString()))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingStatistics() throws Exception {
        // Get the statistics
        restStatisticsMockMvc.perform(get("/api/statistics/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStatistics() throws Exception {
        // Initialize the database
        statisticsRepository.saveAndFlush(statistics);

        int databaseSizeBeforeUpdate = statisticsRepository.findAll().size();

        // Update the statistics
        Statistics updatedStatistics = statisticsRepository.findById(statistics.getId()).get();
        // Disconnect from session so that the updates on updatedStatistics are not directly saved in db
        em.detach(updatedStatistics);
        updatedStatistics
            .created(UPDATED_CREATED)
            .tpye(UPDATED_TPYE)
            .content(UPDATED_CONTENT)
            .description(UPDATED_DESCRIPTION);
        StatisticsDTO statisticsDTO = statisticsMapper.toDto(updatedStatistics);

        restStatisticsMockMvc.perform(put("/api/statistics")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statisticsDTO)))
            .andExpect(status().isOk());

        // Validate the Statistics in the database
        List<Statistics> statisticsList = statisticsRepository.findAll();
        assertThat(statisticsList).hasSize(databaseSizeBeforeUpdate);
        Statistics testStatistics = statisticsList.get(statisticsList.size() - 1);
        assertThat(testStatistics.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testStatistics.getTpye()).isEqualTo(UPDATED_TPYE);
        assertThat(testStatistics.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testStatistics.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingStatistics() throws Exception {
        int databaseSizeBeforeUpdate = statisticsRepository.findAll().size();

        // Create the Statistics
        StatisticsDTO statisticsDTO = statisticsMapper.toDto(statistics);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStatisticsMockMvc.perform(put("/api/statistics")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statisticsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Statistics in the database
        List<Statistics> statisticsList = statisticsRepository.findAll();
        assertThat(statisticsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStatistics() throws Exception {
        // Initialize the database
        statisticsRepository.saveAndFlush(statistics);

        int databaseSizeBeforeDelete = statisticsRepository.findAll().size();

        // Delete the statistics
        restStatisticsMockMvc.perform(delete("/api/statistics/{id}", statistics.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Statistics> statisticsList = statisticsRepository.findAll();
        assertThat(statisticsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
