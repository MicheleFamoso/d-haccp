package it.epicode.d.haccp.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class FornitoreDto {
    @NotEmpty(message = "Il campo nome non puo essere vuoto")
    private String nomeFornitore;
    @NotEmpty(message = "Il campo sede non puo essere vuoto")
    private String sede;
    @NotEmpty(message = "Il campo telefono non puo essere vuoto")
    private String telefono;
    @NotEmpty(message = "Il campo email non puo essere vuoto")
    private String email;
    @NotNull(message = "Il campo prodotti non puo essere vuoto")
    private List<String> prodottiForniti;
}
