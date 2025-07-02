package it.epicode.d.haccp.dto;

import it.epicode.d.haccp.enumeration.Conformita;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FornituraDto {
    @NotNull(message = "Il campo data non puo essere vuoto")
    private LocalDate data;
    @NotNull(message = "Il campo fornitore non puo essere vuoto")
    private int fornitoreId;
    @NotEmpty(message = "Il prodotto non puo essere vuoto")
    private String prodotto;
    @NotNull(message = "Il campo conformita non puo essere vuoto")
    private Conformita conformita;
    @NotEmpty(message = "il lotto non puo essere vuoto")
    private String lotto;
}
