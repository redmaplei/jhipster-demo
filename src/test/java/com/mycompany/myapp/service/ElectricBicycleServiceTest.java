package com.mycompany.myapp.service;

import com.mycompany.myapp.Demo3App;
import com.mycompany.myapp.domain.ElectricBicycle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Create by wys on 2018/7/24
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo3App.class)
public class ElectricBicycleServiceTest {

    @Autowired
    ElectricBicycleService electricBicycleService;

    @Test
    public void getAllElectricBicycle() throws Exception {
        System.out.println(electricBicycleService.getAllElectricBicycle());
    }

    @Test
    public void getMaintenanceBicycle() throws Exception {
        System.out.println(electricBicycleService.getMaintenanceBicycle());
    }

    @Test
    public void maintenanceBicycleToSteward() throws Exception {
        System.out.println(electricBicycleService.maintenanceBicycleToSteward("d1","s1"));
    }

}
