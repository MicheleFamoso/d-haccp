package it.epicode.d.haccp.dto;


import it.epicode.d.haccp.enumeration.Conformita;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InfestantiDto {
    @NotNull(message = "Il campo data non puo essere vuoto")
    private LocalDate data;
    @NotNull(message = "Il campo roditori non puo essere vuoto")
    private Conformita roditori;
    @NotNull(message = "Il campo insettiStriscianti non puo essere vuoto")
    private Conformita insettiStriscianti;
    @NotNull(message = "Il campo insettiVolanti non puo essere vuoto")
    private Conformita insettiVolanti;
}
