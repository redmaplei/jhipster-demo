package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Round;
import com.mycompany.myapp.repository.RoundRepository;
import com.mycompany.myapp.service.RoundService;
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
 * REST controller for managing Round.
 */
@RestController
@RequestMapping("/api")
public class RoundResource {

    private final Logger log = LoggerFactory.getLogger(RoundResource.class);

    private static final String ENTITY_NAME = "round";

    private final RoundRepository roundRepository;

    @Autowired
    RoundService roundService;

    public RoundResource(RoundRepository roundRepository) {
        this.roundRepository = roundRepository;
    }

    /**
     * POST  /rounds : Create a new round.
     *
     * @param round the round to create
     * @return the ResponseEntity with status 201 (Created) and with body the new round, or with status 400 (Bad Request) if the round has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rounds")
    @Timed
    public ResponseEntity<Round> createRound(@RequestBody Round round) throws URISyntaxException {
        log.debug("REST request to save Round : {}", round);
        if (round.getId() != null) {
            throw new BadRequestAlertException("A new round cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Round result = roundRepository.save(round);
        return ResponseEntity.created(new URI("/api/rounds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PostMapping("/calculatePi")
    @Timed
    public ResponseEntity<Round> calculatePi(@RequestParam double radius,@RequestParam double n) {
        //double radius = 2;
        log.debug("calculatePi to radius : {}",radius);

        Round round;

        round = roundService.calculatePi(radius,n);

        return ResponseEntity.ok()
            .body(round);
    }

    /**
     * PUT  /rounds : Updates an existing round.
     *
     * -------------------------------------------------------------------------------
     * 更新的时候根据实体的ID，来调用Repository的save方法 save实体  ID同，就可以完成更新操作
     * -------------------------------------------------------------------------------
     *
     * @param round the round to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated round,
     * or with status 400 (Bad Request) if the round is not valid,
     * or with status 500 (Internal Server Error) if the round couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rounds")
    @Timed
    public ResponseEntity<Round> updateRound(@RequestBody Round round) throws URISyntaxException {
        log.debug("REST request to update Round : {}", round);
        if (round.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Round result = roundRepository.save(round);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, round.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rounds : get all the rounds.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of rounds in body
     */
    @GetMapping("/rounds")
    @Timed
    public List<Round> getAllRounds() {
        log.debug("REST request to get all Rounds");
        return roundRepository.findAll();
    }

    /**
     * GET  /rounds/:id : get the "id" round.
     *
     * @param id the id of the round to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the round, or with status 404 (Not Found)
     */
    @GetMapping("/rounds/{id}")
    @Timed
    public ResponseEntity<Round> getRound(@PathVariable Long id) {
        log.debug("REST request to get Round : {}", id);
        Optional<Round> round = roundRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(round);
    }

    /**
     * DELETE  /rounds/:id : delete the "id" round.
     *
     * @param id the id of the round to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rounds/{id}")
    @Timed
    public ResponseEntity<Void> deleteRound(@PathVariable Long id) {
        log.debug("REST request to delete Round : {}", id);

        roundRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
