package it.epicode.d.haccp.model;

import it.epicode.d.haccp.enumeration.TipoConservazione;
import it.epicode.d.haccp.enumeration.TipoProdotto;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Conservabilita {

    @GeneratedValue
    @Id
    private int id;
   private String prodotto;
   @Enumerated(EnumType.STRING)
   private TipoProdotto tipoProdotto;
   @Enumerated(EnumType.STRING)
   private TipoConservazione tipoConservazione;
    private int giorniMassimaConservazione;
   private String note;



}
