package com.kpfu.itis.gasstation.repositories;

import com.kpfu.itis.gasstation.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Rustem.
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    News findById(int id);

    int deleteNewsById(int id);
}
