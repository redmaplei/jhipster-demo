package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Steward;
import com.mycompany.myapp.repository.StewardRepository;
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
 * REST controller for managing Steward.
 */
@RestController
@RequestMapping("/api")
public class StewardResource {

    private final Logger log = LoggerFactory.getLogger(StewardResource.class);

    private static final String ENTITY_NAME = "steward";

    private final StewardRepository stewardRepository;

    public StewardResource(StewardRepository stewardRepository) {
        this.stewardRepository = stewardRepository;
    }

    /**
     * POST  /stewards : Create a new steward.
     *
     * @param steward the steward to create
     * @return the ResponseEntity with status 201 (Created) and with body the new steward, or with status 400 (Bad Request) if the steward has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/stewards")
    @Timed
    public ResponseEntity<Steward> createSteward(@RequestBody Steward steward) throws URISyntaxException {
        log.debug("REST request to save Steward : {}", steward);
        if (steward.getId() != null) {
            throw new BadRequestAlertException("A new steward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Steward result = stewardRepository.save(steward);
        return ResponseEntity.created(new URI("/api/stewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /stewards : Updates an existing steward.
     *
     * @param steward the steward to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated steward,
     * or with status 400 (Bad Request) if the steward is not valid,
     * or with status 500 (Internal Server Error) if the steward couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/stewards")
    @Timed
    public ResponseEntity<Steward> updateSteward(@RequestBody Steward steward) throws URISyntaxException {
        log.debug("REST request to update Steward : {}", steward);
        if (steward.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Steward result = stewardRepository.save(steward);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, steward.getId().toString()))
            .body(result);
    }

    /**
     * GET  /stewards : get all the stewards.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of stewards in body
     */
    @GetMapping("/stewards")
    @Timed
    public List<Steward> getAllStewards() {
        log.debug("REST request to get all Stewards");
        return stewardRepository.findAll();
    }

    /**
     * GET  /stewards/:id : get the "id" steward.
     *
     * @param id the id of the steward to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the steward, or with status 404 (Not Found)
     */
    @GetMapping("/stewards/{id}")
    @Timed
    public ResponseEntity<Steward> getSteward(@PathVariable Long id) {
        log.debug("REST request to get Steward : {}", id);
        Optional<Steward> steward = stewardRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(steward);
    }

    /**
     * DELETE  /stewards/:id : delete the "id" steward.
     *
     * @param id the id of the steward to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/stewards/{id}")
    @Timed
    public ResponseEntity<Void> deleteSteward(@PathVariable Long id) {
        log.debug("REST request to delete Steward : {}", id);

        stewardRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
