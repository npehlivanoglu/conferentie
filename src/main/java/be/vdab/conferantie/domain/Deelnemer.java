package be.vdab.conferantie.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class Deelnemer {
    private final long id;
    private final String voornaam;
    private final String familienaam;
    private final String email;

    public Deelnemer(@PositiveOrZero long id, @NotBlank String voornaam, @NotBlank String familienaam, @Email String email) {
        this.id = id;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.email = email;
    }

    public Deelnemer(@NotBlank String voornaam, @NotBlank String familienaam, @Email String email) {
        this.id = 0;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public String getEmail() {
        return email;
    }
}
