package it.epicode.d.haccp.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class OperazioniPuliziaDto {

    @NotEmpty(message = "Il campo oggetto non puo essere vuoto")
    private String oggetto;
    @NotEmpty(message = "Il campo frequenza non puo essere vuoto")
    private String frequenza;
    @NotEmpty(message = "Il campo detergente non puo essere vuoto")
    private String detergente;
    @NotEmpty(message = "Il campo attrezzature utilizzate  non puo essere vuoto")
    private String attrezzatureUtilizzate;
}
