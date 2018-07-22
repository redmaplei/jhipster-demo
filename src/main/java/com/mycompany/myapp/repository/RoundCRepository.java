package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.RoundC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Create by wys on 2018/7/22
 */
@Repository
public interface RoundCRepository extends JpaRepository<RoundC,Long> {
}
