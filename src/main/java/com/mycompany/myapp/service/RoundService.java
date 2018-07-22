package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Round;
import com.mycompany.myapp.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by wys on 2018/7/22
 */
@Service
public class RoundService {


    @Autowired
    RoundRepository roundRepository;

    public Round calculatePi(double radius,double n) {

        double angle = 180/n;
        double radians_angle = Math.toRadians(angle);
        double a = (Math.sin(radians_angle)) * radius;
        double perimeter = 2 * a * n;
        double pi = perimeter / (2*radius);
        System.out.println("pi to value----------->:"+pi);

        Round round = new Round(radius,perimeter,pi);

        roundRepository.save(round);

        return round;
    }

}
