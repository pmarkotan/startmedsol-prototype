package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.FeedBackMessage;
import hu.paninform.startmedsol.repository.FeedBackMessageRepository;
import hu.paninform.startmedsol.service.FeedBackMessageService;
import hu.paninform.startmedsol.service.dto.FeedBackMessageDTO;
import hu.paninform.startmedsol.service.mapper.FeedBackMessageMapper;

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

import hu.paninform.startmedsol.domain.enumeration.FeedBackMessageType;
/**
 * Integration tests for the {@link FeedBackMessageResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FeedBackMessageResourceIT {

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_AUTHOR = "AAAAAAAAAA";
    private static final String UPDATED_AUTHOR = "BBBBBBBBBB";

    private static final FeedBackMessageType DEFAULT_TYPE = FeedBackMessageType.BUG;
    private static final FeedBackMessageType UPDATED_TYPE = FeedBackMessageType.FEATURE;

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    @Autowired
    private FeedBackMessageRepository feedBackMessageRepository;

    @Autowired
    private FeedBackMessageMapper feedBackMessageMapper;

    @Autowired
    private FeedBackMessageService feedBackMessageService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFeedBackMessageMockMvc;

    private FeedBackMessage feedBackMessage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FeedBackMessage createEntity(EntityManager em) {
        FeedBackMessage feedBackMessage = new FeedBackMessage()
            .createDate(DEFAULT_CREATE_DATE)
            .author(DEFAULT_AUTHOR)
            .type(DEFAULT_TYPE)
            .content(DEFAULT_CONTENT);
        return feedBackMessage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FeedBackMessage createUpdatedEntity(EntityManager em) {
        FeedBackMessage feedBackMessage = new FeedBackMessage()
            .createDate(UPDATED_CREATE_DATE)
            .author(UPDATED_AUTHOR)
            .type(UPDATED_TYPE)
            .content(UPDATED_CONTENT);
        return feedBackMessage;
    }

    @BeforeEach
    public void initTest() {
        feedBackMessage = createEntity(em);
    }

    @Test
    @Transactional
    public void createFeedBackMessage() throws Exception {
        int databaseSizeBeforeCreate = feedBackMessageRepository.findAll().size();
        // Create the FeedBackMessage
        FeedBackMessageDTO feedBackMessageDTO = feedBackMessageMapper.toDto(feedBackMessage);
        restFeedBackMessageMockMvc.perform(post("/api/feed-back-messages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(feedBackMessageDTO)))
            .andExpect(status().isCreated());

        // Validate the FeedBackMessage in the database
        List<FeedBackMessage> feedBackMessageList = feedBackMessageRepository.findAll();
        assertThat(feedBackMessageList).hasSize(databaseSizeBeforeCreate + 1);
        FeedBackMessage testFeedBackMessage = feedBackMessageList.get(feedBackMessageList.size() - 1);
        assertThat(testFeedBackMessage.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testFeedBackMessage.getAuthor()).isEqualTo(DEFAULT_AUTHOR);
        assertThat(testFeedBackMessage.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testFeedBackMessage.getContent()).isEqualTo(DEFAULT_CONTENT);
    }

    @Test
    @Transactional
    public void createFeedBackMessageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = feedBackMessageRepository.findAll().size();

        // Create the FeedBackMessage with an existing ID
        feedBackMessage.setId(1L);
        FeedBackMessageDTO feedBackMessageDTO = feedBackMessageMapper.toDto(feedBackMessage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFeedBackMessageMockMvc.perform(post("/api/feed-back-messages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(feedBackMessageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FeedBackMessage in the database
        List<FeedBackMessage> feedBackMessageList = feedBackMessageRepository.findAll();
        assertThat(feedBackMessageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = feedBackMessageRepository.findAll().size();
        // set the field null
        feedBackMessage.setCreateDate(null);

        // Create the FeedBackMessage, which fails.
        FeedBackMessageDTO feedBackMessageDTO = feedBackMessageMapper.toDto(feedBackMessage);


        restFeedBackMessageMockMvc.perform(post("/api/feed-back-messages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(feedBackMessageDTO)))
            .andExpect(status().isBadRequest());

        List<FeedBackMessage> feedBackMessageList = feedBackMessageRepository.findAll();
        assertThat(feedBackMessageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = feedBackMessageRepository.findAll().size();
        // set the field null
        feedBackMessage.setType(null);

        // Create the FeedBackMessage, which fails.
        FeedBackMessageDTO feedBackMessageDTO = feedBackMessageMapper.toDto(feedBackMessage);


        restFeedBackMessageMockMvc.perform(post("/api/feed-back-messages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(feedBackMessageDTO)))
            .andExpect(status().isBadRequest());

        List<FeedBackMessage> feedBackMessageList = feedBackMessageRepository.findAll();
        assertThat(feedBackMessageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentIsRequired() throws Exception {
        int databaseSizeBeforeTest = feedBackMessageRepository.findAll().size();
        // set the field null
        feedBackMessage.setContent(null);

        // Create the FeedBackMessage, which fails.
        FeedBackMessageDTO feedBackMessageDTO = feedBackMessageMapper.toDto(feedBackMessage);


        restFeedBackMessageMockMvc.perform(post("/api/feed-back-messages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(feedBackMessageDTO)))
            .andExpect(status().isBadRequest());

        List<FeedBackMessage> feedBackMessageList = feedBackMessageRepository.findAll();
        assertThat(feedBackMessageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFeedBackMessages() throws Exception {
        // Initialize the database
        feedBackMessageRepository.saveAndFlush(feedBackMessage);

        // Get all the feedBackMessageList
        restFeedBackMessageMockMvc.perform(get("/api/feed-back-messages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(feedBackMessage.getId().intValue())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].author").value(hasItem(DEFAULT_AUTHOR)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT)));
    }
    
    @Test
    @Transactional
    public void getFeedBackMessage() throws Exception {
        // Initialize the database
        feedBackMessageRepository.saveAndFlush(feedBackMessage);

        // Get the feedBackMessage
        restFeedBackMessageMockMvc.perform(get("/api/feed-back-messages/{id}", feedBackMessage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(feedBackMessage.getId().intValue()))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.author").value(DEFAULT_AUTHOR))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT));
    }
    @Test
    @Transactional
    public void getNonExistingFeedBackMessage() throws Exception {
        // Get the feedBackMessage
        restFeedBackMessageMockMvc.perform(get("/api/feed-back-messages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFeedBackMessage() throws Exception {
        // Initialize the database
        feedBackMessageRepository.saveAndFlush(feedBackMessage);

        int databaseSizeBeforeUpdate = feedBackMessageRepository.findAll().size();

        // Update the feedBackMessage
        FeedBackMessage updatedFeedBackMessage = feedBackMessageRepository.findById(feedBackMessage.getId()).get();
        // Disconnect from session so that the updates on updatedFeedBackMessage are not directly saved in db
        em.detach(updatedFeedBackMessage);
        updatedFeedBackMessage
            .createDate(UPDATED_CREATE_DATE)
            .author(UPDATED_AUTHOR)
            .type(UPDATED_TYPE)
            .content(UPDATED_CONTENT);
        FeedBackMessageDTO feedBackMessageDTO = feedBackMessageMapper.toDto(updatedFeedBackMessage);

        restFeedBackMessageMockMvc.perform(put("/api/feed-back-messages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(feedBackMessageDTO)))
            .andExpect(status().isOk());

        // Validate the FeedBackMessage in the database
        List<FeedBackMessage> feedBackMessageList = feedBackMessageRepository.findAll();
        assertThat(feedBackMessageList).hasSize(databaseSizeBeforeUpdate);
        FeedBackMessage testFeedBackMessage = feedBackMessageList.get(feedBackMessageList.size() - 1);
        assertThat(testFeedBackMessage.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testFeedBackMessage.getAuthor()).isEqualTo(UPDATED_AUTHOR);
        assertThat(testFeedBackMessage.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testFeedBackMessage.getContent()).isEqualTo(UPDATED_CONTENT);
    }

    @Test
    @Transactional
    public void updateNonExistingFeedBackMessage() throws Exception {
        int databaseSizeBeforeUpdate = feedBackMessageRepository.findAll().size();

        // Create the FeedBackMessage
        FeedBackMessageDTO feedBackMessageDTO = feedBackMessageMapper.toDto(feedBackMessage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFeedBackMessageMockMvc.perform(put("/api/feed-back-messages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(feedBackMessageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FeedBackMessage in the database
        List<FeedBackMessage> feedBackMessageList = feedBackMessageRepository.findAll();
        assertThat(feedBackMessageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFeedBackMessage() throws Exception {
        // Initialize the database
        feedBackMessageRepository.saveAndFlush(feedBackMessage);

        int databaseSizeBeforeDelete = feedBackMessageRepository.findAll().size();

        // Delete the feedBackMessage
        restFeedBackMessageMockMvc.perform(delete("/api/feed-back-messages/{id}", feedBackMessage.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FeedBackMessage> feedBackMessageList = feedBackMessageRepository.findAll();
        assertThat(feedBackMessageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
