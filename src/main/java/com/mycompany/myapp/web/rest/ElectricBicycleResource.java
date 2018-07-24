package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.ElectricBicycle;
import com.mycompany.myapp.repository.ElectricBicycleRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ElectricBicycle.
 */
@RestController
@RequestMapping("/api")
public class ElectricBicycleResource {

    private final Logger log = LoggerFactory.getLogger(ElectricBicycleResource.class);

    private static final String ENTITY_NAME = "electricBicycle";

    private final ElectricBicycleRepository electricBicycleRepository;

    public ElectricBicycleResource(ElectricBicycleRepository electricBicycleRepository) {
        this.electricBicycleRepository = electricBicycleRepository;
    }

    /**
     * POST  /electric-bicycles : Create a new electricBicycle.
     *
     * @param electricBicycle the electricBicycle to create
     * @return the ResponseEntity with status 201 (Created) and with body the new electricBicycle, or with status 400 (Bad Request) if the electricBicycle has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/electric-bicycles")
    @Timed
    public ResponseEntity<ElectricBicycle> createElectricBicycle(@RequestBody ElectricBicycle electricBicycle) throws URISyntaxException {
        log.debug("REST request to save ElectricBicycle : {}", electricBicycle);
        if (electricBicycle.getId() != null) {
            throw new BadRequestAlertException("A new electricBicycle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ElectricBicycle result = electricBicycleRepository.save(electricBicycle);
        return ResponseEntity.created(new URI("/api/electric-bicycles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /electric-bicycles : Updates an existing electricBicycle.
     *
     * @param electricBicycle the electricBicycle to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated electricBicycle,
     * or with status 400 (Bad Request) if the electricBicycle is not valid,
     * or with status 500 (Internal Server Error) if the electricBicycle couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/electric-bicycles")
    @Timed
    public ResponseEntity<ElectricBicycle> updateElectricBicycle(@RequestBody ElectricBicycle electricBicycle) throws URISyntaxException {
        log.debug("REST request to update ElectricBicycle : {}", electricBicycle);
        if (electricBicycle.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ElectricBicycle result = electricBicycleRepository.save(electricBicycle);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, electricBicycle.getId().toString()))
            .body(result);
    }

    /**
     * GET  /electric-bicycles : get all the electricBicycles.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of electricBicycles in body
     */
    @GetMapping("/electric-bicycles")
    @Timed
    public List<ElectricBicycle> getAllElectricBicycles() {
        log.debug("REST request to get all ElectricBicycles");
        return electricBicycleRepository.findAll();
    }

    /**
     * GET  /electric-bicycles/:id : get the "id" electricBicycle.
     *
     * @param id the id of the electricBicycle to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the electricBicycle, or with status 404 (Not Found)
     */
    @GetMapping("/electric-bicycles/{id}")
    @Timed
    public ResponseEntity<ElectricBicycle> getElectricBicycle(@PathVariable Long id) {
        log.debug("REST request to get ElectricBicycle : {}", id);
        Optional<ElectricBicycle> electricBicycle = electricBicycleRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(electricBicycle);
    }

    /**
     * DELETE  /electric-bicycles/:id : delete the "id" electricBicycle.
     *
     * @param id the id of the electricBicycle to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/electric-bicycles/{id}")
    @Timed
    public ResponseEntity<Void> deleteElectricBicycle(@PathVariable Long id) {
        log.debug("REST request to delete ElectricBicycle : {}", id);

        electricBicycleRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
