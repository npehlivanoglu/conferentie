package be.vdab.conferantie.controllers;

import be.vdab.conferantie.domain.Deelnemer;
import be.vdab.conferantie.dto.NieuweBoeking;
import be.vdab.conferantie.services.TicketService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("deelnemers")
public class DeelnemerControllers {
    private final TicketService ticketService;


    public DeelnemerControllers(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    long create(@RequestBody @Valid NieuweBoeking nieuweBoeking) {
        var deelnemer = new Deelnemer(
                nieuweBoeking.nieuwDeelnemer().voornaam(),
                nieuweBoeking.nieuwDeelnemer().familienaam(),
                nieuweBoeking.nieuwDeelnemer().email());
        return ticketService.boek(deelnemer, nieuweBoeking.sessies());
    }

}
