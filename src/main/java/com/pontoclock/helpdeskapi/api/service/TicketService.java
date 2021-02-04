package com.pontoclock.helpdeskapi.api.service;

import com.pontoclock.helpdeskapi.api.models.entities.Ticket;

public interface TicketService {
    Ticket create(Ticket ticket);
}
