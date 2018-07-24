package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Create by wys on 2018/7/23
 */

@RestController
@RequestMapping("/api")
public class BicycleAdminController {

    private final Logger log = LoggerFactory.getLogger(BicycleAdminController.class);

    /*
        得到电动车管理类
     */
    @GetMapping("/electricBicyce")
    @Timed
    public void electricBicycle() {
        log.debug("electricBicycle to");

    }

    /*
        得到维护员管理类
     */
    @GetMapping("/steward")
    @Timed
    public void steward() {

    }


}
