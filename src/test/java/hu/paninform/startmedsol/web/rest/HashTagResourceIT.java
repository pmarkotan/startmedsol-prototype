package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.HashTag;
import hu.paninform.startmedsol.domain.Provider;
import hu.paninform.startmedsol.domain.Patient;
import hu.paninform.startmedsol.repository.HashTagRepository;
import hu.paninform.startmedsol.service.HashTagService;
import hu.paninform.startmedsol.service.dto.HashTagDTO;
import hu.paninform.startmedsol.service.mapper.HashTagMapper;
import hu.paninform.startmedsol.service.dto.HashTagCriteria;
import hu.paninform.startmedsol.service.HashTagQueryService;

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
 * Integration tests for the {@link HashTagResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class HashTagResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private HashTagRepository hashTagRepository;

    @Autowired
    private HashTagMapper hashTagMapper;

    @Autowired
    private HashTagService hashTagService;

    @Autowired
    private HashTagQueryService hashTagQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHashTagMockMvc;

    private HashTag hashTag;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HashTag createEntity(EntityManager em) {
        HashTag hashTag = new HashTag()
            .name(DEFAULT_NAME);
        // Add required entity
        Provider provider;
        if (TestUtil.findAll(em, Provider.class).isEmpty()) {
            provider = ProviderResourceIT.createEntity(em);
            em.persist(provider);
            em.flush();
        } else {
            provider = TestUtil.findAll(em, Provider.class).get(0);
        }
        hashTag.setProvider(provider);
        return hashTag;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HashTag createUpdatedEntity(EntityManager em) {
        HashTag hashTag = new HashTag()
            .name(UPDATED_NAME);
        // Add required entity
        Provider provider;
        if (TestUtil.findAll(em, Provider.class).isEmpty()) {
            provider = ProviderResourceIT.createUpdatedEntity(em);
            em.persist(provider);
            em.flush();
        } else {
            provider = TestUtil.findAll(em, Provider.class).get(0);
        }
        hashTag.setProvider(provider);
        return hashTag;
    }

    @BeforeEach
    public void initTest() {
        hashTag = createEntity(em);
    }

    @Test
    @Transactional
    public void createHashTag() throws Exception {
        int databaseSizeBeforeCreate = hashTagRepository.findAll().size();
        // Create the HashTag
        HashTagDTO hashTagDTO = hashTagMapper.toDto(hashTag);
        restHashTagMockMvc.perform(post("/api/hash-tags")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(hashTagDTO)))
            .andExpect(status().isCreated());

        // Validate the HashTag in the database
        List<HashTag> hashTagList = hashTagRepository.findAll();
        assertThat(hashTagList).hasSize(databaseSizeBeforeCreate + 1);
        HashTag testHashTag = hashTagList.get(hashTagList.size() - 1);
        assertThat(testHashTag.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createHashTagWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = hashTagRepository.findAll().size();

        // Create the HashTag with an existing ID
        hashTag.setId(1L);
        HashTagDTO hashTagDTO = hashTagMapper.toDto(hashTag);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHashTagMockMvc.perform(post("/api/hash-tags")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(hashTagDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HashTag in the database
        List<HashTag> hashTagList = hashTagRepository.findAll();
        assertThat(hashTagList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllHashTags() throws Exception {
        // Initialize the database
        hashTagRepository.saveAndFlush(hashTag);

        // Get all the hashTagList
        restHashTagMockMvc.perform(get("/api/hash-tags?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hashTag.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getHashTag() throws Exception {
        // Initialize the database
        hashTagRepository.saveAndFlush(hashTag);

        // Get the hashTag
        restHashTagMockMvc.perform(get("/api/hash-tags/{id}", hashTag.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(hashTag.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }


    @Test
    @Transactional
    public void getHashTagsByIdFiltering() throws Exception {
        // Initialize the database
        hashTagRepository.saveAndFlush(hashTag);

        Long id = hashTag.getId();

        defaultHashTagShouldBeFound("id.equals=" + id);
        defaultHashTagShouldNotBeFound("id.notEquals=" + id);

        defaultHashTagShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultHashTagShouldNotBeFound("id.greaterThan=" + id);

        defaultHashTagShouldBeFound("id.lessThanOrEqual=" + id);
        defaultHashTagShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllHashTagsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        hashTagRepository.saveAndFlush(hashTag);

        // Get all the hashTagList where name equals to DEFAULT_NAME
        defaultHashTagShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the hashTagList where name equals to UPDATED_NAME
        defaultHashTagShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllHashTagsByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        hashTagRepository.saveAndFlush(hashTag);

        // Get all the hashTagList where name not equals to DEFAULT_NAME
        defaultHashTagShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

        // Get all the hashTagList where name not equals to UPDATED_NAME
        defaultHashTagShouldBeFound("name.notEquals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllHashTagsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        hashTagRepository.saveAndFlush(hashTag);

        // Get all the hashTagList where name in DEFAULT_NAME or UPDATED_NAME
        defaultHashTagShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the hashTagList where name equals to UPDATED_NAME
        defaultHashTagShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllHashTagsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        hashTagRepository.saveAndFlush(hashTag);

        // Get all the hashTagList where name is not null
        defaultHashTagShouldBeFound("name.specified=true");

        // Get all the hashTagList where name is null
        defaultHashTagShouldNotBeFound("name.specified=false");
    }
                @Test
    @Transactional
    public void getAllHashTagsByNameContainsSomething() throws Exception {
        // Initialize the database
        hashTagRepository.saveAndFlush(hashTag);

        // Get all the hashTagList where name contains DEFAULT_NAME
        defaultHashTagShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the hashTagList where name contains UPDATED_NAME
        defaultHashTagShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllHashTagsByNameNotContainsSomething() throws Exception {
        // Initialize the database
        hashTagRepository.saveAndFlush(hashTag);

        // Get all the hashTagList where name does not contain DEFAULT_NAME
        defaultHashTagShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the hashTagList where name does not contain UPDATED_NAME
        defaultHashTagShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }


    @Test
    @Transactional
    public void getAllHashTagsByProviderIsEqualToSomething() throws Exception {
        // Get already existing entity
        Provider provider = hashTag.getProvider();
        hashTagRepository.saveAndFlush(hashTag);
        Long providerId = provider.getId();

        // Get all the hashTagList where provider equals to providerId
        defaultHashTagShouldBeFound("providerId.equals=" + providerId);

        // Get all the hashTagList where provider equals to providerId + 1
        defaultHashTagShouldNotBeFound("providerId.equals=" + (providerId + 1));
    }


    @Test
    @Transactional
    public void getAllHashTagsByPatientsIsEqualToSomething() throws Exception {
        // Initialize the database
        hashTagRepository.saveAndFlush(hashTag);
        Patient patients = PatientResourceIT.createEntity(em);
        em.persist(patients);
        em.flush();
        hashTag.addPatients(patients);
        hashTagRepository.saveAndFlush(hashTag);
        Long patientsId = patients.getId();

        // Get all the hashTagList where patients equals to patientsId
        defaultHashTagShouldBeFound("patientsId.equals=" + patientsId);

        // Get all the hashTagList where patients equals to patientsId + 1
        defaultHashTagShouldNotBeFound("patientsId.equals=" + (patientsId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultHashTagShouldBeFound(String filter) throws Exception {
        restHashTagMockMvc.perform(get("/api/hash-tags?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hashTag.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));

        // Check, that the count call also returns 1
        restHashTagMockMvc.perform(get("/api/hash-tags/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultHashTagShouldNotBeFound(String filter) throws Exception {
        restHashTagMockMvc.perform(get("/api/hash-tags?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restHashTagMockMvc.perform(get("/api/hash-tags/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingHashTag() throws Exception {
        // Get the hashTag
        restHashTagMockMvc.perform(get("/api/hash-tags/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHashTag() throws Exception {
        // Initialize the database
        hashTagRepository.saveAndFlush(hashTag);

        int databaseSizeBeforeUpdate = hashTagRepository.findAll().size();

        // Update the hashTag
        HashTag updatedHashTag = hashTagRepository.findById(hashTag.getId()).get();
        // Disconnect from session so that the updates on updatedHashTag are not directly saved in db
        em.detach(updatedHashTag);
        updatedHashTag
            .name(UPDATED_NAME);
        HashTagDTO hashTagDTO = hashTagMapper.toDto(updatedHashTag);

        restHashTagMockMvc.perform(put("/api/hash-tags")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(hashTagDTO)))
            .andExpect(status().isOk());

        // Validate the HashTag in the database
        List<HashTag> hashTagList = hashTagRepository.findAll();
        assertThat(hashTagList).hasSize(databaseSizeBeforeUpdate);
        HashTag testHashTag = hashTagList.get(hashTagList.size() - 1);
        assertThat(testHashTag.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingHashTag() throws Exception {
        int databaseSizeBeforeUpdate = hashTagRepository.findAll().size();

        // Create the HashTag
        HashTagDTO hashTagDTO = hashTagMapper.toDto(hashTag);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHashTagMockMvc.perform(put("/api/hash-tags")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(hashTagDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HashTag in the database
        List<HashTag> hashTagList = hashTagRepository.findAll();
        assertThat(hashTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHashTag() throws Exception {
        // Initialize the database
        hashTagRepository.saveAndFlush(hashTag);

        int databaseSizeBeforeDelete = hashTagRepository.findAll().size();

        // Delete the hashTag
        restHashTagMockMvc.perform(delete("/api/hash-tags/{id}", hashTag.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HashTag> hashTagList = hashTagRepository.findAll();
        assertThat(hashTagList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
