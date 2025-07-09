package it.epicode.d.haccp.controller;

import it.epicode.d.haccp.dto.FornitoreDto;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.Fornitore;
import it.epicode.d.haccp.service.FornitoreService;
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
@RequestMapping("/fornitori")
public class FornitoreController {

    @Autowired
    private FornitoreService fornitoreService;

    @Autowired
    private UserService userService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Fornitore saveFornitore(@RequestBody @Validated FornitoreDto fornitoreDto, BindingResult bindingResult, Principal principal) throws NotFoundException {
        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .reduce("", (s,e)->s+e));
        }
        int aziendaId = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"))
                .getAzienda().getId();
        return fornitoreService.createFornitore(fornitoreDto, aziendaId);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Fornitore> getAllFornitori(Principal principal) throws NotFoundException {
        int aziendaId = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"))
                .getAzienda().getId();
        return fornitoreService.getAllFornitori(aziendaId);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Fornitore updateFornitore(@RequestBody @Validated FornitoreDto fornitoreDto, @PathVariable int id) throws NotFoundException {
        return fornitoreService.updateFornitore(id, fornitoreDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteFornitore(@PathVariable int id) throws NotFoundException {
        fornitoreService.deleteFornitore(id);
    }

    @GetMapping("/prodotto")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Fornitore> getFornitoreByProdotto(@RequestParam("prodotto") String prodotto, Principal principal) throws NotFoundException {
        int aziendaId = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"))
                .getAzienda().getId();
        return fornitoreService.findByProdotto(prodotto, aziendaId);
    }

    @GetMapping("/fornitore")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Fornitore> getFornitore(@RequestParam("fornitore") String fornitore, Principal principal) throws NotFoundException {
        int aziendaId = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"))
                .getAzienda().getId();
        return fornitoreService.findByNomeFornitore(fornitore, aziendaId);
    }


    @GetMapping("/cerca")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Fornitore> cercaFornitori(@RequestParam("query") String query, Principal principal) throws NotFoundException {
        int aziendaId = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"))
                .getAzienda().getId();
        return fornitoreService.cercaPerNomeOFornitura(query, aziendaId);
    }


}


