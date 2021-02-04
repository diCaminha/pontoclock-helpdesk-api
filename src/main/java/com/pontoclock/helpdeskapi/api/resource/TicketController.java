package com.pontoclock.helpdeskapi.api.resource;

import com.pontoclock.helpdeskapi.api.models.TicketDTO;
import com.pontoclock.helpdeskapi.api.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketDTO> create(@RequestBody TicketDTO ticketDTO) {
        this.ticketService.create(ticketDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketDTO);
    }
}
