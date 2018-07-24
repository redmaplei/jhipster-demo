package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Steward;
import com.mycompany.myapp.repository.StewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by wys on 2018/7/23
 */

@Service
public class StewardService implements StewardServiceInterface {

    @Autowired
    StewardRepository stewardRepository;

    @Override
    public List<Steward> getAllSteward() {
        List<Steward> stewards = stewardRepository
            .findAll();
        return stewards;
    }

    @Override
    public List<Steward> getAllFreeSteward() {

        List<Steward> stewards = stewardRepository
            .findAllByStewardInfo("true");
        return stewards;
    }

    @Override
    public String maintenanceBicycleSituation(String bicycleID, String stewardID) {



        return null;
    }
}
