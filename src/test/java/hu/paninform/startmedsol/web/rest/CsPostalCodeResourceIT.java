package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.CsPostalCode;
import hu.paninform.startmedsol.domain.Validity;
import hu.paninform.startmedsol.repository.CsPostalCodeRepository;
import hu.paninform.startmedsol.service.CsPostalCodeService;
import hu.paninform.startmedsol.service.dto.CsPostalCodeDTO;
import hu.paninform.startmedsol.service.mapper.CsPostalCodeMapper;
import hu.paninform.startmedsol.service.dto.CsPostalCodeCriteria;
import hu.paninform.startmedsol.service.CsPostalCodeQueryService;

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

import hu.paninform.startmedsol.domain.enumeration.PcRange;
/**
 * Integration tests for the {@link CsPostalCodeResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CsPostalCodeResourceIT {

    private static final String DEFAULT_CODE = "AAAAA";
    private static final String UPDATED_CODE = "BBBBB";

    private static final String DEFAULT_SETTLEMENT = "AAAAAAAAAA";
    private static final String UPDATED_SETTLEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_PART = "AAAAAAAAAA";
    private static final String UPDATED_PART = "BBBBBBBBBB";

    private static final String DEFAULT_STREET = "AAAAAAAAAA";
    private static final String UPDATED_STREET = "BBBBBBBBBB";

    private static final String DEFAULT_KIND = "AAAAAAAAAA";
    private static final String UPDATED_KIND = "BBBBBBBBBB";

    private static final PcRange DEFAULT_RANGE_TYPE = PcRange.NONE;
    private static final PcRange UPDATED_RANGE_TYPE = PcRange.ALL;

    private static final Integer DEFAULT_RANGE_LO = 0;
    private static final Integer UPDATED_RANGE_LO = 1;
    private static final Integer SMALLER_RANGE_LO = 0 - 1;

    private static final Integer DEFAULT_RANGE_HI = 0;
    private static final Integer UPDATED_RANGE_HI = 1;
    private static final Integer SMALLER_RANGE_HI = 0 - 1;

    private static final String DEFAULT_DISTRICT = "AAAAAAAAAA";
    private static final String UPDATED_DISTRICT = "BBBBBBBBBB";

    @Autowired
    private CsPostalCodeRepository csPostalCodeRepository;

    @Autowired
    private CsPostalCodeMapper csPostalCodeMapper;

    @Autowired
    private CsPostalCodeService csPostalCodeService;

    @Autowired
    private CsPostalCodeQueryService csPostalCodeQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCsPostalCodeMockMvc;

    private CsPostalCode csPostalCode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CsPostalCode createEntity(EntityManager em) {
        CsPostalCode csPostalCode = new CsPostalCode()
            .code(DEFAULT_CODE)
            .settlement(DEFAULT_SETTLEMENT)
            .part(DEFAULT_PART)
            .street(DEFAULT_STREET)
            .kind(DEFAULT_KIND)
            .rangeType(DEFAULT_RANGE_TYPE)
            .rangeLo(DEFAULT_RANGE_LO)
            .rangeHi(DEFAULT_RANGE_HI)
            .district(DEFAULT_DISTRICT);
        // Add required entity
        Validity validity;
        if (TestUtil.findAll(em, Validity.class).isEmpty()) {
            validity = ValidityResourceIT.createEntity(em);
            em.persist(validity);
            em.flush();
        } else {
            validity = TestUtil.findAll(em, Validity.class).get(0);
        }
        csPostalCode.setValidity(validity);
        return csPostalCode;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CsPostalCode createUpdatedEntity(EntityManager em) {
        CsPostalCode csPostalCode = new CsPostalCode()
            .code(UPDATED_CODE)
            .settlement(UPDATED_SETTLEMENT)
            .part(UPDATED_PART)
            .street(UPDATED_STREET)
            .kind(UPDATED_KIND)
            .rangeType(UPDATED_RANGE_TYPE)
            .rangeLo(UPDATED_RANGE_LO)
            .rangeHi(UPDATED_RANGE_HI)
            .district(UPDATED_DISTRICT);
        // Add required entity
        Validity validity;
        if (TestUtil.findAll(em, Validity.class).isEmpty()) {
            validity = ValidityResourceIT.createUpdatedEntity(em);
            em.persist(validity);
            em.flush();
        } else {
            validity = TestUtil.findAll(em, Validity.class).get(0);
        }
        csPostalCode.setValidity(validity);
        return csPostalCode;
    }

    @BeforeEach
    public void initTest() {
        csPostalCode = createEntity(em);
    }

    @Test
    @Transactional
    public void createCsPostalCode() throws Exception {
        int databaseSizeBeforeCreate = csPostalCodeRepository.findAll().size();
        // Create the CsPostalCode
        CsPostalCodeDTO csPostalCodeDTO = csPostalCodeMapper.toDto(csPostalCode);
        restCsPostalCodeMockMvc.perform(post("/api/cs-postal-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csPostalCodeDTO)))
            .andExpect(status().isCreated());

        // Validate the CsPostalCode in the database
        List<CsPostalCode> csPostalCodeList = csPostalCodeRepository.findAll();
        assertThat(csPostalCodeList).hasSize(databaseSizeBeforeCreate + 1);
        CsPostalCode testCsPostalCode = csPostalCodeList.get(csPostalCodeList.size() - 1);
        assertThat(testCsPostalCode.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testCsPostalCode.getSettlement()).isEqualTo(DEFAULT_SETTLEMENT);
        assertThat(testCsPostalCode.getPart()).isEqualTo(DEFAULT_PART);
        assertThat(testCsPostalCode.getStreet()).isEqualTo(DEFAULT_STREET);
        assertThat(testCsPostalCode.getKind()).isEqualTo(DEFAULT_KIND);
        assertThat(testCsPostalCode.getRangeType()).isEqualTo(DEFAULT_RANGE_TYPE);
        assertThat(testCsPostalCode.getRangeLo()).isEqualTo(DEFAULT_RANGE_LO);
        assertThat(testCsPostalCode.getRangeHi()).isEqualTo(DEFAULT_RANGE_HI);
        assertThat(testCsPostalCode.getDistrict()).isEqualTo(DEFAULT_DISTRICT);
    }

    @Test
    @Transactional
    public void createCsPostalCodeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = csPostalCodeRepository.findAll().size();

        // Create the CsPostalCode with an existing ID
        csPostalCode.setId(1L);
        CsPostalCodeDTO csPostalCodeDTO = csPostalCodeMapper.toDto(csPostalCode);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCsPostalCodeMockMvc.perform(post("/api/cs-postal-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csPostalCodeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CsPostalCode in the database
        List<CsPostalCode> csPostalCodeList = csPostalCodeRepository.findAll();
        assertThat(csPostalCodeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = csPostalCodeRepository.findAll().size();
        // set the field null
        csPostalCode.setCode(null);

        // Create the CsPostalCode, which fails.
        CsPostalCodeDTO csPostalCodeDTO = csPostalCodeMapper.toDto(csPostalCode);


        restCsPostalCodeMockMvc.perform(post("/api/cs-postal-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csPostalCodeDTO)))
            .andExpect(status().isBadRequest());

        List<CsPostalCode> csPostalCodeList = csPostalCodeRepository.findAll();
        assertThat(csPostalCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSettlementIsRequired() throws Exception {
        int databaseSizeBeforeTest = csPostalCodeRepository.findAll().size();
        // set the field null
        csPostalCode.setSettlement(null);

        // Create the CsPostalCode, which fails.
        CsPostalCodeDTO csPostalCodeDTO = csPostalCodeMapper.toDto(csPostalCode);


        restCsPostalCodeMockMvc.perform(post("/api/cs-postal-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csPostalCodeDTO)))
            .andExpect(status().isBadRequest());

        List<CsPostalCode> csPostalCodeList = csPostalCodeRepository.findAll();
        assertThat(csPostalCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRangeTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = csPostalCodeRepository.findAll().size();
        // set the field null
        csPostalCode.setRangeType(null);

        // Create the CsPostalCode, which fails.
        CsPostalCodeDTO csPostalCodeDTO = csPostalCodeMapper.toDto(csPostalCode);


        restCsPostalCodeMockMvc.perform(post("/api/cs-postal-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csPostalCodeDTO)))
            .andExpect(status().isBadRequest());

        List<CsPostalCode> csPostalCodeList = csPostalCodeRepository.findAll();
        assertThat(csPostalCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodes() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList
        restCsPostalCodeMockMvc.perform(get("/api/cs-postal-codes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(csPostalCode.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].settlement").value(hasItem(DEFAULT_SETTLEMENT)))
            .andExpect(jsonPath("$.[*].part").value(hasItem(DEFAULT_PART)))
            .andExpect(jsonPath("$.[*].street").value(hasItem(DEFAULT_STREET)))
            .andExpect(jsonPath("$.[*].kind").value(hasItem(DEFAULT_KIND)))
            .andExpect(jsonPath("$.[*].rangeType").value(hasItem(DEFAULT_RANGE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].rangeLo").value(hasItem(DEFAULT_RANGE_LO)))
            .andExpect(jsonPath("$.[*].rangeHi").value(hasItem(DEFAULT_RANGE_HI)))
            .andExpect(jsonPath("$.[*].district").value(hasItem(DEFAULT_DISTRICT)));
    }
    
    @Test
    @Transactional
    public void getCsPostalCode() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get the csPostalCode
        restCsPostalCodeMockMvc.perform(get("/api/cs-postal-codes/{id}", csPostalCode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(csPostalCode.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.settlement").value(DEFAULT_SETTLEMENT))
            .andExpect(jsonPath("$.part").value(DEFAULT_PART))
            .andExpect(jsonPath("$.street").value(DEFAULT_STREET))
            .andExpect(jsonPath("$.kind").value(DEFAULT_KIND))
            .andExpect(jsonPath("$.rangeType").value(DEFAULT_RANGE_TYPE.toString()))
            .andExpect(jsonPath("$.rangeLo").value(DEFAULT_RANGE_LO))
            .andExpect(jsonPath("$.rangeHi").value(DEFAULT_RANGE_HI))
            .andExpect(jsonPath("$.district").value(DEFAULT_DISTRICT));
    }


    @Test
    @Transactional
    public void getCsPostalCodesByIdFiltering() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        Long id = csPostalCode.getId();

        defaultCsPostalCodeShouldBeFound("id.equals=" + id);
        defaultCsPostalCodeShouldNotBeFound("id.notEquals=" + id);

        defaultCsPostalCodeShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCsPostalCodeShouldNotBeFound("id.greaterThan=" + id);

        defaultCsPostalCodeShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCsPostalCodeShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCsPostalCodesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where code equals to DEFAULT_CODE
        defaultCsPostalCodeShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the csPostalCodeList where code equals to UPDATED_CODE
        defaultCsPostalCodeShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where code not equals to DEFAULT_CODE
        defaultCsPostalCodeShouldNotBeFound("code.notEquals=" + DEFAULT_CODE);

        // Get all the csPostalCodeList where code not equals to UPDATED_CODE
        defaultCsPostalCodeShouldBeFound("code.notEquals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where code in DEFAULT_CODE or UPDATED_CODE
        defaultCsPostalCodeShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the csPostalCodeList where code equals to UPDATED_CODE
        defaultCsPostalCodeShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where code is not null
        defaultCsPostalCodeShouldBeFound("code.specified=true");

        // Get all the csPostalCodeList where code is null
        defaultCsPostalCodeShouldNotBeFound("code.specified=false");
    }
                @Test
    @Transactional
    public void getAllCsPostalCodesByCodeContainsSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where code contains DEFAULT_CODE
        defaultCsPostalCodeShouldBeFound("code.contains=" + DEFAULT_CODE);

        // Get all the csPostalCodeList where code contains UPDATED_CODE
        defaultCsPostalCodeShouldNotBeFound("code.contains=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByCodeNotContainsSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where code does not contain DEFAULT_CODE
        defaultCsPostalCodeShouldNotBeFound("code.doesNotContain=" + DEFAULT_CODE);

        // Get all the csPostalCodeList where code does not contain UPDATED_CODE
        defaultCsPostalCodeShouldBeFound("code.doesNotContain=" + UPDATED_CODE);
    }


    @Test
    @Transactional
    public void getAllCsPostalCodesBySettlementIsEqualToSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where settlement equals to DEFAULT_SETTLEMENT
        defaultCsPostalCodeShouldBeFound("settlement.equals=" + DEFAULT_SETTLEMENT);

        // Get all the csPostalCodeList where settlement equals to UPDATED_SETTLEMENT
        defaultCsPostalCodeShouldNotBeFound("settlement.equals=" + UPDATED_SETTLEMENT);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesBySettlementIsNotEqualToSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where settlement not equals to DEFAULT_SETTLEMENT
        defaultCsPostalCodeShouldNotBeFound("settlement.notEquals=" + DEFAULT_SETTLEMENT);

        // Get all the csPostalCodeList where settlement not equals to UPDATED_SETTLEMENT
        defaultCsPostalCodeShouldBeFound("settlement.notEquals=" + UPDATED_SETTLEMENT);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesBySettlementIsInShouldWork() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where settlement in DEFAULT_SETTLEMENT or UPDATED_SETTLEMENT
        defaultCsPostalCodeShouldBeFound("settlement.in=" + DEFAULT_SETTLEMENT + "," + UPDATED_SETTLEMENT);

        // Get all the csPostalCodeList where settlement equals to UPDATED_SETTLEMENT
        defaultCsPostalCodeShouldNotBeFound("settlement.in=" + UPDATED_SETTLEMENT);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesBySettlementIsNullOrNotNull() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where settlement is not null
        defaultCsPostalCodeShouldBeFound("settlement.specified=true");

        // Get all the csPostalCodeList where settlement is null
        defaultCsPostalCodeShouldNotBeFound("settlement.specified=false");
    }
                @Test
    @Transactional
    public void getAllCsPostalCodesBySettlementContainsSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where settlement contains DEFAULT_SETTLEMENT
        defaultCsPostalCodeShouldBeFound("settlement.contains=" + DEFAULT_SETTLEMENT);

        // Get all the csPostalCodeList where settlement contains UPDATED_SETTLEMENT
        defaultCsPostalCodeShouldNotBeFound("settlement.contains=" + UPDATED_SETTLEMENT);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesBySettlementNotContainsSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where settlement does not contain DEFAULT_SETTLEMENT
        defaultCsPostalCodeShouldNotBeFound("settlement.doesNotContain=" + DEFAULT_SETTLEMENT);

        // Get all the csPostalCodeList where settlement does not contain UPDATED_SETTLEMENT
        defaultCsPostalCodeShouldBeFound("settlement.doesNotContain=" + UPDATED_SETTLEMENT);
    }


    @Test
    @Transactional
    public void getAllCsPostalCodesByPartIsEqualToSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where part equals to DEFAULT_PART
        defaultCsPostalCodeShouldBeFound("part.equals=" + DEFAULT_PART);

        // Get all the csPostalCodeList where part equals to UPDATED_PART
        defaultCsPostalCodeShouldNotBeFound("part.equals=" + UPDATED_PART);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByPartIsNotEqualToSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where part not equals to DEFAULT_PART
        defaultCsPostalCodeShouldNotBeFound("part.notEquals=" + DEFAULT_PART);

        // Get all the csPostalCodeList where part not equals to UPDATED_PART
        defaultCsPostalCodeShouldBeFound("part.notEquals=" + UPDATED_PART);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByPartIsInShouldWork() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where part in DEFAULT_PART or UPDATED_PART
        defaultCsPostalCodeShouldBeFound("part.in=" + DEFAULT_PART + "," + UPDATED_PART);

        // Get all the csPostalCodeList where part equals to UPDATED_PART
        defaultCsPostalCodeShouldNotBeFound("part.in=" + UPDATED_PART);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByPartIsNullOrNotNull() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where part is not null
        defaultCsPostalCodeShouldBeFound("part.specified=true");

        // Get all the csPostalCodeList where part is null
        defaultCsPostalCodeShouldNotBeFound("part.specified=false");
    }
                @Test
    @Transactional
    public void getAllCsPostalCodesByPartContainsSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where part contains DEFAULT_PART
        defaultCsPostalCodeShouldBeFound("part.contains=" + DEFAULT_PART);

        // Get all the csPostalCodeList where part contains UPDATED_PART
        defaultCsPostalCodeShouldNotBeFound("part.contains=" + UPDATED_PART);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByPartNotContainsSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where part does not contain DEFAULT_PART
        defaultCsPostalCodeShouldNotBeFound("part.doesNotContain=" + DEFAULT_PART);

        // Get all the csPostalCodeList where part does not contain UPDATED_PART
        defaultCsPostalCodeShouldBeFound("part.doesNotContain=" + UPDATED_PART);
    }


    @Test
    @Transactional
    public void getAllCsPostalCodesByStreetIsEqualToSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where street equals to DEFAULT_STREET
        defaultCsPostalCodeShouldBeFound("street.equals=" + DEFAULT_STREET);

        // Get all the csPostalCodeList where street equals to UPDATED_STREET
        defaultCsPostalCodeShouldNotBeFound("street.equals=" + UPDATED_STREET);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByStreetIsNotEqualToSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where street not equals to DEFAULT_STREET
        defaultCsPostalCodeShouldNotBeFound("street.notEquals=" + DEFAULT_STREET);

        // Get all the csPostalCodeList where street not equals to UPDATED_STREET
        defaultCsPostalCodeShouldBeFound("street.notEquals=" + UPDATED_STREET);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByStreetIsInShouldWork() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where street in DEFAULT_STREET or UPDATED_STREET
        defaultCsPostalCodeShouldBeFound("street.in=" + DEFAULT_STREET + "," + UPDATED_STREET);

        // Get all the csPostalCodeList where street equals to UPDATED_STREET
        defaultCsPostalCodeShouldNotBeFound("street.in=" + UPDATED_STREET);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByStreetIsNullOrNotNull() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where street is not null
        defaultCsPostalCodeShouldBeFound("street.specified=true");

        // Get all the csPostalCodeList where street is null
        defaultCsPostalCodeShouldNotBeFound("street.specified=false");
    }
                @Test
    @Transactional
    public void getAllCsPostalCodesByStreetContainsSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where street contains DEFAULT_STREET
        defaultCsPostalCodeShouldBeFound("street.contains=" + DEFAULT_STREET);

        // Get all the csPostalCodeList where street contains UPDATED_STREET
        defaultCsPostalCodeShouldNotBeFound("street.contains=" + UPDATED_STREET);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByStreetNotContainsSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where street does not contain DEFAULT_STREET
        defaultCsPostalCodeShouldNotBeFound("street.doesNotContain=" + DEFAULT_STREET);

        // Get all the csPostalCodeList where street does not contain UPDATED_STREET
        defaultCsPostalCodeShouldBeFound("street.doesNotContain=" + UPDATED_STREET);
    }


    @Test
    @Transactional
    public void getAllCsPostalCodesByKindIsEqualToSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where kind equals to DEFAULT_KIND
        defaultCsPostalCodeShouldBeFound("kind.equals=" + DEFAULT_KIND);

        // Get all the csPostalCodeList where kind equals to UPDATED_KIND
        defaultCsPostalCodeShouldNotBeFound("kind.equals=" + UPDATED_KIND);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByKindIsNotEqualToSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where kind not equals to DEFAULT_KIND
        defaultCsPostalCodeShouldNotBeFound("kind.notEquals=" + DEFAULT_KIND);

        // Get all the csPostalCodeList where kind not equals to UPDATED_KIND
        defaultCsPostalCodeShouldBeFound("kind.notEquals=" + UPDATED_KIND);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByKindIsInShouldWork() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where kind in DEFAULT_KIND or UPDATED_KIND
        defaultCsPostalCodeShouldBeFound("kind.in=" + DEFAULT_KIND + "," + UPDATED_KIND);

        // Get all the csPostalCodeList where kind equals to UPDATED_KIND
        defaultCsPostalCodeShouldNotBeFound("kind.in=" + UPDATED_KIND);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByKindIsNullOrNotNull() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where kind is not null
        defaultCsPostalCodeShouldBeFound("kind.specified=true");

        // Get all the csPostalCodeList where kind is null
        defaultCsPostalCodeShouldNotBeFound("kind.specified=false");
    }
                @Test
    @Transactional
    public void getAllCsPostalCodesByKindContainsSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where kind contains DEFAULT_KIND
        defaultCsPostalCodeShouldBeFound("kind.contains=" + DEFAULT_KIND);

        // Get all the csPostalCodeList where kind contains UPDATED_KIND
        defaultCsPostalCodeShouldNotBeFound("kind.contains=" + UPDATED_KIND);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByKindNotContainsSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where kind does not contain DEFAULT_KIND
        defaultCsPostalCodeShouldNotBeFound("kind.doesNotContain=" + DEFAULT_KIND);

        // Get all the csPostalCodeList where kind does not contain UPDATED_KIND
        defaultCsPostalCodeShouldBeFound("kind.doesNotContain=" + UPDATED_KIND);
    }


    @Test
    @Transactional
    public void getAllCsPostalCodesByRangeTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where rangeType equals to DEFAULT_RANGE_TYPE
        defaultCsPostalCodeShouldBeFound("rangeType.equals=" + DEFAULT_RANGE_TYPE);

        // Get all the csPostalCodeList where rangeType equals to UPDATED_RANGE_TYPE
        defaultCsPostalCodeShouldNotBeFound("rangeType.equals=" + UPDATED_RANGE_TYPE);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByRangeTypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where rangeType not equals to DEFAULT_RANGE_TYPE
        defaultCsPostalCodeShouldNotBeFound("rangeType.notEquals=" + DEFAULT_RANGE_TYPE);

        // Get all the csPostalCodeList where rangeType not equals to UPDATED_RANGE_TYPE
        defaultCsPostalCodeShouldBeFound("rangeType.notEquals=" + UPDATED_RANGE_TYPE);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByRangeTypeIsInShouldWork() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where rangeType in DEFAULT_RANGE_TYPE or UPDATED_RANGE_TYPE
        defaultCsPostalCodeShouldBeFound("rangeType.in=" + DEFAULT_RANGE_TYPE + "," + UPDATED_RANGE_TYPE);

        // Get all the csPostalCodeList where rangeType equals to UPDATED_RANGE_TYPE
        defaultCsPostalCodeShouldNotBeFound("rangeType.in=" + UPDATED_RANGE_TYPE);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByRangeTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where rangeType is not null
        defaultCsPostalCodeShouldBeFound("rangeType.specified=true");

        // Get all the csPostalCodeList where rangeType is null
        defaultCsPostalCodeShouldNotBeFound("rangeType.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByRangeLoIsEqualToSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where rangeLo equals to DEFAULT_RANGE_LO
        defaultCsPostalCodeShouldBeFound("rangeLo.equals=" + DEFAULT_RANGE_LO);

        // Get all the csPostalCodeList where rangeLo equals to UPDATED_RANGE_LO
        defaultCsPostalCodeShouldNotBeFound("rangeLo.equals=" + UPDATED_RANGE_LO);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByRangeLoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where rangeLo not equals to DEFAULT_RANGE_LO
        defaultCsPostalCodeShouldNotBeFound("rangeLo.notEquals=" + DEFAULT_RANGE_LO);

        // Get all the csPostalCodeList where rangeLo not equals to UPDATED_RANGE_LO
        defaultCsPostalCodeShouldBeFound("rangeLo.notEquals=" + UPDATED_RANGE_LO);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByRangeLoIsInShouldWork() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where rangeLo in DEFAULT_RANGE_LO or UPDATED_RANGE_LO
        defaultCsPostalCodeShouldBeFound("rangeLo.in=" + DEFAULT_RANGE_LO + "," + UPDATED_RANGE_LO);

        // Get all the csPostalCodeList where rangeLo equals to UPDATED_RANGE_LO
        defaultCsPostalCodeShouldNotBeFound("rangeLo.in=" + UPDATED_RANGE_LO);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByRangeLoIsNullOrNotNull() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where rangeLo is not null
        defaultCsPostalCodeShouldBeFound("rangeLo.specified=true");

        // Get all the csPostalCodeList where rangeLo is null
        defaultCsPostalCodeShouldNotBeFound("rangeLo.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByRangeLoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where rangeLo is greater than or equal to DEFAULT_RANGE_LO
        defaultCsPostalCodeShouldBeFound("rangeLo.greaterThanOrEqual=" + DEFAULT_RANGE_LO);

        // Get all the csPostalCodeList where rangeLo is greater than or equal to UPDATED_RANGE_LO
        defaultCsPostalCodeShouldNotBeFound("rangeLo.greaterThanOrEqual=" + UPDATED_RANGE_LO);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByRangeLoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where rangeLo is less than or equal to DEFAULT_RANGE_LO
        defaultCsPostalCodeShouldBeFound("rangeLo.lessThanOrEqual=" + DEFAULT_RANGE_LO);

        // Get all the csPostalCodeList where rangeLo is less than or equal to SMALLER_RANGE_LO
        defaultCsPostalCodeShouldNotBeFound("rangeLo.lessThanOrEqual=" + SMALLER_RANGE_LO);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByRangeLoIsLessThanSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where rangeLo is less than DEFAULT_RANGE_LO
        defaultCsPostalCodeShouldNotBeFound("rangeLo.lessThan=" + DEFAULT_RANGE_LO);

        // Get all the csPostalCodeList where rangeLo is less than UPDATED_RANGE_LO
        defaultCsPostalCodeShouldBeFound("rangeLo.lessThan=" + UPDATED_RANGE_LO);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByRangeLoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where rangeLo is greater than DEFAULT_RANGE_LO
        defaultCsPostalCodeShouldNotBeFound("rangeLo.greaterThan=" + DEFAULT_RANGE_LO);

        // Get all the csPostalCodeList where rangeLo is greater than SMALLER_RANGE_LO
        defaultCsPostalCodeShouldBeFound("rangeLo.greaterThan=" + SMALLER_RANGE_LO);
    }


    @Test
    @Transactional
    public void getAllCsPostalCodesByRangeHiIsEqualToSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where rangeHi equals to DEFAULT_RANGE_HI
        defaultCsPostalCodeShouldBeFound("rangeHi.equals=" + DEFAULT_RANGE_HI);

        // Get all the csPostalCodeList where rangeHi equals to UPDATED_RANGE_HI
        defaultCsPostalCodeShouldNotBeFound("rangeHi.equals=" + UPDATED_RANGE_HI);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByRangeHiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where rangeHi not equals to DEFAULT_RANGE_HI
        defaultCsPostalCodeShouldNotBeFound("rangeHi.notEquals=" + DEFAULT_RANGE_HI);

        // Get all the csPostalCodeList where rangeHi not equals to UPDATED_RANGE_HI
        defaultCsPostalCodeShouldBeFound("rangeHi.notEquals=" + UPDATED_RANGE_HI);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByRangeHiIsInShouldWork() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where rangeHi in DEFAULT_RANGE_HI or UPDATED_RANGE_HI
        defaultCsPostalCodeShouldBeFound("rangeHi.in=" + DEFAULT_RANGE_HI + "," + UPDATED_RANGE_HI);

        // Get all the csPostalCodeList where rangeHi equals to UPDATED_RANGE_HI
        defaultCsPostalCodeShouldNotBeFound("rangeHi.in=" + UPDATED_RANGE_HI);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByRangeHiIsNullOrNotNull() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where rangeHi is not null
        defaultCsPostalCodeShouldBeFound("rangeHi.specified=true");

        // Get all the csPostalCodeList where rangeHi is null
        defaultCsPostalCodeShouldNotBeFound("rangeHi.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByRangeHiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where rangeHi is greater than or equal to DEFAULT_RANGE_HI
        defaultCsPostalCodeShouldBeFound("rangeHi.greaterThanOrEqual=" + DEFAULT_RANGE_HI);

        // Get all the csPostalCodeList where rangeHi is greater than or equal to UPDATED_RANGE_HI
        defaultCsPostalCodeShouldNotBeFound("rangeHi.greaterThanOrEqual=" + UPDATED_RANGE_HI);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByRangeHiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where rangeHi is less than or equal to DEFAULT_RANGE_HI
        defaultCsPostalCodeShouldBeFound("rangeHi.lessThanOrEqual=" + DEFAULT_RANGE_HI);

        // Get all the csPostalCodeList where rangeHi is less than or equal to SMALLER_RANGE_HI
        defaultCsPostalCodeShouldNotBeFound("rangeHi.lessThanOrEqual=" + SMALLER_RANGE_HI);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByRangeHiIsLessThanSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where rangeHi is less than DEFAULT_RANGE_HI
        defaultCsPostalCodeShouldNotBeFound("rangeHi.lessThan=" + DEFAULT_RANGE_HI);

        // Get all the csPostalCodeList where rangeHi is less than UPDATED_RANGE_HI
        defaultCsPostalCodeShouldBeFound("rangeHi.lessThan=" + UPDATED_RANGE_HI);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByRangeHiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where rangeHi is greater than DEFAULT_RANGE_HI
        defaultCsPostalCodeShouldNotBeFound("rangeHi.greaterThan=" + DEFAULT_RANGE_HI);

        // Get all the csPostalCodeList where rangeHi is greater than SMALLER_RANGE_HI
        defaultCsPostalCodeShouldBeFound("rangeHi.greaterThan=" + SMALLER_RANGE_HI);
    }


    @Test
    @Transactional
    public void getAllCsPostalCodesByDistrictIsEqualToSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where district equals to DEFAULT_DISTRICT
        defaultCsPostalCodeShouldBeFound("district.equals=" + DEFAULT_DISTRICT);

        // Get all the csPostalCodeList where district equals to UPDATED_DISTRICT
        defaultCsPostalCodeShouldNotBeFound("district.equals=" + UPDATED_DISTRICT);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByDistrictIsNotEqualToSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where district not equals to DEFAULT_DISTRICT
        defaultCsPostalCodeShouldNotBeFound("district.notEquals=" + DEFAULT_DISTRICT);

        // Get all the csPostalCodeList where district not equals to UPDATED_DISTRICT
        defaultCsPostalCodeShouldBeFound("district.notEquals=" + UPDATED_DISTRICT);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByDistrictIsInShouldWork() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where district in DEFAULT_DISTRICT or UPDATED_DISTRICT
        defaultCsPostalCodeShouldBeFound("district.in=" + DEFAULT_DISTRICT + "," + UPDATED_DISTRICT);

        // Get all the csPostalCodeList where district equals to UPDATED_DISTRICT
        defaultCsPostalCodeShouldNotBeFound("district.in=" + UPDATED_DISTRICT);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByDistrictIsNullOrNotNull() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where district is not null
        defaultCsPostalCodeShouldBeFound("district.specified=true");

        // Get all the csPostalCodeList where district is null
        defaultCsPostalCodeShouldNotBeFound("district.specified=false");
    }
                @Test
    @Transactional
    public void getAllCsPostalCodesByDistrictContainsSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where district contains DEFAULT_DISTRICT
        defaultCsPostalCodeShouldBeFound("district.contains=" + DEFAULT_DISTRICT);

        // Get all the csPostalCodeList where district contains UPDATED_DISTRICT
        defaultCsPostalCodeShouldNotBeFound("district.contains=" + UPDATED_DISTRICT);
    }

    @Test
    @Transactional
    public void getAllCsPostalCodesByDistrictNotContainsSomething() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        // Get all the csPostalCodeList where district does not contain DEFAULT_DISTRICT
        defaultCsPostalCodeShouldNotBeFound("district.doesNotContain=" + DEFAULT_DISTRICT);

        // Get all the csPostalCodeList where district does not contain UPDATED_DISTRICT
        defaultCsPostalCodeShouldBeFound("district.doesNotContain=" + UPDATED_DISTRICT);
    }


    @Test
    @Transactional
    public void getAllCsPostalCodesByValidityIsEqualToSomething() throws Exception {
        // Get already existing entity
        Validity validity = csPostalCode.getValidity();
        csPostalCodeRepository.saveAndFlush(csPostalCode);
        Long validityId = validity.getId();

        // Get all the csPostalCodeList where validity equals to validityId
        defaultCsPostalCodeShouldBeFound("validityId.equals=" + validityId);

        // Get all the csPostalCodeList where validity equals to validityId + 1
        defaultCsPostalCodeShouldNotBeFound("validityId.equals=" + (validityId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCsPostalCodeShouldBeFound(String filter) throws Exception {
        restCsPostalCodeMockMvc.perform(get("/api/cs-postal-codes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(csPostalCode.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].settlement").value(hasItem(DEFAULT_SETTLEMENT)))
            .andExpect(jsonPath("$.[*].part").value(hasItem(DEFAULT_PART)))
            .andExpect(jsonPath("$.[*].street").value(hasItem(DEFAULT_STREET)))
            .andExpect(jsonPath("$.[*].kind").value(hasItem(DEFAULT_KIND)))
            .andExpect(jsonPath("$.[*].rangeType").value(hasItem(DEFAULT_RANGE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].rangeLo").value(hasItem(DEFAULT_RANGE_LO)))
            .andExpect(jsonPath("$.[*].rangeHi").value(hasItem(DEFAULT_RANGE_HI)))
            .andExpect(jsonPath("$.[*].district").value(hasItem(DEFAULT_DISTRICT)));

        // Check, that the count call also returns 1
        restCsPostalCodeMockMvc.perform(get("/api/cs-postal-codes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCsPostalCodeShouldNotBeFound(String filter) throws Exception {
        restCsPostalCodeMockMvc.perform(get("/api/cs-postal-codes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCsPostalCodeMockMvc.perform(get("/api/cs-postal-codes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingCsPostalCode() throws Exception {
        // Get the csPostalCode
        restCsPostalCodeMockMvc.perform(get("/api/cs-postal-codes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCsPostalCode() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        int databaseSizeBeforeUpdate = csPostalCodeRepository.findAll().size();

        // Update the csPostalCode
        CsPostalCode updatedCsPostalCode = csPostalCodeRepository.findById(csPostalCode.getId()).get();
        // Disconnect from session so that the updates on updatedCsPostalCode are not directly saved in db
        em.detach(updatedCsPostalCode);
        updatedCsPostalCode
            .code(UPDATED_CODE)
            .settlement(UPDATED_SETTLEMENT)
            .part(UPDATED_PART)
            .street(UPDATED_STREET)
            .kind(UPDATED_KIND)
            .rangeType(UPDATED_RANGE_TYPE)
            .rangeLo(UPDATED_RANGE_LO)
            .rangeHi(UPDATED_RANGE_HI)
            .district(UPDATED_DISTRICT);
        CsPostalCodeDTO csPostalCodeDTO = csPostalCodeMapper.toDto(updatedCsPostalCode);

        restCsPostalCodeMockMvc.perform(put("/api/cs-postal-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csPostalCodeDTO)))
            .andExpect(status().isOk());

        // Validate the CsPostalCode in the database
        List<CsPostalCode> csPostalCodeList = csPostalCodeRepository.findAll();
        assertThat(csPostalCodeList).hasSize(databaseSizeBeforeUpdate);
        CsPostalCode testCsPostalCode = csPostalCodeList.get(csPostalCodeList.size() - 1);
        assertThat(testCsPostalCode.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testCsPostalCode.getSettlement()).isEqualTo(UPDATED_SETTLEMENT);
        assertThat(testCsPostalCode.getPart()).isEqualTo(UPDATED_PART);
        assertThat(testCsPostalCode.getStreet()).isEqualTo(UPDATED_STREET);
        assertThat(testCsPostalCode.getKind()).isEqualTo(UPDATED_KIND);
        assertThat(testCsPostalCode.getRangeType()).isEqualTo(UPDATED_RANGE_TYPE);
        assertThat(testCsPostalCode.getRangeLo()).isEqualTo(UPDATED_RANGE_LO);
        assertThat(testCsPostalCode.getRangeHi()).isEqualTo(UPDATED_RANGE_HI);
        assertThat(testCsPostalCode.getDistrict()).isEqualTo(UPDATED_DISTRICT);
    }

    @Test
    @Transactional
    public void updateNonExistingCsPostalCode() throws Exception {
        int databaseSizeBeforeUpdate = csPostalCodeRepository.findAll().size();

        // Create the CsPostalCode
        CsPostalCodeDTO csPostalCodeDTO = csPostalCodeMapper.toDto(csPostalCode);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCsPostalCodeMockMvc.perform(put("/api/cs-postal-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csPostalCodeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CsPostalCode in the database
        List<CsPostalCode> csPostalCodeList = csPostalCodeRepository.findAll();
        assertThat(csPostalCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCsPostalCode() throws Exception {
        // Initialize the database
        csPostalCodeRepository.saveAndFlush(csPostalCode);

        int databaseSizeBeforeDelete = csPostalCodeRepository.findAll().size();

        // Delete the csPostalCode
        restCsPostalCodeMockMvc.perform(delete("/api/cs-postal-codes/{id}", csPostalCode.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CsPostalCode> csPostalCodeList = csPostalCodeRepository.findAll();
        assertThat(csPostalCodeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
