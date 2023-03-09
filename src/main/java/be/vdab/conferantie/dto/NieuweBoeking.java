package be.vdab.conferantie.dto;

import be.vdab.conferantie.domain.Deelnemer;
import be.vdab.conferantie.domain.Sessie;
import jakarta.validation.Valid;

import java.util.List;

public record NieuweBoeking(@Valid Deelnemer deelnemer, List<Sessie> sessies) {
}
