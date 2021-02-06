package com.pontoclock.helpdeskapi.api.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pontoclock.helpdeskapi.api.models.ApontamentoDTO;
import com.pontoclock.helpdeskapi.api.models.TicketDTO;
import com.pontoclock.helpdeskapi.api.models.entities.Apontamento;
import com.pontoclock.helpdeskapi.api.models.entities.Ticket;
import com.pontoclock.helpdeskapi.api.service.ApontamentoService;
import com.pontoclock.helpdeskapi.api.service.TicketService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = ApontamentoController.class)
public class ApontamentoControlerTest {

    public static final String API_LOANS = "/api/loans";
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;
    @MockBean
    private ApontamentoService apontamentoService;

    @Test
    @DisplayName("Deve realizar um apontamento")
    public void criarApontamentoTest() throws Exception {

        ApontamentoDTO dto =
                ApontamentoDTO.builder()
                        .ticketId(1L)
                        .horasTrabalho(2)
                        .descricao("teste descricao")
                        .build();
        Ticket ticketParaApontamento = Ticket.builder().id(1l).titulo("titulo").descricao("descricao").build();
        Apontamento apontamento =
                Apontamento.builder()
                        .ticket(ticketParaApontamento)
                        .horas(2)
                        .descricao("apontamento")
                        .build();

        String json = new ObjectMapper().writeValueAsString(dto);

        //mock servicos
        BDDMockito.given(this.ticketService.findById(dto.getTicketId()))
                .willReturn(Optional.of(Ticket.builder().id(1l).build()));
        BDDMockito.given(this.apontamentoService.save(Mockito.any(Apontamento.class))).willReturn(apontamento);

        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders
                        .post(API_LOANS)
                        .accept(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(1l));

    }
}
