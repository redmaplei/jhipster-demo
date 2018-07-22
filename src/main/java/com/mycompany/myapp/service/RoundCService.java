package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Round;
import com.mycompany.myapp.domain.RoundC;
import com.mycompany.myapp.repository.RoundCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by wys on 2018/7/22
 */
@Service
public class RoundCService {

    @Autowired
    RoundCRepository roundCRepository;

    public RoundC calculatePi(double radius, double n) {

        RoundC roundC = new RoundC(1d,2d,3d,4d);

        roundCRepository.save(roundC);

        return roundC;
    }

}
