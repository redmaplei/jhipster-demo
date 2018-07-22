package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Round;
import com.mycompany.myapp.domain.RoundC;
import com.mycompany.myapp.service.RoundCService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Create by wys on 2018/7/22
 */
@RestController
@RequestMapping("/api")
public class RoundCResource {

    private final Logger log = LoggerFactory.getLogger(RoundResource.class);

    @Autowired
    RoundCService roundCService;

    @PostMapping("/calculatePiC")
    @Timed
    public ResponseEntity<RoundC> calculatePi(@RequestParam double radius, @RequestParam double n) {
        //double radius = 2;
        log.debug("calculatePi to radius : {}",radius);

        RoundC roundC;

        roundC = roundCService.calculatePi(radius,n);

        return ResponseEntity.ok()
            .body(roundC);
    }

}
