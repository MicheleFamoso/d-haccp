package it.epicode.d.haccp.model;


import it.epicode.d.haccp.enumeration.Conformita;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Infestanti {
    @Id
    @GeneratedValue
    private int id;
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    private Conformita roditori;
    @Enumerated(EnumType.STRING)
    private Conformita insettiStriscianti;
    @Enumerated(EnumType.STRING)
    private Conformita insettiVolanti;

}
