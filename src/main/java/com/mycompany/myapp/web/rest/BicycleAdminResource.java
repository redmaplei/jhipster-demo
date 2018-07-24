package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.BicycleAdmin;
import com.mycompany.myapp.repository.BicycleAdminRepository;
import com.mycompany.myapp.service.BicycleAdminService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing BicycleAdmin.
 */
@RestController
@RequestMapping("/api")
public class BicycleAdminResource {

    private final Logger log = LoggerFactory.getLogger(BicycleAdminResource.class);

    private static final String ENTITY_NAME = "bicycleAdmin";

    private final BicycleAdminRepository bicycleAdminRepository;

    public BicycleAdminResource(BicycleAdminRepository bicycleAdminRepository) {
        this.bicycleAdminRepository = bicycleAdminRepository;
    }

    /**
     * POST  /bicycle-admins : Create a new bicycleAdmin.
     *
     * @param bicycleAdmin the bicycleAdmin to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bicycleAdmin, or with status 400 (Bad Request) if the bicycleAdmin has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bicycle-admins")
    @Timed
    public ResponseEntity<BicycleAdmin> createBicycleAdmin(@RequestBody BicycleAdmin bicycleAdmin) throws URISyntaxException {
        log.debug("REST request to save BicycleAdmin : {}", bicycleAdmin);
        if (bicycleAdmin.getId() != null) {
            throw new BadRequestAlertException("A new bicycleAdmin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BicycleAdmin result = bicycleAdminRepository.save(bicycleAdmin);
        return ResponseEntity.created(new URI("/api/bicycle-admins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bicycle-admins : Updates an existing bicycleAdmin.
     *
     * @param bicycleAdmin the bicycleAdmin to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bicycleAdmin,
     * or with status 400 (Bad Request) if the bicycleAdmin is not valid,
     * or with status 500 (Internal Server Error) if the bicycleAdmin couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bicycle-admins")
    @Timed
    public ResponseEntity<BicycleAdmin> updateBicycleAdmin(@RequestBody BicycleAdmin bicycleAdmin) throws URISyntaxException {
        log.debug("REST request to update BicycleAdmin : {}", bicycleAdmin);
        if (bicycleAdmin.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BicycleAdmin result = bicycleAdminRepository.save(bicycleAdmin);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bicycleAdmin.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bicycle-admins : get all the bicycleAdmins.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of bicycleAdmins in body
     */
    @GetMapping("/bicycle-admins")
    @Timed
    public List<BicycleAdmin> getAllBicycleAdmins() {
        log.debug("REST request to get all BicycleAdmins");
        return bicycleAdminRepository.findAll();
    }

    /**
     * GET  /bicycle-admins/:id : get the "id" bicycleAdmin.
     *
     * @param id the id of the bicycleAdmin to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bicycleAdmin, or with status 404 (Not Found)
     */
    @GetMapping("/bicycle-admins/{id}")
    @Timed
    public ResponseEntity<BicycleAdmin> getBicycleAdmin(@PathVariable Long id) {
        log.debug("REST request to get BicycleAdmin : {}", id);
        Optional<BicycleAdmin> bicycleAdmin = bicycleAdminRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(bicycleAdmin);
    }

    /**
     * DELETE  /bicycle-admins/:id : delete the "id" bicycleAdmin.
     *
     * @param id the id of the bicycleAdmin to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bicycle-admins/{id}")
    @Timed
    public ResponseEntity<Void> deleteBicycleAdmin(@PathVariable Long id) {
        log.debug("REST request to delete BicycleAdmin : {}", id);

        bicycleAdminRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }


    @Autowired
    BicycleAdminService bicycleAdminService;

    @PostMapping("/bicycle-admin-login")
    @Timed
    public ResponseEntity<String> bicycleAdminLogin(@RequestParam String username,@RequestParam String password) {
        log.debug("bicycleAdminLogin : {}",username," ",password);

        String result = bicycleAdminService.login(username,password);
        return ResponseEntity.ok()
            .body(result);
    }


}
