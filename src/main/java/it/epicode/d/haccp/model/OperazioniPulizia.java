package it.epicode.d.haccp.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

}
