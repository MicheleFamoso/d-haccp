package it.epicode.d.haccp.dto;


import it.epicode.d.haccp.enumeration.Conformita;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data

public class CalendarioDto {

    private int id;
    private LocalDate data;
    private String tipoControllo;
    private String descrizione;
    private String valore;
    private Conformita conformita;


    public CalendarioDto(int id, LocalDate data, String temperatura, String s, String s1, String name) {
    }
}
