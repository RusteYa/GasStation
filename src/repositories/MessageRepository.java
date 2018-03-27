package repositories;

import entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Rustem.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Message findById(int id);

    void deleteMessageById(int id);
}
