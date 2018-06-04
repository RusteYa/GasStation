package com.kpfu.itis.gasstation.repositories;

import com.kpfu.itis.gasstation.entities.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Rustem.
 */
@Repository
public interface TicketStatusRepository extends JpaRepository<TicketStatus, Long> {
    TicketStatus findByName(String name);
}
