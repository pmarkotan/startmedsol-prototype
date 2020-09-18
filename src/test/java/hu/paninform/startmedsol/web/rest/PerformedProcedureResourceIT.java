package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.PerformedProcedure;
import hu.paninform.startmedsol.domain.CsProcedure;
import hu.paninform.startmedsol.domain.MedicalCase;
import hu.paninform.startmedsol.repository.PerformedProcedureRepository;
import hu.paninform.startmedsol.service.PerformedProcedureService;
import hu.paninform.startmedsol.service.dto.PerformedProcedureDTO;
import hu.paninform.startmedsol.service.mapper.PerformedProcedureMapper;

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
 * Integration tests for the {@link PerformedProcedureResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PerformedProcedureResourceIT {

    @Autowired
    private PerformedProcedureRepository performedProcedureRepository;

    @Autowired
    private PerformedProcedureMapper performedProcedureMapper;

    @Autowired
    private PerformedProcedureService performedProcedureService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPerformedProcedureMockMvc;

    private PerformedProcedure performedProcedure;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PerformedProcedure createEntity(EntityManager em) {
        PerformedProcedure performedProcedure = new PerformedProcedure();
        // Add required entity
        CsProcedure csProcedure;
        if (TestUtil.findAll(em, CsProcedure.class).isEmpty()) {
            csProcedure = CsProcedureResourceIT.createEntity(em);
            em.persist(csProcedure);
            em.flush();
        } else {
            csProcedure = TestUtil.findAll(em, CsProcedure.class).get(0);
        }
        performedProcedure.setProcedure(csProcedure);
        // Add required entity
        MedicalCase medicalCase;
        if (TestUtil.findAll(em, MedicalCase.class).isEmpty()) {
            medicalCase = MedicalCaseResourceIT.createEntity(em);
            em.persist(medicalCase);
            em.flush();
        } else {
            medicalCase = TestUtil.findAll(em, MedicalCase.class).get(0);
        }
        performedProcedure.setMedicalCase(medicalCase);
        return performedProcedure;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PerformedProcedure createUpdatedEntity(EntityManager em) {
        PerformedProcedure performedProcedure = new PerformedProcedure();
        // Add required entity
        CsProcedure csProcedure;
        if (TestUtil.findAll(em, CsProcedure.class).isEmpty()) {
            csProcedure = CsProcedureResourceIT.createUpdatedEntity(em);
            em.persist(csProcedure);
            em.flush();
        } else {
            csProcedure = TestUtil.findAll(em, CsProcedure.class).get(0);
        }
        performedProcedure.setProcedure(csProcedure);
        // Add required entity
        MedicalCase medicalCase;
        if (TestUtil.findAll(em, MedicalCase.class).isEmpty()) {
            medicalCase = MedicalCaseResourceIT.createUpdatedEntity(em);
            em.persist(medicalCase);
            em.flush();
        } else {
            medicalCase = TestUtil.findAll(em, MedicalCase.class).get(0);
        }
        performedProcedure.setMedicalCase(medicalCase);
        return performedProcedure;
    }

    @BeforeEach
    public void initTest() {
        performedProcedure = createEntity(em);
    }

    @Test
    @Transactional
    public void createPerformedProcedure() throws Exception {
        int databaseSizeBeforeCreate = performedProcedureRepository.findAll().size();
        // Create the PerformedProcedure
        PerformedProcedureDTO performedProcedureDTO = performedProcedureMapper.toDto(performedProcedure);
        restPerformedProcedureMockMvc.perform(post("/api/performed-procedures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(performedProcedureDTO)))
            .andExpect(status().isCreated());

        // Validate the PerformedProcedure in the database
        List<PerformedProcedure> performedProcedureList = performedProcedureRepository.findAll();
        assertThat(performedProcedureList).hasSize(databaseSizeBeforeCreate + 1);
        PerformedProcedure testPerformedProcedure = performedProcedureList.get(performedProcedureList.size() - 1);
    }

    @Test
    @Transactional
    public void createPerformedProcedureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = performedProcedureRepository.findAll().size();

        // Create the PerformedProcedure with an existing ID
        performedProcedure.setId(1L);
        PerformedProcedureDTO performedProcedureDTO = performedProcedureMapper.toDto(performedProcedure);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPerformedProcedureMockMvc.perform(post("/api/performed-procedures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(performedProcedureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PerformedProcedure in the database
        List<PerformedProcedure> performedProcedureList = performedProcedureRepository.findAll();
        assertThat(performedProcedureList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPerformedProcedures() throws Exception {
        // Initialize the database
        performedProcedureRepository.saveAndFlush(performedProcedure);

        // Get all the performedProcedureList
        restPerformedProcedureMockMvc.perform(get("/api/performed-procedures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(performedProcedure.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getPerformedProcedure() throws Exception {
        // Initialize the database
        performedProcedureRepository.saveAndFlush(performedProcedure);

        // Get the performedProcedure
        restPerformedProcedureMockMvc.perform(get("/api/performed-procedures/{id}", performedProcedure.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(performedProcedure.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPerformedProcedure() throws Exception {
        // Get the performedProcedure
        restPerformedProcedureMockMvc.perform(get("/api/performed-procedures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePerformedProcedure() throws Exception {
        // Initialize the database
        performedProcedureRepository.saveAndFlush(performedProcedure);

        int databaseSizeBeforeUpdate = performedProcedureRepository.findAll().size();

        // Update the performedProcedure
        PerformedProcedure updatedPerformedProcedure = performedProcedureRepository.findById(performedProcedure.getId()).get();
        // Disconnect from session so that the updates on updatedPerformedProcedure are not directly saved in db
        em.detach(updatedPerformedProcedure);
        PerformedProcedureDTO performedProcedureDTO = performedProcedureMapper.toDto(updatedPerformedProcedure);

        restPerformedProcedureMockMvc.perform(put("/api/performed-procedures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(performedProcedureDTO)))
            .andExpect(status().isOk());

        // Validate the PerformedProcedure in the database
        List<PerformedProcedure> performedProcedureList = performedProcedureRepository.findAll();
        assertThat(performedProcedureList).hasSize(databaseSizeBeforeUpdate);
        PerformedProcedure testPerformedProcedure = performedProcedureList.get(performedProcedureList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingPerformedProcedure() throws Exception {
        int databaseSizeBeforeUpdate = performedProcedureRepository.findAll().size();

        // Create the PerformedProcedure
        PerformedProcedureDTO performedProcedureDTO = performedProcedureMapper.toDto(performedProcedure);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPerformedProcedureMockMvc.perform(put("/api/performed-procedures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(performedProcedureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PerformedProcedure in the database
        List<PerformedProcedure> performedProcedureList = performedProcedureRepository.findAll();
        assertThat(performedProcedureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePerformedProcedure() throws Exception {
        // Initialize the database
        performedProcedureRepository.saveAndFlush(performedProcedure);

        int databaseSizeBeforeDelete = performedProcedureRepository.findAll().size();

        // Delete the performedProcedure
        restPerformedProcedureMockMvc.perform(delete("/api/performed-procedures/{id}", performedProcedure.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PerformedProcedure> performedProcedureList = performedProcedureRepository.findAll();
        assertThat(performedProcedureList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
