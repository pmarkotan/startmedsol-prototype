package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.Employee;
import hu.paninform.startmedsol.domain.PersonalData;
import hu.paninform.startmedsol.repository.EmployeeRepository;

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

import hu.paninform.startmedsol.domain.enumeration.EmployeeType;
import hu.paninform.startmedsol.domain.enumeration.EesztLoginType;
/**
 * Integration tests for the {@link EmployeeResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EmployeeResourceIT {

    private static final EmployeeType DEFAULT_EMLPOYEE_TYPE = EmployeeType.DOCTOR;
    private static final EmployeeType UPDATED_EMLPOYEE_TYPE = EmployeeType.ADMIN;

    private static final String DEFAULT_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_EESZT_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_EESZT_IDENTIFIER = "BBBBBBBBBB";

    private static final EesztLoginType DEFAULT_EESZT_LOGIN_TYPE = EesztLoginType.PASSWORD;
    private static final EesztLoginType UPDATED_EESZT_LOGIN_TYPE = EesztLoginType.TOKEN;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEmployeeMockMvc;

    private Employee employee;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Employee createEntity(EntityManager em) {
        Employee employee = new Employee()
            .emlpoyeeType(DEFAULT_EMLPOYEE_TYPE)
            .identifier(DEFAULT_IDENTIFIER)
            .eesztIdentifier(DEFAULT_EESZT_IDENTIFIER)
            .eesztLoginType(DEFAULT_EESZT_LOGIN_TYPE);
        // Add required entity
        PersonalData personalData;
        if (TestUtil.findAll(em, PersonalData.class).isEmpty()) {
            personalData = PersonalDataResourceIT.createEntity(em);
            em.persist(personalData);
            em.flush();
        } else {
            personalData = TestUtil.findAll(em, PersonalData.class).get(0);
        }
        employee.setPersonalData(personalData);
        return employee;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Employee createUpdatedEntity(EntityManager em) {
        Employee employee = new Employee()
            .emlpoyeeType(UPDATED_EMLPOYEE_TYPE)
            .identifier(UPDATED_IDENTIFIER)
            .eesztIdentifier(UPDATED_EESZT_IDENTIFIER)
            .eesztLoginType(UPDATED_EESZT_LOGIN_TYPE);
        // Add required entity
        PersonalData personalData;
        if (TestUtil.findAll(em, PersonalData.class).isEmpty()) {
            personalData = PersonalDataResourceIT.createUpdatedEntity(em);
            em.persist(personalData);
            em.flush();
        } else {
            personalData = TestUtil.findAll(em, PersonalData.class).get(0);
        }
        employee.setPersonalData(personalData);
        return employee;
    }

    @BeforeEach
    public void initTest() {
        employee = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmployee() throws Exception {
        int databaseSizeBeforeCreate = employeeRepository.findAll().size();
        // Create the Employee
        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employee)))
            .andExpect(status().isCreated());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeCreate + 1);
        Employee testEmployee = employeeList.get(employeeList.size() - 1);
        assertThat(testEmployee.getEmlpoyeeType()).isEqualTo(DEFAULT_EMLPOYEE_TYPE);
        assertThat(testEmployee.getIdentifier()).isEqualTo(DEFAULT_IDENTIFIER);
        assertThat(testEmployee.getEesztIdentifier()).isEqualTo(DEFAULT_EESZT_IDENTIFIER);
        assertThat(testEmployee.getEesztLoginType()).isEqualTo(DEFAULT_EESZT_LOGIN_TYPE);
    }

    @Test
    @Transactional
    public void createEmployeeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = employeeRepository.findAll().size();

        // Create the Employee with an existing ID
        employee.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employee)))
            .andExpect(status().isBadRequest());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEmlpoyeeTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeeRepository.findAll().size();
        // set the field null
        employee.setEmlpoyeeType(null);

        // Create the Employee, which fails.


        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employee)))
            .andExpect(status().isBadRequest());

        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdentifierIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeeRepository.findAll().size();
        // set the field null
        employee.setIdentifier(null);

        // Create the Employee, which fails.


        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employee)))
            .andExpect(status().isBadRequest());

        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEesztLoginTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeeRepository.findAll().size();
        // set the field null
        employee.setEesztLoginType(null);

        // Create the Employee, which fails.


        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employee)))
            .andExpect(status().isBadRequest());

        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEmployees() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList
        restEmployeeMockMvc.perform(get("/api/employees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(employee.getId().intValue())))
            .andExpect(jsonPath("$.[*].emlpoyeeType").value(hasItem(DEFAULT_EMLPOYEE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].identifier").value(hasItem(DEFAULT_IDENTIFIER)))
            .andExpect(jsonPath("$.[*].eesztIdentifier").value(hasItem(DEFAULT_EESZT_IDENTIFIER)))
            .andExpect(jsonPath("$.[*].eesztLoginType").value(hasItem(DEFAULT_EESZT_LOGIN_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get the employee
        restEmployeeMockMvc.perform(get("/api/employees/{id}", employee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(employee.getId().intValue()))
            .andExpect(jsonPath("$.emlpoyeeType").value(DEFAULT_EMLPOYEE_TYPE.toString()))
            .andExpect(jsonPath("$.identifier").value(DEFAULT_IDENTIFIER))
            .andExpect(jsonPath("$.eesztIdentifier").value(DEFAULT_EESZT_IDENTIFIER))
            .andExpect(jsonPath("$.eesztLoginType").value(DEFAULT_EESZT_LOGIN_TYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingEmployee() throws Exception {
        // Get the employee
        restEmployeeMockMvc.perform(get("/api/employees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        int databaseSizeBeforeUpdate = employeeRepository.findAll().size();

        // Update the employee
        Employee updatedEmployee = employeeRepository.findById(employee.getId()).get();
        // Disconnect from session so that the updates on updatedEmployee are not directly saved in db
        em.detach(updatedEmployee);
        updatedEmployee
            .emlpoyeeType(UPDATED_EMLPOYEE_TYPE)
            .identifier(UPDATED_IDENTIFIER)
            .eesztIdentifier(UPDATED_EESZT_IDENTIFIER)
            .eesztLoginType(UPDATED_EESZT_LOGIN_TYPE);

        restEmployeeMockMvc.perform(put("/api/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedEmployee)))
            .andExpect(status().isOk());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate);
        Employee testEmployee = employeeList.get(employeeList.size() - 1);
        assertThat(testEmployee.getEmlpoyeeType()).isEqualTo(UPDATED_EMLPOYEE_TYPE);
        assertThat(testEmployee.getIdentifier()).isEqualTo(UPDATED_IDENTIFIER);
        assertThat(testEmployee.getEesztIdentifier()).isEqualTo(UPDATED_EESZT_IDENTIFIER);
        assertThat(testEmployee.getEesztLoginType()).isEqualTo(UPDATED_EESZT_LOGIN_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingEmployee() throws Exception {
        int databaseSizeBeforeUpdate = employeeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmployeeMockMvc.perform(put("/api/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employee)))
            .andExpect(status().isBadRequest());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        int databaseSizeBeforeDelete = employeeRepository.findAll().size();

        // Delete the employee
        restEmployeeMockMvc.perform(delete("/api/employees/{id}", employee.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
