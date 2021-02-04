package com.pontoclock.helpdeskapi.api.service;

import com.pontoclock.helpdeskapi.api.models.entities.Ticket;
import com.pontoclock.helpdeskapi.api.repositories.TicketRepository;
import com.pontoclock.helpdeskapi.api.service.impl.TicketServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class TicketServiceTest {

    TicketService ticketService;

    @MockBean
    TicketRepository ticketRepository;

    @BeforeEach
    public void setUp() {
        this.ticketService = new TicketServiceImpl(ticketRepository);
    }

    @Test
    @DisplayName("Deve salvar ticket com sucesso")
    public void createTicketTest() {

        //cenario
        Ticket ticket = Ticket.builder()
                .id(1l)
                .titulo("titulo qualquer")
                .descricao("uma descrição da tarefa")
                .build();
        Mockito.when(ticketRepository.save(ticket))
                .thenReturn(Ticket.builder().id(10l).titulo("tarefa teste").descricao("descricao").build());

        //execução
        Ticket savedTicket = ticketService.create(ticket);

        //verificacao
        assertThat(savedTicket.getId()).isNotNull();
        assertThat(savedTicket.getId().equals(10l));
        assertThat(savedTicket.getTitulo().equals("tarefa teste"));
    }
}
