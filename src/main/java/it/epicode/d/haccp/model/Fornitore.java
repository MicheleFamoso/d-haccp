package it.epicode.d.haccp.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Fornitore {
    @Id
    @GeneratedValue
    private int id;
    private String nomeFornitore;
    private String sede;
    private String telefono;
    private String email;

    @ElementCollection
    private List<String> prodottiForniti;


}
