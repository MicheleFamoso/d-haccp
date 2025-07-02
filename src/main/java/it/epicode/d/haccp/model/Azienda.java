package it.epicode.d.haccp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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
    @OneToMany(mappedBy = "azienda")
    @JsonIgnore
    private List<User> utenti;


}
