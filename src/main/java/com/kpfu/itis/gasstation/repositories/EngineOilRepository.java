package com.kpfu.itis.gasstation.repositories;

import com.kpfu.itis.gasstation.entities.EngineOil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Rustem.
 */
@Repository
public interface EngineOilRepository extends JpaRepository<EngineOil, Long> {
    EngineOil findById(int id);

    void deleteEngineOilById(int id);
}
