package com.kpfu.itis.gasstation.repositories;

import com.kpfu.itis.gasstation.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ma on 14.04.2016.
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findById(int id);

    AppUser findByLogin(String login);

    List<AppUser> findAllByLoginContains(String value);

    void deleteUserById(int id);
}
