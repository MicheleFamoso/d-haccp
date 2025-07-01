package it.epicode.d.haccp.dto;

import it.epicode.d.haccp.enumeration.TipoConservazione;
import it.epicode.d.haccp.enumeration.TipoProdotto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ConservabilitaDto {

    @NotEmpty(message = "il prodotto non può essere vuoto")
    private String prodotto;
    @NotNull(message = "Il campo Tipo prodotto non puo essere vuoto")
    private TipoProdotto tipoProdotto;
    @NotNull(message = "Il campo tipo conservazione non puo essere vuoto")
    private TipoConservazione tipoConservazione;
    @NotNull(message = "Il giorni conservazione non puo essere vuoto")
    private int giorniMassimaConservazione;
    private String note;


}
