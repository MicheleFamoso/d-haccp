package it.epicode.d.haccp.model;

import it.epicode.d.haccp.enumeration.Conformita;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Fornitura {

    @Id
    @GeneratedValue
    private int id;

    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "fornitore_id")
    private Fornitore fornitore;

    private String prodotto;

    @Enumerated(EnumType.STRING)
    private Conformita conformita;

    private String lotto;
    @ManyToOne
    @JoinColumn(name = "azienda_id")
    private Azienda azienda;
}
