package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Steward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Steward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StewardRepository extends JpaRepository<Steward, Long> {

    Steward findStewardByStewardID(String stewardID);

    List<Steward> findAllByStewardInfo(String stewardInfo);

}
