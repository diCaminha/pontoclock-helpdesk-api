package com.pontoclock.helpdeskapi.api.service.impl;

import com.pontoclock.helpdeskapi.api.exceptions.BusinessException;
import com.pontoclock.helpdeskapi.api.models.entities.Ticket;
import com.pontoclock.helpdeskapi.api.repositories.TicketRepository;
import com.pontoclock.helpdeskapi.api.service.TicketService;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket create(Ticket ticket) {
        if (ticket.getTitulo().equals(ticket.getDescricao()))
            throw new BusinessException("Titulo e Descrição não podem serem iguais.");

        return this.ticketRepository.save(ticket);
    }
}
