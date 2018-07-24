package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.ElectricBicycle;
import com.mycompany.myapp.domain.Steward;
import com.mycompany.myapp.repository.ElectricBicycleRepository;
import com.mycompany.myapp.repository.StewardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import sun.rmi.runtime.Log;

import java.util.List;

/**
 * Create by wys on 2018/7/23
 */

@Service
public class ElectricBicycleService implements ElectricBicycleServiceInterface{

    @Autowired
    ElectricBicycleRepository electricBicycleRepository;

    @Autowired
    StewardRepository stewardRepository;

    Logger log = LoggerFactory.getLogger(ElectricBicycleService.class);

    /**
     * 获取全部的电动车
     * @return
     */
    @Override
    public List<ElectricBicycle> getAllElectricBicycle() {
        List<ElectricBicycle> electricBicycleList = electricBicycleRepository.findAll();
        return electricBicycleList;
    }

    /**
     * 获取需要维护的电动车
     * 是否可以异步，调优,加速
     * @return
     */
    @Override
    public List<ElectricBicycle> getMaintenanceBicycle() {
        List<ElectricBicycle> electricBicycleList = electricBicycleRepository
            .findAllByBicycleInfo("true");
        return electricBicycleList;
    }

    /**
     * 指定一辆需要维护的电动车发送给空闲的维护员
     * 成功:电动车需要维护，维护员空闲
     * state
     * electricBicycle_null_steward_null   空
     * success  成功
     * electricBicycle_true_steward_false  车坏维护员不闲
     * electricBicycle_false_steward_true  车不需要维护
     * electricBicycle_false_steward_false
     * unknown 未知
     *
     * @param bicycleID
     * @param stewardID
     * @return 车的维护对接 状态
     */
    @Override
    public String maintenanceBicycleToSteward(String bicycleID, String stewardID) {

        String state = "";

        ElectricBicycle electricBicycle = electricBicycleRepository
            .findElectricBicycleByBicycleID(bicycleID);

        Steward steward = stewardRepository
            .findStewardByStewardID(stewardID);

        /**
         * test log
         * log.debug("---------------------elect: "+electricBicycle+" ----- " +
         "-------------steward: "+steward+"----binfo: "+
         electricBicycle.getBicycleInfo().equals("true") + "----swerad:---" +steward.getStewardID().equals("false"));

         */

        if(electricBicycle == null || steward == null) {
            state = "electricBicycle_null_steward_null";
        }else if (electricBicycle.getBicycleInfo().equals("true") && steward.getStewardInfo().equals("true")) {
            /**
             * test log
             log.debug("----------maintenanceBicycleToSteward success");
             */
            state = "success";
            /**
             * 匹配成功 更新info数据
             */
            electricBicycle.setBicycleInfo("false");
            electricBicycleRepository.save(electricBicycle);
            steward.setStewardInfo("false");
            stewardRepository.save(steward);
        }else if(electricBicycle.getBicycleInfo().equals("true") && steward.getStewardInfo().equals("false")) {
            state = "electricBicycle_true_steward_false";
        }else if(electricBicycle.getBicycleInfo().equals("false")) {
            state = "electricBicycle_false_steward_true";
        }else if (electricBicycle.getBicycleInfo().equals("false") &&  steward.getStewardInfo().equals("false")){
            state = "electricBicycle_false_steward_false";
        }else {
            state = "unknown";
        }

        log.debug("--------------"+state);
        return state;
    }
}
