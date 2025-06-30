package it.epicode.d.haccp.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AziendaDto {
    @NotBlank(message = "Denominazione aziendale obbligatoria")
    private String denominazioneAziendale;
    @NotBlank(message = "Ragione sociale obbligatoria")
    private String ragioneSociale;
    @NotBlank(message = "Tipologia attività obbligatoria")
    private String tipologiaAttivita;
    @NotBlank(message = "Sede operativa obbligatoria")
    private String sedeOperativa;
    @NotBlank(message = "Partita IVA obbligatoria")
    @Size(min = 11, max = 11, message = "La partita IVA deve essere di 11 caratteri")
    private String partitaIva;
    @NotBlank(message = "Telefono obbligatorio")
    private String telefono;
    @Email(message = "Email non valida")
    private String email;

}
