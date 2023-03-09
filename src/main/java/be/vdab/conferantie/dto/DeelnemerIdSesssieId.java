package be.vdab.conferantie.dto;

import jakarta.validation.constraints.PositiveOrZero;

public record DeelnemerIdSesssieId(@PositiveOrZero long deelnemerId, @PositiveOrZero long sessieId) {
}
