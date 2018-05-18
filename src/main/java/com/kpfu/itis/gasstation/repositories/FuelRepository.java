package com.kpfu.itis.gasstation.repositories;

import com.kpfu.itis.gasstation.entities.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Rustem.
 */
@Repository
public interface FuelRepository extends JpaRepository<Fuel, Long> {

}
