package com.kpfu.itis.gasstation.repositories;

import com.kpfu.itis.gasstation.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ma on 14.04.2016.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findById(int id);

    User findByLogin(String login);

    void deleteUserById(int id);
}
