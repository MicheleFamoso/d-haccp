package it.epicode.d.haccp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDto {

    @NotEmpty(message = "Nome non puo essere vuoto")
    private String nome;
    @NotEmpty(message = "Cognome non puo essere vuoto")
    private String cognome;
    @NotEmpty(message = "Username non puo essere vuoto")
    private String username;
    @NotEmpty(message = "La password non puo essere vuoto")
    private String password;
    @NotEmpty(message = "email richiesta")
    private String email;


}
