package com.pontoclock.helpdeskapi.api.resource;

import com.pontoclock.helpdeskapi.api.exceptions.ApiErrors;
import com.pontoclock.helpdeskapi.api.models.TicketDTO;
import com.pontoclock.helpdeskapi.api.models.entities.Ticket;
import com.pontoclock.helpdeskapi.api.service.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final ModelMapper modelMapper;

    public TicketController(TicketService ticketService, ModelMapper modelMapper) {
        this.ticketService = ticketService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<TicketDTO> create(@RequestBody @Valid TicketDTO ticketDTO) {
        var newTicket = modelMapper.map(ticketDTO, Ticket.class);
        var savedTicket = this.ticketService.create(newTicket);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(savedTicket, TicketDTO.class));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        return new ApiErrors(bindingResult);
    }
}
