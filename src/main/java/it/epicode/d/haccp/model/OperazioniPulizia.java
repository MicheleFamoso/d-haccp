package it.epicode.d.haccp.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class OperazioniPulizia {
    @Id
    @GeneratedValue
    private int id;
    private String oggetto;
    private String frequenza;
    private String detergente;
    private String attrezzatureUtilizzate;

    @ManyToOne
    @JoinColumn(name = "azienda_id")
    private Azienda azienda;

}
