package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Round;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


/**
 * Spring Data  repository for the Round entity.
 */
@Repository
public interface RoundRepository extends JpaRepository<Round, Long> {



}
