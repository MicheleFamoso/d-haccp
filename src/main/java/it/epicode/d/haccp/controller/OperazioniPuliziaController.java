package it.epicode.d.haccp.controller;

import it.epicode.d.haccp.dto.OperazioniPuliziaDto;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.OperazioniPulizia;
import it.epicode.d.haccp.service.OperazioniRepositoryService;
import it.epicode.d.haccp.service.UserService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/pulizie")
public class OperazioniPuliziaController {

    @Autowired
    private OperazioniRepositoryService operazioniRepositoryService;

    @Autowired
    private UserService userService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public OperazioniPulizia createPulizia(@RequestBody @Validated OperazioniPuliziaDto operazioniPuliziaDto, BindingResult bindingResult, Principal principal) throws NotFoundException {
        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s,e)->s+e));
        }
        int aziendaId = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"))
                .getAzienda().getId();
        return operazioniRepositoryService.createPulizia(operazioniPuliziaDto, aziendaId);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<OperazioniPulizia> getPulizie(Principal principal) throws NotFoundException {
        int aziendaId = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"))
                .getAzienda().getId();
        return operazioniRepositoryService.getOperazioniPulizia(aziendaId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public OperazioniPulizia getPuliziaById(@PathVariable int id) throws NotFoundException {
        return operazioniRepositoryService.getOperazioniPuliziaById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public OperazioniPulizia updatePulizia(@PathVariable int id, @RequestBody @Validated OperazioniPuliziaDto operazioniPuliziaDto) throws NotFoundException {
        return operazioniRepositoryService.updatePulizia(id, operazioniPuliziaDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.GONE)
    public void deletePulizia(@PathVariable int id) throws NotFoundException {
        operazioniRepositoryService.deletePulizia(id);
    }



    @GetMapping("/oggetto")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<OperazioniPulizia> findByOggetto(@RequestParam String oggetto, Principal principal) throws NotFoundException {
        int aziendaId = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"))
                .getAzienda().getId();
        return operazioniRepositoryService.findByOggettoAndAziendaId(oggetto, aziendaId);
    }

    @GetMapping("/frequenza")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<OperazioniPulizia> findByFrequenza(@RequestParam String frequenza, Principal principal) throws NotFoundException {
        int aziendaId = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"))
                .getAzienda().getId();
        return operazioniRepositoryService.findByFrequenzaAndAziendaId(frequenza, aziendaId);
    }
}
