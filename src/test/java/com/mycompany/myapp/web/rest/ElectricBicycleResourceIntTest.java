package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Demo3App;

import com.mycompany.myapp.domain.ElectricBicycle;
import com.mycompany.myapp.repository.ElectricBicycleRepository;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ElectricBicycleResource REST controller.
 *
 * @see ElectricBicycleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo3App.class)
public class ElectricBicycleResourceIntTest {

    private static final String DEFAULT_BICYCLE_ID = "AAAAAAAAAA";
    private static final String UPDATED_BICYCLE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BICYCLE_MODEL = "AAAAAAAAAA";
    private static final String UPDATED_BICYCLE_MODEL = "BBBBBBBBBB";

    private static final String DEFAULT_BICYCLE_INFO = "AAAAAAAAAA";
    private static final String UPDATED_BICYCLE_INFO = "BBBBBBBBBB";

    @Autowired
    private ElectricBicycleRepository electricBicycleRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restElectricBicycleMockMvc;

    private ElectricBicycle electricBicycle;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ElectricBicycleResource electricBicycleResource = new ElectricBicycleResource(electricBicycleRepository);
        this.restElectricBicycleMockMvc = MockMvcBuilders.standaloneSetup(electricBicycleResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ElectricBicycle createEntity(EntityManager em) {
        ElectricBicycle electricBicycle = new ElectricBicycle()
            .bicycleID(DEFAULT_BICYCLE_ID)
            .bicycleModel(DEFAULT_BICYCLE_MODEL)
            .bicycleInfo(DEFAULT_BICYCLE_INFO);
        return electricBicycle;
    }

    @Before
    public void initTest() {
        electricBicycle = createEntity(em);
    }

    @Test
    @Transactional
    public void createElectricBicycle() throws Exception {
        int databaseSizeBeforeCreate = electricBicycleRepository.findAll().size();

        // Create the ElectricBicycle
        restElectricBicycleMockMvc.perform(post("/api/electric-bicycles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(electricBicycle)))
            .andExpect(status().isCreated());

        // Validate the ElectricBicycle in the database
        List<ElectricBicycle> electricBicycleList = electricBicycleRepository.findAll();
        assertThat(electricBicycleList).hasSize(databaseSizeBeforeCreate + 1);
        ElectricBicycle testElectricBicycle = electricBicycleList.get(electricBicycleList.size() - 1);
        assertThat(testElectricBicycle.getBicycleID()).isEqualTo(DEFAULT_BICYCLE_ID);
        assertThat(testElectricBicycle.getBicycleModel()).isEqualTo(DEFAULT_BICYCLE_MODEL);
        assertThat(testElectricBicycle.getBicycleInfo()).isEqualTo(DEFAULT_BICYCLE_INFO);
    }

    @Test
    @Transactional
    public void createElectricBicycleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = electricBicycleRepository.findAll().size();

        // Create the ElectricBicycle with an existing ID
        electricBicycle.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restElectricBicycleMockMvc.perform(post("/api/electric-bicycles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(electricBicycle)))
            .andExpect(status().isBadRequest());

        // Validate the ElectricBicycle in the database
        List<ElectricBicycle> electricBicycleList = electricBicycleRepository.findAll();
        assertThat(electricBicycleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllElectricBicycles() throws Exception {
        // Initialize the database
        electricBicycleRepository.saveAndFlush(electricBicycle);

        // Get all the electricBicycleList
        restElectricBicycleMockMvc.perform(get("/api/electric-bicycles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(electricBicycle.getId().intValue())))
            .andExpect(jsonPath("$.[*].bicycleID").value(hasItem(DEFAULT_BICYCLE_ID.toString())))
            .andExpect(jsonPath("$.[*].bicycleModel").value(hasItem(DEFAULT_BICYCLE_MODEL.toString())))
            .andExpect(jsonPath("$.[*].bicycleInfo").value(hasItem(DEFAULT_BICYCLE_INFO.toString())));
    }
    

    @Test
    @Transactional
    public void getElectricBicycle() throws Exception {
        // Initialize the database
        electricBicycleRepository.saveAndFlush(electricBicycle);

        // Get the electricBicycle
        restElectricBicycleMockMvc.perform(get("/api/electric-bicycles/{id}", electricBicycle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(electricBicycle.getId().intValue()))
            .andExpect(jsonPath("$.bicycleID").value(DEFAULT_BICYCLE_ID.toString()))
            .andExpect(jsonPath("$.bicycleModel").value(DEFAULT_BICYCLE_MODEL.toString()))
            .andExpect(jsonPath("$.bicycleInfo").value(DEFAULT_BICYCLE_INFO.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingElectricBicycle() throws Exception {
        // Get the electricBicycle
        restElectricBicycleMockMvc.perform(get("/api/electric-bicycles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateElectricBicycle() throws Exception {
        // Initialize the database
        electricBicycleRepository.saveAndFlush(electricBicycle);

        int databaseSizeBeforeUpdate = electricBicycleRepository.findAll().size();

        // Update the electricBicycle
        ElectricBicycle updatedElectricBicycle = electricBicycleRepository.findById(electricBicycle.getId()).get();
        // Disconnect from session so that the updates on updatedElectricBicycle are not directly saved in db
        em.detach(updatedElectricBicycle);
        updatedElectricBicycle
            .bicycleID(UPDATED_BICYCLE_ID)
            .bicycleModel(UPDATED_BICYCLE_MODEL)
            .bicycleInfo(UPDATED_BICYCLE_INFO);

        restElectricBicycleMockMvc.perform(put("/api/electric-bicycles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedElectricBicycle)))
            .andExpect(status().isOk());

        // Validate the ElectricBicycle in the database
        List<ElectricBicycle> electricBicycleList = electricBicycleRepository.findAll();
        assertThat(electricBicycleList).hasSize(databaseSizeBeforeUpdate);
        ElectricBicycle testElectricBicycle = electricBicycleList.get(electricBicycleList.size() - 1);
        assertThat(testElectricBicycle.getBicycleID()).isEqualTo(UPDATED_BICYCLE_ID);
        assertThat(testElectricBicycle.getBicycleModel()).isEqualTo(UPDATED_BICYCLE_MODEL);
        assertThat(testElectricBicycle.getBicycleInfo()).isEqualTo(UPDATED_BICYCLE_INFO);
    }

    @Test
    @Transactional
    public void updateNonExistingElectricBicycle() throws Exception {
        int databaseSizeBeforeUpdate = electricBicycleRepository.findAll().size();

        // Create the ElectricBicycle

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restElectricBicycleMockMvc.perform(put("/api/electric-bicycles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(electricBicycle)))
            .andExpect(status().isBadRequest());

        // Validate the ElectricBicycle in the database
        List<ElectricBicycle> electricBicycleList = electricBicycleRepository.findAll();
        assertThat(electricBicycleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteElectricBicycle() throws Exception {
        // Initialize the database
        electricBicycleRepository.saveAndFlush(electricBicycle);

        int databaseSizeBeforeDelete = electricBicycleRepository.findAll().size();

        // Get the electricBicycle
        restElectricBicycleMockMvc.perform(delete("/api/electric-bicycles/{id}", electricBicycle.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ElectricBicycle> electricBicycleList = electricBicycleRepository.findAll();
        assertThat(electricBicycleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElectricBicycle.class);
        ElectricBicycle electricBicycle1 = new ElectricBicycle();
        electricBicycle1.setId(1L);
        ElectricBicycle electricBicycle2 = new ElectricBicycle();
        electricBicycle2.setId(electricBicycle1.getId());
        assertThat(electricBicycle1).isEqualTo(electricBicycle2);
        electricBicycle2.setId(2L);
        assertThat(electricBicycle1).isNotEqualTo(electricBicycle2);
        electricBicycle1.setId(null);
        assertThat(electricBicycle1).isNotEqualTo(electricBicycle2);
    }
}
