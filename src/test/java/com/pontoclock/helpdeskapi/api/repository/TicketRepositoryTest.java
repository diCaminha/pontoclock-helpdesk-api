package com.pontoclock.helpdeskapi.api.repository;

import com.pontoclock.helpdeskapi.api.models.entities.Ticket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class TicketRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    TicketRepository ticketRepository;

    @Test
    @DisplayName("Deve retornar verdadeiro caso exista um ticket com o mesmo titulo")
    public void criarTicketComTituloQueJaExista() {

        String tituloExistente = "titulo que ja exista";

        Ticket ticket = Ticket.builder().titulo(tituloExistente).descricao("uma descrrição qualquer").build();
        testEntityManager.persist(ticket);

        boolean exists = ticketRepository.existsByTitulo(tituloExistente);

        assertThat(exists).isTrue();
    }
}
