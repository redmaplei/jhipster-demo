package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.BicycleAdmin;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


/**
 * Spring Data  repository for the BicycleAdmin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BicycleAdminRepository extends JpaRepository<BicycleAdmin, Long> {

    BicycleAdmin findBicycleAdminByUsernameAndPassword(String username,String password);

}
