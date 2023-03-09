package be.vdab.conferantie.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record NieuwDeelnemer(@NotBlank String voornaam,@NotBlank String familienaam, @Email String email) {
}
