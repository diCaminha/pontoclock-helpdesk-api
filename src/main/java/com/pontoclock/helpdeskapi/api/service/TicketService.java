package com.pontoclock.helpdeskapi.api.service;

import com.pontoclock.helpdeskapi.api.models.TicketDTO;

public interface TicketService {
    TicketDTO create(TicketDTO ticketDTO);
}
