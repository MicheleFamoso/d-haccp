package it.epicode.d.haccp.dto;

import it.epicode.d.haccp.enumeration.Conformita;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TemperaturaGiornalieraDto {
    @NotNull(message = "Il campo data non puo essere vuoto")
    private LocalDate data;
    @NotNull(message = "Il campo frigo non puo essere vuoto")
    private int frigo;
   @NotNull(message = "Il campo conformita non puo essere vuoto")
    private Conformita conformita;
   @NotNull(message = "Temperatura richiesta")
   private int temperatura;
}
