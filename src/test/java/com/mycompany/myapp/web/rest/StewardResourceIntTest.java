package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Demo3App;

import com.mycompany.myapp.domain.Steward;
import com.mycompany.myapp.repository.StewardRepository;
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
 * Test class for the StewardResource REST controller.
 *
 * @see StewardResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo3App.class)
public class StewardResourceIntTest {

    private static final String DEFAULT_STEWARD_ID = "AAAAAAAAAA";
    private static final String UPDATED_STEWARD_ID = "BBBBBBBBBB";

    private static final String DEFAULT_STEWARD_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STEWARD_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STEWARD_INFO = "AAAAAAAAAA";
    private static final String UPDATED_STEWARD_INFO = "BBBBBBBBBB";

    @Autowired
    private StewardRepository stewardRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStewardMockMvc;

    private Steward steward;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StewardResource stewardResource = new StewardResource(stewardRepository);
        this.restStewardMockMvc = MockMvcBuilders.standaloneSetup(stewardResource)
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
    public static Steward createEntity(EntityManager em) {
        Steward steward = new Steward()
            .stewardID(DEFAULT_STEWARD_ID)
            .stewardName(DEFAULT_STEWARD_NAME)
            .stewardInfo(DEFAULT_STEWARD_INFO);
        return steward;
    }

    @Before
    public void initTest() {
        steward = createEntity(em);
    }

    @Test
    @Transactional
    public void createSteward() throws Exception {
        int databaseSizeBeforeCreate = stewardRepository.findAll().size();

        // Create the Steward
        restStewardMockMvc.perform(post("/api/stewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(steward)))
            .andExpect(status().isCreated());

        // Validate the Steward in the database
        List<Steward> stewardList = stewardRepository.findAll();
        assertThat(stewardList).hasSize(databaseSizeBeforeCreate + 1);
        Steward testSteward = stewardList.get(stewardList.size() - 1);
        assertThat(testSteward.getStewardID()).isEqualTo(DEFAULT_STEWARD_ID);
        assertThat(testSteward.getStewardName()).isEqualTo(DEFAULT_STEWARD_NAME);
        assertThat(testSteward.getStewardInfo()).isEqualTo(DEFAULT_STEWARD_INFO);
    }

    @Test
    @Transactional
    public void createStewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stewardRepository.findAll().size();

        // Create the Steward with an existing ID
        steward.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStewardMockMvc.perform(post("/api/stewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(steward)))
            .andExpect(status().isBadRequest());

        // Validate the Steward in the database
        List<Steward> stewardList = stewardRepository.findAll();
        assertThat(stewardList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllStewards() throws Exception {
        // Initialize the database
        stewardRepository.saveAndFlush(steward);

        // Get all the stewardList
        restStewardMockMvc.perform(get("/api/stewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(steward.getId().intValue())))
            .andExpect(jsonPath("$.[*].stewardID").value(hasItem(DEFAULT_STEWARD_ID.toString())))
            .andExpect(jsonPath("$.[*].stewardName").value(hasItem(DEFAULT_STEWARD_NAME.toString())))
            .andExpect(jsonPath("$.[*].stewardInfo").value(hasItem(DEFAULT_STEWARD_INFO.toString())));
    }
    

    @Test
    @Transactional
    public void getSteward() throws Exception {
        // Initialize the database
        stewardRepository.saveAndFlush(steward);

        // Get the steward
        restStewardMockMvc.perform(get("/api/stewards/{id}", steward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(steward.getId().intValue()))
            .andExpect(jsonPath("$.stewardID").value(DEFAULT_STEWARD_ID.toString()))
            .andExpect(jsonPath("$.stewardName").value(DEFAULT_STEWARD_NAME.toString()))
            .andExpect(jsonPath("$.stewardInfo").value(DEFAULT_STEWARD_INFO.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSteward() throws Exception {
        // Get the steward
        restStewardMockMvc.perform(get("/api/stewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSteward() throws Exception {
        // Initialize the database
        stewardRepository.saveAndFlush(steward);

        int databaseSizeBeforeUpdate = stewardRepository.findAll().size();

        // Update the steward
        Steward updatedSteward = stewardRepository.findById(steward.getId()).get();
        // Disconnect from session so that the updates on updatedSteward are not directly saved in db
        em.detach(updatedSteward);
        updatedSteward
            .stewardID(UPDATED_STEWARD_ID)
            .stewardName(UPDATED_STEWARD_NAME)
            .stewardInfo(UPDATED_STEWARD_INFO);

        restStewardMockMvc.perform(put("/api/stewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSteward)))
            .andExpect(status().isOk());

        // Validate the Steward in the database
        List<Steward> stewardList = stewardRepository.findAll();
        assertThat(stewardList).hasSize(databaseSizeBeforeUpdate);
        Steward testSteward = stewardList.get(stewardList.size() - 1);
        assertThat(testSteward.getStewardID()).isEqualTo(UPDATED_STEWARD_ID);
        assertThat(testSteward.getStewardName()).isEqualTo(UPDATED_STEWARD_NAME);
        assertThat(testSteward.getStewardInfo()).isEqualTo(UPDATED_STEWARD_INFO);
    }

    @Test
    @Transactional
    public void updateNonExistingSteward() throws Exception {
        int databaseSizeBeforeUpdate = stewardRepository.findAll().size();

        // Create the Steward

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restStewardMockMvc.perform(put("/api/stewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(steward)))
            .andExpect(status().isBadRequest());

        // Validate the Steward in the database
        List<Steward> stewardList = stewardRepository.findAll();
        assertThat(stewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSteward() throws Exception {
        // Initialize the database
        stewardRepository.saveAndFlush(steward);

        int databaseSizeBeforeDelete = stewardRepository.findAll().size();

        // Get the steward
        restStewardMockMvc.perform(delete("/api/stewards/{id}", steward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Steward> stewardList = stewardRepository.findAll();
        assertThat(stewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Steward.class);
        Steward steward1 = new Steward();
        steward1.setId(1L);
        Steward steward2 = new Steward();
        steward2.setId(steward1.getId());
        assertThat(steward1).isEqualTo(steward2);
        steward2.setId(2L);
        assertThat(steward1).isNotEqualTo(steward2);
        steward1.setId(null);
        assertThat(steward1).isNotEqualTo(steward2);
    }
}
