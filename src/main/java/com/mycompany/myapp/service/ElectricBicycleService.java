package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.ElectricBicycle;
import com.mycompany.myapp.domain.Steward;
import com.mycompany.myapp.repository.ElectricBicycleRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Create by wys on 2018/7/23
 */

@Service
public class ElectricBicycleService implements ElectricBicycleServiceInterface{


    @Autowired
    ElectricBicycleRepository electricBicycleRepository;
    /*
        获取全部的电动车
     */
    @Override
    public List<ElectricBicycle> getAllElectricBicycle() {
        List<ElectricBicycle> electricBicycleList = electricBicycleRepository.findAll();
        return electricBicycleList;
    }

    /*
        获取需要维护的电动车
        是否可以异步，调优,加速
     */
    @Override
    public List<ElectricBicycle> getMaintenanceBicycle() {
        List<ElectricBicycle> electricBicycleList = electricBicycleRepository.findElectricBicycleByBicycleInfo("true");
        return electricBicycleList;
    }

    /**
     * 指定一辆需要维护的电动车发送给空闲的维护员
     * 成功:电动车需要维护，维护员空闲
     *
     * @param bicycleID
     * @param stewardID
     * @return 车的维护对接 状态
     */
    @Override
    public String maintenanceBicycleToSteward(String bicycleID, String stewardID) {

        String state = "";



        return state;
    }
}
