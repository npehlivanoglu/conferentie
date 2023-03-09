package be.vdab.conferantie.controllers;

import be.vdab.conferantie.dto.NieuweBoeking;
import be.vdab.conferantie.services.TicketService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tickets")
public class TicketControllers {
    private final TicketService ticketService;

    public TicketControllers(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    int findBeschikbaarTickets(){
        return ticketService.findBeschikbaarTickets();
    }


}
