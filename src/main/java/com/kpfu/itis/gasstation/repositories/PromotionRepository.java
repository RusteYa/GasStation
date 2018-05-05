package com.kpfu.itis.gasstation.repositories;

import com.kpfu.itis.gasstation.entities.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Rustem.
 */
@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    Promotion findById(int id);

    void deletePromotionById(int id);
}
