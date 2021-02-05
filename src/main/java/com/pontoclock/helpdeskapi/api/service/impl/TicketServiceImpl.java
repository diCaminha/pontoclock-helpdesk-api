package com.pontoclock.helpdeskapi.api.service.impl;

import com.pontoclock.helpdeskapi.api.exceptions.BusinessException;
import com.pontoclock.helpdeskapi.api.models.entities.Ticket;
import com.pontoclock.helpdeskapi.api.repository.TicketRepository;
import com.pontoclock.helpdeskapi.api.service.TicketService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

        if (ticketRepository.existsByTitulo(ticket.getTitulo()))
            throw new BusinessException("Já existe um ticket com esse título.");

        return this.ticketRepository.save(ticket);
    }

    @Override
    public Page<Ticket> find(Ticket ticket, PageRequest pageRequest) {
        Example<Ticket> example = Example.of(ticket,
                ExampleMatcher
                        .matching()
                        .withIgnoreCase()
                        .withIgnoreNullValues()
                        .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING));

        return this.ticketRepository.findAll(example, pageRequest);
    }
}
