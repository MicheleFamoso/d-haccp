package it.epicode.d.haccp.controller;

import it.epicode.d.haccp.dto.FornituraDto;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.Fornitura;
import it.epicode.d.haccp.service.FornituraService;
import it.epicode.d.haccp.service.UserService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/forniture")
public class FornituraController {

    @Autowired
    private FornituraService service;

    @Autowired
    private UserService userService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Fornitura saveFornitura(@RequestBody @Validated FornituraDto dto, BindingResult bindingResult, Principal principal) throws NotFoundException {
        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s,e)->s+e));
        }
        int aziendaId = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"))
                .getAzienda().getId();
        return service.createFornitura(dto, aziendaId);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Fornitura> getAllForniture(Principal principal) throws NotFoundException {
        int aziendaId = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"))
                .getAzienda().getId();
        return service.getAllForniture(aziendaId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Fornitura getFornituraById(@PathVariable int id) throws NotFoundException {
        return service.getFornituraById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Fornitura updateFornitura(@PathVariable int id, @RequestBody @Validated FornituraDto dto, BindingResult bindingResult) throws NotFoundException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s, e) -> s + e));
        }
        return service.updateFornitura(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFornitura(@PathVariable int id) throws NotFoundException {
        service.deleteFornitura(id);
    }

    @GetMapping("/data")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Fornitura> findByData(@RequestParam("data") LocalDate data, Principal principal) throws NotFoundException {
        int aziendaId = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"))
                .getAzienda().getId();
        return service.findByData(data, aziendaId);
    }

    @GetMapping("/range")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Fornitura> findByDataBetween(@RequestParam("start") LocalDate start, @RequestParam("end") LocalDate end, Principal principal) throws NotFoundException {
        int aziendaId = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"))
                .getAzienda().getId();
        return service.findByDataBetween(start, end, aziendaId);
    }

    @GetMapping("/prodotto")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Fornitura> findByProdotto(@RequestParam("prodotto") String prodotto, Principal principal) throws NotFoundException {
        int aziendaId = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"))
                .getAzienda().getId();
        return service.findByProdottoContainingIgnoreCase(prodotto, aziendaId);
    }

    @GetMapping("/fornitore")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Fornitura> findByNomeFornitore(@RequestParam("nomeFornitore") String nomeFornitore, Principal principal) throws NotFoundException {
        int aziendaId = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"))
                .getAzienda().getId();
        return service.findByNomeFornitoreContainingIgnoreCase(nomeFornitore, aziendaId);
    }
}
