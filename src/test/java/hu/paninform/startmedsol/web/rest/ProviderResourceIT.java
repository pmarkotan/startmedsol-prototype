package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.Provider;
import hu.paninform.startmedsol.repository.ProviderRepository;
import hu.paninform.startmedsol.service.ProviderService;
import hu.paninform.startmedsol.service.dto.ProviderDTO;
import hu.paninform.startmedsol.service.mapper.ProviderMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProviderResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProviderResourceIT {

    private static final String DEFAULT_NAME_LONG = "AAAAAAAAAA";
    private static final String UPDATED_NAME_LONG = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_SHORT = "AAAAAAAAAA";
    private static final String UPDATED_NAME_SHORT = "BBBBBBBBBB";

    private static final String DEFAULT_INSTITUTION_ID = "AAAAAA";
    private static final String UPDATED_INSTITUTION_ID = "BBBBBB";

    private static final String DEFAULT_EMAIL = "=th&@_.w";
    private static final String UPDATED_EMAIL = "[kft[u@(EWt'.gfrJ41";

    private static final String DEFAULT_PHONE = "++53";
    private static final String UPDATED_PHONE = "+++13906";

    private static final String DEFAULT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NUMBER = "BBBBBBBBBB";

    @Autowired
    private ProviderRepository providerRepository;

    @Mock
    private ProviderRepository providerRepositoryMock;

    @Autowired
    private ProviderMapper providerMapper;

    @Mock
    private ProviderService providerServiceMock;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProviderMockMvc;

    private Provider provider;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Provider createEntity(EntityManager em) {
        Provider provider = new Provider()
            .nameLong(DEFAULT_NAME_LONG)
            .nameShort(DEFAULT_NAME_SHORT)
            .institutionId(DEFAULT_INSTITUTION_ID)
            .email(DEFAULT_EMAIL)
            .phone(DEFAULT_PHONE)
            .accountNumber(DEFAULT_ACCOUNT_NUMBER);
        return provider;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Provider createUpdatedEntity(EntityManager em) {
        Provider provider = new Provider()
            .nameLong(UPDATED_NAME_LONG)
            .nameShort(UPDATED_NAME_SHORT)
            .institutionId(UPDATED_INSTITUTION_ID)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE)
            .accountNumber(UPDATED_ACCOUNT_NUMBER);
        return provider;
    }

    @BeforeEach
    public void initTest() {
        provider = createEntity(em);
    }

    @Test
    @Transactional
    public void createProvider() throws Exception {
        int databaseSizeBeforeCreate = providerRepository.findAll().size();
        // Create the Provider
        ProviderDTO providerDTO = providerMapper.toDto(provider);
        restProviderMockMvc.perform(post("/api/providers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providerDTO)))
            .andExpect(status().isCreated());

        // Validate the Provider in the database
        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeCreate + 1);
        Provider testProvider = providerList.get(providerList.size() - 1);
        assertThat(testProvider.getNameLong()).isEqualTo(DEFAULT_NAME_LONG);
        assertThat(testProvider.getNameShort()).isEqualTo(DEFAULT_NAME_SHORT);
        assertThat(testProvider.getInstitutionId()).isEqualTo(DEFAULT_INSTITUTION_ID);
        assertThat(testProvider.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testProvider.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testProvider.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
    }

    @Test
    @Transactional
    public void createProviderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = providerRepository.findAll().size();

        // Create the Provider with an existing ID
        provider.setId(1L);
        ProviderDTO providerDTO = providerMapper.toDto(provider);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProviderMockMvc.perform(post("/api/providers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Provider in the database
        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameLongIsRequired() throws Exception {
        int databaseSizeBeforeTest = providerRepository.findAll().size();
        // set the field null
        provider.setNameLong(null);

        // Create the Provider, which fails.
        ProviderDTO providerDTO = providerMapper.toDto(provider);


        restProviderMockMvc.perform(post("/api/providers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providerDTO)))
            .andExpect(status().isBadRequest());

        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInstitutionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = providerRepository.findAll().size();
        // set the field null
        provider.setInstitutionId(null);

        // Create the Provider, which fails.
        ProviderDTO providerDTO = providerMapper.toDto(provider);


        restProviderMockMvc.perform(post("/api/providers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providerDTO)))
            .andExpect(status().isBadRequest());

        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = providerRepository.findAll().size();
        // set the field null
        provider.setEmail(null);

        // Create the Provider, which fails.
        ProviderDTO providerDTO = providerMapper.toDto(provider);


        restProviderMockMvc.perform(post("/api/providers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providerDTO)))
            .andExpect(status().isBadRequest());

        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = providerRepository.findAll().size();
        // set the field null
        provider.setPhone(null);

        // Create the Provider, which fails.
        ProviderDTO providerDTO = providerMapper.toDto(provider);


        restProviderMockMvc.perform(post("/api/providers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providerDTO)))
            .andExpect(status().isBadRequest());

        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAccountNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = providerRepository.findAll().size();
        // set the field null
        provider.setAccountNumber(null);

        // Create the Provider, which fails.
        ProviderDTO providerDTO = providerMapper.toDto(provider);


        restProviderMockMvc.perform(post("/api/providers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providerDTO)))
            .andExpect(status().isBadRequest());

        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProviders() throws Exception {
        // Initialize the database
        providerRepository.saveAndFlush(provider);

        // Get all the providerList
        restProviderMockMvc.perform(get("/api/providers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(provider.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameLong").value(hasItem(DEFAULT_NAME_LONG)))
            .andExpect(jsonPath("$.[*].nameShort").value(hasItem(DEFAULT_NAME_SHORT)))
            .andExpect(jsonPath("$.[*].institutionId").value(hasItem(DEFAULT_INSTITUTION_ID)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].accountNumber").value(hasItem(DEFAULT_ACCOUNT_NUMBER)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllProvidersWithEagerRelationshipsIsEnabled() throws Exception {
        when(providerServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restProviderMockMvc.perform(get("/api/providers?eagerload=true"))
            .andExpect(status().isOk());

        verify(providerServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllProvidersWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(providerServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restProviderMockMvc.perform(get("/api/providers?eagerload=true"))
            .andExpect(status().isOk());

        verify(providerServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getProvider() throws Exception {
        // Initialize the database
        providerRepository.saveAndFlush(provider);

        // Get the provider
        restProviderMockMvc.perform(get("/api/providers/{id}", provider.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(provider.getId().intValue()))
            .andExpect(jsonPath("$.nameLong").value(DEFAULT_NAME_LONG))
            .andExpect(jsonPath("$.nameShort").value(DEFAULT_NAME_SHORT))
            .andExpect(jsonPath("$.institutionId").value(DEFAULT_INSTITUTION_ID))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.accountNumber").value(DEFAULT_ACCOUNT_NUMBER));
    }
    @Test
    @Transactional
    public void getNonExistingProvider() throws Exception {
        // Get the provider
        restProviderMockMvc.perform(get("/api/providers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProvider() throws Exception {
        // Initialize the database
        providerRepository.saveAndFlush(provider);

        int databaseSizeBeforeUpdate = providerRepository.findAll().size();

        // Update the provider
        Provider updatedProvider = providerRepository.findById(provider.getId()).get();
        // Disconnect from session so that the updates on updatedProvider are not directly saved in db
        em.detach(updatedProvider);
        updatedProvider
            .nameLong(UPDATED_NAME_LONG)
            .nameShort(UPDATED_NAME_SHORT)
            .institutionId(UPDATED_INSTITUTION_ID)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE)
            .accountNumber(UPDATED_ACCOUNT_NUMBER);
        ProviderDTO providerDTO = providerMapper.toDto(updatedProvider);

        restProviderMockMvc.perform(put("/api/providers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providerDTO)))
            .andExpect(status().isOk());

        // Validate the Provider in the database
        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeUpdate);
        Provider testProvider = providerList.get(providerList.size() - 1);
        assertThat(testProvider.getNameLong()).isEqualTo(UPDATED_NAME_LONG);
        assertThat(testProvider.getNameShort()).isEqualTo(UPDATED_NAME_SHORT);
        assertThat(testProvider.getInstitutionId()).isEqualTo(UPDATED_INSTITUTION_ID);
        assertThat(testProvider.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testProvider.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testProvider.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingProvider() throws Exception {
        int databaseSizeBeforeUpdate = providerRepository.findAll().size();

        // Create the Provider
        ProviderDTO providerDTO = providerMapper.toDto(provider);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProviderMockMvc.perform(put("/api/providers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Provider in the database
        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProvider() throws Exception {
        // Initialize the database
        providerRepository.saveAndFlush(provider);

        int databaseSizeBeforeDelete = providerRepository.findAll().size();

        // Delete the provider
        restProviderMockMvc.perform(delete("/api/providers/{id}", provider.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
