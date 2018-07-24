package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.ElectricBicycle;
import com.mycompany.myapp.domain.Steward;

import java.util.List;

/**
 * Create by wys on 2018/7/23
 */
public interface ElectricBicycleServiceInterface {

    /*
        获取全部的电动车
     */
    public List<ElectricBicycle> getAllElectricBicycle();

    /*
        获取需要维护的电动车
     */
    public List<ElectricBicycle> getMaintenanceBicycle();

    /*
        指定一辆需要维护的电动车发送给空闲的维护员
     */
    public String maintenanceBicycleToSteward(String bicycleID, String stewardID);

}
