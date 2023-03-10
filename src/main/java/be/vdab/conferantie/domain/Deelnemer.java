package be.vdab.conferantie.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public class Deelnemer {
    private final long id;
    private final String voornaam;
    private final String familienaam;
    private final String email;

    public Deelnemer(@PositiveOrZero long id, @NotBlank @NotEmpty String voornaam, @NotBlank @NotEmpty String familienaam, @Email String email) {
        this.id = id;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.email = email;
    }

    @JsonCreator
    public Deelnemer(@NotBlank @NotNull String voornaam, @NotBlank @NotNull String familienaam, @Email @NotNull String email) {
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
