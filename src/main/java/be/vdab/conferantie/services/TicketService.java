package be.vdab.conferantie.services;

import be.vdab.conferantie.domain.Deelnemer;
import be.vdab.conferantie.domain.Sessie;
import be.vdab.conferantie.dto.DeelnemerIdSesssieId;
import be.vdab.conferantie.dto.VoornaamFamilienaam;
import be.vdab.conferantie.exceptions.DeelnemerIsAlBestaatException;
import be.vdab.conferantie.exceptions.UitverkochtException;
import be.vdab.conferantie.repositories.DeelnemerRepository;
import be.vdab.conferantie.repositories.SessieRepository;
import be.vdab.conferantie.repositories.TicketRepository;
import be.vdab.conferantie.repositories.VoorkeurRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final DeelnemerRepository deelnemerRepository;
    private final VoorkeurRepository voorkeurRepository;
    private final SessieRepository sessieRepository;

    public TicketService(TicketRepository ticketRepository, DeelnemerRepository deelnemerRepository, VoorkeurRepository voorkeurRepository, SessieRepository sessieRepository) {
        this.ticketRepository = ticketRepository;
        this.deelnemerRepository = deelnemerRepository;
        this.voorkeurRepository = voorkeurRepository;
        this.sessieRepository = sessieRepository;
    }

    public int findBeschikbaarTickets() {
        return ticketRepository.findBeschikbaarTickets();
    }

    @Transactional
    public long boek(@Valid Deelnemer deelnemer, @Valid List<Long> sessieIDs) {
        if (ticketRepository.findAndLockBeschikbaarTickets() == 0) {
            throw new UitverkochtException();
        }
        VoornaamFamilienaam voornaamFamilienaam = new VoornaamFamilienaam(deelnemer.getVoornaam(), deelnemer.getFamilienaam());
        if (deelnemerRepository.findAndLockByVoornaamEnFamilienaam(voornaamFamilienaam).isPresent()) {
            throw new DeelnemerIsAlBestaatException();
        }
        ticketRepository.boekEenticket();
        var id = deelnemerRepository.create(deelnemer);
        for (var sessieID : sessieIDs) {
            sessieRepository.verhoogInteresseById(sessieID);
            var voorkeur = new DeelnemerIdSesssieId(id, sessieID);
            voorkeurRepository.create(voorkeur);
        }
        return id;
    }
}
