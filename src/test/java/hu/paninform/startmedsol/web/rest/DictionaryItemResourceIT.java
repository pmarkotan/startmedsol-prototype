package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.DictionaryItem;
import hu.paninform.startmedsol.repository.DictionaryItemRepository;
import hu.paninform.startmedsol.service.DictionaryItemService;
import hu.paninform.startmedsol.service.dto.DictionaryItemDTO;
import hu.paninform.startmedsol.service.mapper.DictionaryItemMapper;

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
 * Integration tests for the {@link DictionaryItemResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class DictionaryItemResourceIT {

    private static final String DEFAULT_DICTIONARY_ITEM_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DICTIONARY_ITEM_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDER_NUMBER = 1;
    private static final Integer UPDATED_ORDER_NUMBER = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private DictionaryItemRepository dictionaryItemRepository;

    @Mock
    private DictionaryItemRepository dictionaryItemRepositoryMock;

    @Autowired
    private DictionaryItemMapper dictionaryItemMapper;

    @Mock
    private DictionaryItemService dictionaryItemServiceMock;

    @Autowired
    private DictionaryItemService dictionaryItemService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDictionaryItemMockMvc;

    private DictionaryItem dictionaryItem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DictionaryItem createEntity(EntityManager em) {
        DictionaryItem dictionaryItem = new DictionaryItem()
            .dictionaryItemType(DEFAULT_DICTIONARY_ITEM_TYPE)
            .code(DEFAULT_CODE)
            .orderNumber(DEFAULT_ORDER_NUMBER)
            .description(DEFAULT_DESCRIPTION);
        return dictionaryItem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DictionaryItem createUpdatedEntity(EntityManager em) {
        DictionaryItem dictionaryItem = new DictionaryItem()
            .dictionaryItemType(UPDATED_DICTIONARY_ITEM_TYPE)
            .code(UPDATED_CODE)
            .orderNumber(UPDATED_ORDER_NUMBER)
            .description(UPDATED_DESCRIPTION);
        return dictionaryItem;
    }

    @BeforeEach
    public void initTest() {
        dictionaryItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createDictionaryItem() throws Exception {
        int databaseSizeBeforeCreate = dictionaryItemRepository.findAll().size();
        // Create the DictionaryItem
        DictionaryItemDTO dictionaryItemDTO = dictionaryItemMapper.toDto(dictionaryItem);
        restDictionaryItemMockMvc.perform(post("/api/dictionary-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dictionaryItemDTO)))
            .andExpect(status().isCreated());

        // Validate the DictionaryItem in the database
        List<DictionaryItem> dictionaryItemList = dictionaryItemRepository.findAll();
        assertThat(dictionaryItemList).hasSize(databaseSizeBeforeCreate + 1);
        DictionaryItem testDictionaryItem = dictionaryItemList.get(dictionaryItemList.size() - 1);
        assertThat(testDictionaryItem.getDictionaryItemType()).isEqualTo(DEFAULT_DICTIONARY_ITEM_TYPE);
        assertThat(testDictionaryItem.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testDictionaryItem.getOrderNumber()).isEqualTo(DEFAULT_ORDER_NUMBER);
        assertThat(testDictionaryItem.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createDictionaryItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dictionaryItemRepository.findAll().size();

        // Create the DictionaryItem with an existing ID
        dictionaryItem.setId(1L);
        DictionaryItemDTO dictionaryItemDTO = dictionaryItemMapper.toDto(dictionaryItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDictionaryItemMockMvc.perform(post("/api/dictionary-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dictionaryItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DictionaryItem in the database
        List<DictionaryItem> dictionaryItemList = dictionaryItemRepository.findAll();
        assertThat(dictionaryItemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDictionaryItemTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dictionaryItemRepository.findAll().size();
        // set the field null
        dictionaryItem.setDictionaryItemType(null);

        // Create the DictionaryItem, which fails.
        DictionaryItemDTO dictionaryItemDTO = dictionaryItemMapper.toDto(dictionaryItem);


        restDictionaryItemMockMvc.perform(post("/api/dictionary-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dictionaryItemDTO)))
            .andExpect(status().isBadRequest());

        List<DictionaryItem> dictionaryItemList = dictionaryItemRepository.findAll();
        assertThat(dictionaryItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dictionaryItemRepository.findAll().size();
        // set the field null
        dictionaryItem.setCode(null);

        // Create the DictionaryItem, which fails.
        DictionaryItemDTO dictionaryItemDTO = dictionaryItemMapper.toDto(dictionaryItem);


        restDictionaryItemMockMvc.perform(post("/api/dictionary-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dictionaryItemDTO)))
            .andExpect(status().isBadRequest());

        List<DictionaryItem> dictionaryItemList = dictionaryItemRepository.findAll();
        assertThat(dictionaryItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDictionaryItems() throws Exception {
        // Initialize the database
        dictionaryItemRepository.saveAndFlush(dictionaryItem);

        // Get all the dictionaryItemList
        restDictionaryItemMockMvc.perform(get("/api/dictionary-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dictionaryItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].dictionaryItemType").value(hasItem(DEFAULT_DICTIONARY_ITEM_TYPE)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].orderNumber").value(hasItem(DEFAULT_ORDER_NUMBER)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllDictionaryItemsWithEagerRelationshipsIsEnabled() throws Exception {
        when(dictionaryItemServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restDictionaryItemMockMvc.perform(get("/api/dictionary-items?eagerload=true"))
            .andExpect(status().isOk());

        verify(dictionaryItemServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllDictionaryItemsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(dictionaryItemServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restDictionaryItemMockMvc.perform(get("/api/dictionary-items?eagerload=true"))
            .andExpect(status().isOk());

        verify(dictionaryItemServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getDictionaryItem() throws Exception {
        // Initialize the database
        dictionaryItemRepository.saveAndFlush(dictionaryItem);

        // Get the dictionaryItem
        restDictionaryItemMockMvc.perform(get("/api/dictionary-items/{id}", dictionaryItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dictionaryItem.getId().intValue()))
            .andExpect(jsonPath("$.dictionaryItemType").value(DEFAULT_DICTIONARY_ITEM_TYPE))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.orderNumber").value(DEFAULT_ORDER_NUMBER))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingDictionaryItem() throws Exception {
        // Get the dictionaryItem
        restDictionaryItemMockMvc.perform(get("/api/dictionary-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDictionaryItem() throws Exception {
        // Initialize the database
        dictionaryItemRepository.saveAndFlush(dictionaryItem);

        int databaseSizeBeforeUpdate = dictionaryItemRepository.findAll().size();

        // Update the dictionaryItem
        DictionaryItem updatedDictionaryItem = dictionaryItemRepository.findById(dictionaryItem.getId()).get();
        // Disconnect from session so that the updates on updatedDictionaryItem are not directly saved in db
        em.detach(updatedDictionaryItem);
        updatedDictionaryItem
            .dictionaryItemType(UPDATED_DICTIONARY_ITEM_TYPE)
            .code(UPDATED_CODE)
            .orderNumber(UPDATED_ORDER_NUMBER)
            .description(UPDATED_DESCRIPTION);
        DictionaryItemDTO dictionaryItemDTO = dictionaryItemMapper.toDto(updatedDictionaryItem);

        restDictionaryItemMockMvc.perform(put("/api/dictionary-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dictionaryItemDTO)))
            .andExpect(status().isOk());

        // Validate the DictionaryItem in the database
        List<DictionaryItem> dictionaryItemList = dictionaryItemRepository.findAll();
        assertThat(dictionaryItemList).hasSize(databaseSizeBeforeUpdate);
        DictionaryItem testDictionaryItem = dictionaryItemList.get(dictionaryItemList.size() - 1);
        assertThat(testDictionaryItem.getDictionaryItemType()).isEqualTo(UPDATED_DICTIONARY_ITEM_TYPE);
        assertThat(testDictionaryItem.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testDictionaryItem.getOrderNumber()).isEqualTo(UPDATED_ORDER_NUMBER);
        assertThat(testDictionaryItem.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingDictionaryItem() throws Exception {
        int databaseSizeBeforeUpdate = dictionaryItemRepository.findAll().size();

        // Create the DictionaryItem
        DictionaryItemDTO dictionaryItemDTO = dictionaryItemMapper.toDto(dictionaryItem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDictionaryItemMockMvc.perform(put("/api/dictionary-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dictionaryItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DictionaryItem in the database
        List<DictionaryItem> dictionaryItemList = dictionaryItemRepository.findAll();
        assertThat(dictionaryItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDictionaryItem() throws Exception {
        // Initialize the database
        dictionaryItemRepository.saveAndFlush(dictionaryItem);

        int databaseSizeBeforeDelete = dictionaryItemRepository.findAll().size();

        // Delete the dictionaryItem
        restDictionaryItemMockMvc.perform(delete("/api/dictionary-items/{id}", dictionaryItem.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DictionaryItem> dictionaryItemList = dictionaryItemRepository.findAll();
        assertThat(dictionaryItemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
