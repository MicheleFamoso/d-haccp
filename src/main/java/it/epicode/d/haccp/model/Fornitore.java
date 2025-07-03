package it.epicode.d.haccp.model;

import jakarta.persistence.*;
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
    @ManyToOne
    @JoinColumn(name = "azienda_id")
    private Azienda azienda;

}
