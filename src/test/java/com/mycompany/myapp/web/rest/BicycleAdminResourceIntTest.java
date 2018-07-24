package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Demo3App;

import com.mycompany.myapp.domain.BicycleAdmin;
import com.mycompany.myapp.repository.BicycleAdminRepository;
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
 * Test class for the BicycleAdminResource REST controller.
 *
 * @see BicycleAdminResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo3App.class)
public class BicycleAdminResourceIntTest {

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_PHONENUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONENUMBER = "BBBBBBBBBB";

    @Autowired
    private BicycleAdminRepository bicycleAdminRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBicycleAdminMockMvc;

    private BicycleAdmin bicycleAdmin;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BicycleAdminResource bicycleAdminResource = new BicycleAdminResource(bicycleAdminRepository);
        this.restBicycleAdminMockMvc = MockMvcBuilders.standaloneSetup(bicycleAdminResource)
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
    public static BicycleAdmin createEntity(EntityManager em) {
        BicycleAdmin bicycleAdmin = new BicycleAdmin()
            .username(DEFAULT_USERNAME)
            .password(DEFAULT_PASSWORD)
            .phonenumber(DEFAULT_PHONENUMBER);
        return bicycleAdmin;
    }

    @Before
    public void initTest() {
        bicycleAdmin = createEntity(em);
    }

    @Test
    @Transactional
    public void createBicycleAdmin() throws Exception {
        int databaseSizeBeforeCreate = bicycleAdminRepository.findAll().size();

        // Create the BicycleAdmin
        restBicycleAdminMockMvc.perform(post("/api/bicycle-admins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bicycleAdmin)))
            .andExpect(status().isCreated());

        // Validate the BicycleAdmin in the database
        List<BicycleAdmin> bicycleAdminList = bicycleAdminRepository.findAll();
        assertThat(bicycleAdminList).hasSize(databaseSizeBeforeCreate + 1);
        BicycleAdmin testBicycleAdmin = bicycleAdminList.get(bicycleAdminList.size() - 1);
        assertThat(testBicycleAdmin.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testBicycleAdmin.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testBicycleAdmin.getPhonenumber()).isEqualTo(DEFAULT_PHONENUMBER);
    }

    @Test
    @Transactional
    public void createBicycleAdminWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bicycleAdminRepository.findAll().size();

        // Create the BicycleAdmin with an existing ID
        bicycleAdmin.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBicycleAdminMockMvc.perform(post("/api/bicycle-admins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bicycleAdmin)))
            .andExpect(status().isBadRequest());

        // Validate the BicycleAdmin in the database
        List<BicycleAdmin> bicycleAdminList = bicycleAdminRepository.findAll();
        assertThat(bicycleAdminList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBicycleAdmins() throws Exception {
        // Initialize the database
        bicycleAdminRepository.saveAndFlush(bicycleAdmin);

        // Get all the bicycleAdminList
        restBicycleAdminMockMvc.perform(get("/api/bicycle-admins?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bicycleAdmin.getId().intValue())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME.toString())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].phonenumber").value(hasItem(DEFAULT_PHONENUMBER.toString())));
    }
    

    @Test
    @Transactional
    public void getBicycleAdmin() throws Exception {
        // Initialize the database
        bicycleAdminRepository.saveAndFlush(bicycleAdmin);

        // Get the bicycleAdmin
        restBicycleAdminMockMvc.perform(get("/api/bicycle-admins/{id}", bicycleAdmin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bicycleAdmin.getId().intValue()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME.toString()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()))
            .andExpect(jsonPath("$.phonenumber").value(DEFAULT_PHONENUMBER.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingBicycleAdmin() throws Exception {
        // Get the bicycleAdmin
        restBicycleAdminMockMvc.perform(get("/api/bicycle-admins/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBicycleAdmin() throws Exception {
        // Initialize the database
        bicycleAdminRepository.saveAndFlush(bicycleAdmin);

        int databaseSizeBeforeUpdate = bicycleAdminRepository.findAll().size();

        // Update the bicycleAdmin
        BicycleAdmin updatedBicycleAdmin = bicycleAdminRepository.findById(bicycleAdmin.getId()).get();
        // Disconnect from session so that the updates on updatedBicycleAdmin are not directly saved in db
        em.detach(updatedBicycleAdmin);
        updatedBicycleAdmin
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .phonenumber(UPDATED_PHONENUMBER);

        restBicycleAdminMockMvc.perform(put("/api/bicycle-admins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBicycleAdmin)))
            .andExpect(status().isOk());

        // Validate the BicycleAdmin in the database
        List<BicycleAdmin> bicycleAdminList = bicycleAdminRepository.findAll();
        assertThat(bicycleAdminList).hasSize(databaseSizeBeforeUpdate);
        BicycleAdmin testBicycleAdmin = bicycleAdminList.get(bicycleAdminList.size() - 1);
        assertThat(testBicycleAdmin.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testBicycleAdmin.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testBicycleAdmin.getPhonenumber()).isEqualTo(UPDATED_PHONENUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingBicycleAdmin() throws Exception {
        int databaseSizeBeforeUpdate = bicycleAdminRepository.findAll().size();

        // Create the BicycleAdmin

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBicycleAdminMockMvc.perform(put("/api/bicycle-admins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bicycleAdmin)))
            .andExpect(status().isBadRequest());

        // Validate the BicycleAdmin in the database
        List<BicycleAdmin> bicycleAdminList = bicycleAdminRepository.findAll();
        assertThat(bicycleAdminList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBicycleAdmin() throws Exception {
        // Initialize the database
        bicycleAdminRepository.saveAndFlush(bicycleAdmin);

        int databaseSizeBeforeDelete = bicycleAdminRepository.findAll().size();

        // Get the bicycleAdmin
        restBicycleAdminMockMvc.perform(delete("/api/bicycle-admins/{id}", bicycleAdmin.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BicycleAdmin> bicycleAdminList = bicycleAdminRepository.findAll();
        assertThat(bicycleAdminList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BicycleAdmin.class);
        BicycleAdmin bicycleAdmin1 = new BicycleAdmin();
        bicycleAdmin1.setId(1L);
        BicycleAdmin bicycleAdmin2 = new BicycleAdmin();
        bicycleAdmin2.setId(bicycleAdmin1.getId());
        assertThat(bicycleAdmin1).isEqualTo(bicycleAdmin2);
        bicycleAdmin2.setId(2L);
        assertThat(bicycleAdmin1).isNotEqualTo(bicycleAdmin2);
        bicycleAdmin1.setId(null);
        assertThat(bicycleAdmin1).isNotEqualTo(bicycleAdmin2);
    }
}
