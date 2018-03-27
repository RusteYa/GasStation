package repositories;

import entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Rustem.
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    News findById(int id);

    void deleteNewsById(int id);
}
