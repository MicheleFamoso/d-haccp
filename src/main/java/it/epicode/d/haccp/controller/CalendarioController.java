package it.epicode.d.haccp.controller;

import it.epicode.d.haccp.dto.CalendarioDto;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.service.CalendarioService;
import it.epicode.d.haccp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/controlli")
public class CalendarioController {

    @Autowired
    private CalendarioService calendarioService;

    @Autowired
    private UserService userService;

    @GetMapping("/calendario")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<CalendarioDto> getControlliCalendario(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            Principal principal) throws NotFoundException {

        int aziendaId = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"))
                .getAzienda().getId();

        return calendarioService.getControlliPerCalendario(start, end, aziendaId);
    }
}
