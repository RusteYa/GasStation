package com.kpfu.itis.gasstation.repositories;

import com.kpfu.itis.gasstation.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ma on 14.04.2016.
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findById(int id);

    AppUser findByLogin(String login);

    void deleteUserById(int id);
}
