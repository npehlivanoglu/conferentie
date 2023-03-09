package be.vdab.conferantie.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record NieuwDeelnemer(@NotBlank @NotEmpty String voornaam, @NotBlank @NotEmpty String familienaam, @Email String email) {
}
