package com.pontoclock.helpdeskapi.api.repository;

import com.pontoclock.helpdeskapi.api.models.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    boolean existsByTitulo(String titulo);
}
