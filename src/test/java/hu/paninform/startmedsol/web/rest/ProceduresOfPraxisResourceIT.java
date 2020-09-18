package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.ProceduresOfPraxis;
import hu.paninform.startmedsol.domain.CsProcedure;
import hu.paninform.startmedsol.domain.Praxis;
import hu.paninform.startmedsol.repository.ProceduresOfPraxisRepository;
import hu.paninform.startmedsol.service.ProceduresOfPraxisService;
import hu.paninform.startmedsol.service.dto.ProceduresOfPraxisDTO;
import hu.paninform.startmedsol.service.mapper.ProceduresOfPraxisMapper;

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
 * Integration tests for the {@link ProceduresOfPraxisResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProceduresOfPraxisResourceIT {

    @Autowired
    private ProceduresOfPraxisRepository proceduresOfPraxisRepository;

    @Autowired
    private ProceduresOfPraxisMapper proceduresOfPraxisMapper;

    @Autowired
    private ProceduresOfPraxisService proceduresOfPraxisService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProceduresOfPraxisMockMvc;

    private ProceduresOfPraxis proceduresOfPraxis;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProceduresOfPraxis createEntity(EntityManager em) {
        ProceduresOfPraxis proceduresOfPraxis = new ProceduresOfPraxis();
        // Add required entity
        CsProcedure csProcedure;
        if (TestUtil.findAll(em, CsProcedure.class).isEmpty()) {
            csProcedure = CsProcedureResourceIT.createEntity(em);
            em.persist(csProcedure);
            em.flush();
        } else {
            csProcedure = TestUtil.findAll(em, CsProcedure.class).get(0);
        }
        proceduresOfPraxis.setProcedure(csProcedure);
        // Add required entity
        Praxis praxis;
        if (TestUtil.findAll(em, Praxis.class).isEmpty()) {
            praxis = PraxisResourceIT.createEntity(em);
            em.persist(praxis);
            em.flush();
        } else {
            praxis = TestUtil.findAll(em, Praxis.class).get(0);
        }
        proceduresOfPraxis.setPraxis(praxis);
        return proceduresOfPraxis;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProceduresOfPraxis createUpdatedEntity(EntityManager em) {
        ProceduresOfPraxis proceduresOfPraxis = new ProceduresOfPraxis();
        // Add required entity
        CsProcedure csProcedure;
        if (TestUtil.findAll(em, CsProcedure.class).isEmpty()) {
            csProcedure = CsProcedureResourceIT.createUpdatedEntity(em);
            em.persist(csProcedure);
            em.flush();
        } else {
            csProcedure = TestUtil.findAll(em, CsProcedure.class).get(0);
        }
        proceduresOfPraxis.setProcedure(csProcedure);
        // Add required entity
        Praxis praxis;
        if (TestUtil.findAll(em, Praxis.class).isEmpty()) {
            praxis = PraxisResourceIT.createUpdatedEntity(em);
            em.persist(praxis);
            em.flush();
        } else {
            praxis = TestUtil.findAll(em, Praxis.class).get(0);
        }
        proceduresOfPraxis.setPraxis(praxis);
        return proceduresOfPraxis;
    }

    @BeforeEach
    public void initTest() {
        proceduresOfPraxis = createEntity(em);
    }

    @Test
    @Transactional
    public void createProceduresOfPraxis() throws Exception {
        int databaseSizeBeforeCreate = proceduresOfPraxisRepository.findAll().size();
        // Create the ProceduresOfPraxis
        ProceduresOfPraxisDTO proceduresOfPraxisDTO = proceduresOfPraxisMapper.toDto(proceduresOfPraxis);
        restProceduresOfPraxisMockMvc.perform(post("/api/procedures-of-praxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proceduresOfPraxisDTO)))
            .andExpect(status().isCreated());

        // Validate the ProceduresOfPraxis in the database
        List<ProceduresOfPraxis> proceduresOfPraxisList = proceduresOfPraxisRepository.findAll();
        assertThat(proceduresOfPraxisList).hasSize(databaseSizeBeforeCreate + 1);
        ProceduresOfPraxis testProceduresOfPraxis = proceduresOfPraxisList.get(proceduresOfPraxisList.size() - 1);
    }

    @Test
    @Transactional
    public void createProceduresOfPraxisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proceduresOfPraxisRepository.findAll().size();

        // Create the ProceduresOfPraxis with an existing ID
        proceduresOfPraxis.setId(1L);
        ProceduresOfPraxisDTO proceduresOfPraxisDTO = proceduresOfPraxisMapper.toDto(proceduresOfPraxis);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProceduresOfPraxisMockMvc.perform(post("/api/procedures-of-praxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proceduresOfPraxisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProceduresOfPraxis in the database
        List<ProceduresOfPraxis> proceduresOfPraxisList = proceduresOfPraxisRepository.findAll();
        assertThat(proceduresOfPraxisList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProceduresOfPraxes() throws Exception {
        // Initialize the database
        proceduresOfPraxisRepository.saveAndFlush(proceduresOfPraxis);

        // Get all the proceduresOfPraxisList
        restProceduresOfPraxisMockMvc.perform(get("/api/procedures-of-praxes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proceduresOfPraxis.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getProceduresOfPraxis() throws Exception {
        // Initialize the database
        proceduresOfPraxisRepository.saveAndFlush(proceduresOfPraxis);

        // Get the proceduresOfPraxis
        restProceduresOfPraxisMockMvc.perform(get("/api/procedures-of-praxes/{id}", proceduresOfPraxis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(proceduresOfPraxis.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingProceduresOfPraxis() throws Exception {
        // Get the proceduresOfPraxis
        restProceduresOfPraxisMockMvc.perform(get("/api/procedures-of-praxes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProceduresOfPraxis() throws Exception {
        // Initialize the database
        proceduresOfPraxisRepository.saveAndFlush(proceduresOfPraxis);

        int databaseSizeBeforeUpdate = proceduresOfPraxisRepository.findAll().size();

        // Update the proceduresOfPraxis
        ProceduresOfPraxis updatedProceduresOfPraxis = proceduresOfPraxisRepository.findById(proceduresOfPraxis.getId()).get();
        // Disconnect from session so that the updates on updatedProceduresOfPraxis are not directly saved in db
        em.detach(updatedProceduresOfPraxis);
        ProceduresOfPraxisDTO proceduresOfPraxisDTO = proceduresOfPraxisMapper.toDto(updatedProceduresOfPraxis);

        restProceduresOfPraxisMockMvc.perform(put("/api/procedures-of-praxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proceduresOfPraxisDTO)))
            .andExpect(status().isOk());

        // Validate the ProceduresOfPraxis in the database
        List<ProceduresOfPraxis> proceduresOfPraxisList = proceduresOfPraxisRepository.findAll();
        assertThat(proceduresOfPraxisList).hasSize(databaseSizeBeforeUpdate);
        ProceduresOfPraxis testProceduresOfPraxis = proceduresOfPraxisList.get(proceduresOfPraxisList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingProceduresOfPraxis() throws Exception {
        int databaseSizeBeforeUpdate = proceduresOfPraxisRepository.findAll().size();

        // Create the ProceduresOfPraxis
        ProceduresOfPraxisDTO proceduresOfPraxisDTO = proceduresOfPraxisMapper.toDto(proceduresOfPraxis);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProceduresOfPraxisMockMvc.perform(put("/api/procedures-of-praxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proceduresOfPraxisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProceduresOfPraxis in the database
        List<ProceduresOfPraxis> proceduresOfPraxisList = proceduresOfPraxisRepository.findAll();
        assertThat(proceduresOfPraxisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProceduresOfPraxis() throws Exception {
        // Initialize the database
        proceduresOfPraxisRepository.saveAndFlush(proceduresOfPraxis);

        int databaseSizeBeforeDelete = proceduresOfPraxisRepository.findAll().size();

        // Delete the proceduresOfPraxis
        restProceduresOfPraxisMockMvc.perform(delete("/api/procedures-of-praxes/{id}", proceduresOfPraxis.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProceduresOfPraxis> proceduresOfPraxisList = proceduresOfPraxisRepository.findAll();
        assertThat(proceduresOfPraxisList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
