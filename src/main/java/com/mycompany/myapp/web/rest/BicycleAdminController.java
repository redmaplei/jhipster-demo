package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.ElectricBicycle;
import com.mycompany.myapp.service.ElectricBicycleService;
import com.mycompany.myapp.service.StewardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Create by wys on 2018/7/23
 */

@RestController
@RequestMapping("/api")
public class BicycleAdminController {

    private final Logger log = LoggerFactory.getLogger(BicycleAdminController.class);

    @Autowired
    ElectricBicycleService electricBicycleService;
    StewardService stewardService;
    ElectricBicycle electricBicycle;

    /**
     * 得到电动车管理类
     * @return
     */
    @GetMapping("/electricBicyce")
    @Timed
    public ResponseEntity<String> electricBicycle(@RequestParam String bicycleID,@RequestParam String stewardID) {
        log.debug("electricBicycle to");
        String result = electricBicycleService.maintenanceBicycleToSteward(bicycleID,stewardID);

        return ResponseEntity.ok()
            .body("state:"+result);
    }

    /**
     *  得到维护员管理类
     */
    @GetMapping("/steward")
    @Timed
    public void steward() {
        log.debug("steward to");
    }


}
