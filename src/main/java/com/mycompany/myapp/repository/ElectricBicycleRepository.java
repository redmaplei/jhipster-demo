package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ElectricBicycle;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the ElectricBicycle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ElectricBicycleRepository extends JpaRepository<ElectricBicycle, Long> {

    List<ElectricBicycle> findElectricBicycleByBicycleInfo(String bicycleInfo);

}
