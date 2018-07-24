package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.ElectricBicycle;
import com.mycompany.myapp.domain.Steward;

import java.util.List;

/**
 * Create by wys on 2018/7/24
 */
public interface StewardServiceInterface {

    /**
     * 获取全部的维护员
     * @return
     */
    public List<Steward> getAllSteward();

    /**
     * 获取空闲的维护员
     * @return
     */
    public List<Steward> getAllFreeSteward();

    /**
     * 维护员维护好车辆的情况
     * @return
     */
    public String maintenanceBicycleSituation(String bicycleID, String stewardID);

}
