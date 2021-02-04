package com.pontoclock.helpdeskapi.api.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pontoclock.helpdeskapi.api.models.TicketDTO;
import com.pontoclock.helpdeskapi.api.models.entities.Ticket;
import com.pontoclock.helpdeskapi.api.service.TicketService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class TicketControllerTest {

    static String TICKET_ENDPOINT = "/api/tickets";
    TicketDTO ticketDTO =
            TicketDTO.builder()
                    .id(1l)
                    .titulo("titulo ticket")
                    .descricao("descrição ticket")
                    .build();

    Ticket ticket =
            Ticket.builder()
                    .id(1l)
                    .titulo("titulo ticket")
                    .descricao("descrição ticket")
                    .build();
    @Autowired
    MockMvc mockMvc;

    @MockBean
    TicketService ticketService;

    @Test
    @DisplayName("Deve criar um livro com sucesso.")
    public void createTicketTest() throws Exception {

        BDDMockito.given(ticketService.create(Mockito.any(Ticket.class))).willReturn(this.ticket);
        String json = new ObjectMapper().writeValueAsString(this.ticketDTO);

        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders
                        .post(TICKET_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json);
        mockMvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("titulo").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("descricao").isNotEmpty());
    }

    @Test
    @DisplayName("Deve lançar erro ao tentar salvar Ticket com dados inválidos")
    public void createInvalidTicketTest() throws Exception {
        BDDMockito.given(ticketService.create(Mockito.any(Ticket.class))).willReturn(this.ticket);
        String json = new ObjectMapper().writeValueAsString(new TicketDTO());

        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders
                        .post(TICKET_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("errors").exists());
    }
}
