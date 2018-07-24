package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.ElectricBicycle;
import com.mycompany.myapp.domain.Steward;

import java.util.List;

/**
 * Create by wys on 2018/7/23
 */
public interface ElectricBicycleServiceInterface {

    /**
     * 获取全部的电动车
     * @return
     */
    public List<ElectricBicycle> getAllElectricBicycle();

    /**
     * 获取需要维护的电动车
     * 是否可以异步，调优,加速
     * @return
     */
    public List<ElectricBicycle> getMaintenanceBicycle();

    /**
     * 指定一辆需要维护的电动车发送给空闲的维护员
     * 成功:电动车需要维护，维护员空闲
     * state
     * success  成功
     * electricBicycle_true_steward_false  车坏维护员不闲
     * electricBicycle_false_steward_true  车不需要维护
     *
     * @param bicycleID
     * @param stewardID
     * @return 车的维护对接 状态
     */
    public String maintenanceBicycleToSteward(String bicycleID, String stewardID);

}
