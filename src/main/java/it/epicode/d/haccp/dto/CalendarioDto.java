package it.epicode.d.haccp.dto;


import it.epicode.d.haccp.enumeration.Conformita;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalendarioDto {

    private int id;
    private LocalDate data;
    private String tipoControllo;
    private String descrizione;
    private String valore;
    private Conformita conformita;

}
