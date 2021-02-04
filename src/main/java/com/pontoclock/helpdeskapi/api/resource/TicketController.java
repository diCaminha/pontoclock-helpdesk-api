package com.pontoclock.helpdeskapi.api.resource;

import com.pontoclock.helpdeskapi.api.models.TicketDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @PostMapping
    public ResponseEntity<TicketDTO> create() {
        TicketDTO ticketDTO =
                TicketDTO.builder()
                        .id(1l)
                        .titulo("ticket titulo")
                        .descricao("descrição do ticket")
                        .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketDTO);
    }
}
