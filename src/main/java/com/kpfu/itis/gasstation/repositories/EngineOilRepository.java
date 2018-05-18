package com.kpfu.itis.gasstation.repositories;

import com.kpfu.itis.gasstation.entities.EngineOil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Rustem.
 */
@Repository
public interface EngineOilRepository extends JpaRepository<EngineOil, Long> {
    EngineOil findById(int id);

    void deleteEngineOilById(int id);

    List<EngineOil> findAllByManafacturer(String manafacturer);

    @Query(value = "SELECT DISTINCT manafacturer FROM engineoils", nativeQuery = true)
    List<String> findAllManafacturers();
}
