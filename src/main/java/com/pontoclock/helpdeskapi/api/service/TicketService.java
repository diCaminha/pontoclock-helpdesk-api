package com.pontoclock.helpdeskapi.api.service;

import com.pontoclock.helpdeskapi.api.models.entities.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface TicketService {
    Ticket create(Ticket ticket);

    Page<Ticket> find(Ticket ticket, PageRequest pageRequest);
}
