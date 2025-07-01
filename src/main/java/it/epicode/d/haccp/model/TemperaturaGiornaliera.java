package it.epicode.d.haccp.model;


import it.epicode.d.haccp.enumeration.Conformita;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class TemperaturaGiornaliera {

    @Id
    @GeneratedValue
    private int id;

    private LocalDate data;
    private int frigo;
    @Enumerated(EnumType.STRING)
    private Conformita conformita;
}
