package it.epicode.d.haccp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Azienda {
    @Id
    @GeneratedValue
    private int id;
    private String denominazioneAziendale;
    private String ragioneSociale;
    private String tipologiaAttivita;
    private String sedeOperativa;
    private String partitaIva;
    private String telefono;
    private String email;


}
