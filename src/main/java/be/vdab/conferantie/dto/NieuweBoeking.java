package be.vdab.conferantie.dto;

import be.vdab.conferantie.domain.Deelnemer;
import be.vdab.conferantie.domain.Sessie;
import jakarta.validation.Valid;

import java.util.List;

public record NieuweBoeking(@Valid NieuwDeelnemer nieuwDeelnemer, @Valid List<Sessie> sessies) {
}
