package it.epicode.d.haccp.controller;


import it.epicode.d.haccp.dto.InfestantiDto;
import it.epicode.d.haccp.enumeration.Conformita;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.Infestanti;
import it.epicode.d.haccp.service.InfestantiService;
import it.epicode.d.haccp.service.UserService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/infestanti")
public class InfestantiController {


    @Autowired
    private InfestantiService infestantiService;

    @Autowired
    private UserService userService;


    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Infestanti saveInfestante(@RequestBody @Validated InfestantiDto infestantiDto, BindingResult bindingResult, Principal principal) throws NotFoundException {
        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s,e)->s+e));
        }
        int aziendaId = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"))
                .getAzienda().getId();
        return infestantiService.saveInfestanti(infestantiDto, aziendaId);
    }


    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Infestanti> getAllInfestanti(Principal principal) throws NotFoundException {
        int aziendaId = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"))
                .getAzienda().getId();
        return infestantiService.getAllInfestanti(aziendaId);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Infestanti updateInfestante(@RequestBody @Validated InfestantiDto infestantiDto, @PathVariable int id) throws NotFoundException {
        return infestantiService.updateInfestante(id,infestantiDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public void deleteInfestante(@PathVariable int id) throws NotFoundException {
        infestantiService.deleteInfestante(id);
    }

    @GetMapping("/data")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Infestanti> getInfestantiByData(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,Principal principal) throws NotFoundException {
        int aziendaId = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"))
                .getAzienda().getId();
        return infestantiService.findInfestantiByData(data, aziendaId);
    }
    @GetMapping("/range")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Infestanti> getInfestantiByRange(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,Principal principal) throws NotFoundException {
        int aziendaId = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"))
                .getAzienda().getId();
        return infestantiService.findInfestantiByDate(start, end,aziendaId);
    }

    @GetMapping("/roditori")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Infestanti> getRoditoriByConformita(@RequestParam("conformita") Conformita conformita,Principal principal) throws NotFoundException {
        int aziendaId = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"))
                .getAzienda().getId();
        return infestantiService.findByRoditori(conformita,aziendaId);
    }
    @GetMapping("/striscianti")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Infestanti> getStrisciantiByConformita(@RequestParam("conformita") Conformita conformita,Principal principal) throws NotFoundException {
        int aziendaId = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"))
                .getAzienda().getId();
        return infestantiService.findByInsettiStriscianti(conformita,aziendaId);
    }
    @GetMapping("/volanti")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Infestanti> getVolantiByConformita(@RequestParam("conformita") Conformita conformita,Principal principal) throws NotFoundException {
        int aziendaId = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"))
                .getAzienda().getId();
        return infestantiService.findByInsettiVolanti(conformita,aziendaId);
    }
    @GetMapping("/conformi")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Infestanti> getAllConformi(Principal principal) throws NotFoundException {
        int aziendaId = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"))
                .getAzienda().getId();
        return infestantiService.findByConformi(aziendaId);
    }

    @GetMapping("/non_conformi")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Infestanti> getAllNonConformi(Principal principal) throws NotFoundException {
        int aziendaId = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"))
                .getAzienda().getId();
        return infestantiService.findByNonConformi(aziendaId);
    }

}
