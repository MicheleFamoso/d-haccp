package it.epicode.d.haccp.controller;

import it.epicode.d.haccp.dto.AziendaDto;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.Azienda;
import it.epicode.d.haccp.model.TemperaturaGiornaliera;
import it.epicode.d.haccp.model.User;
import it.epicode.d.haccp.repository.AziendaRepository;
import it.epicode.d.haccp.service.AziendaService;
import it.epicode.d.haccp.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/aziende")
public class AziendaController {
    @Autowired
    private AziendaService aziendaService;
    @Autowired
    private UserService userService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Azienda createAzienda(@RequestBody @Validated AziendaDto aziendaDto, BindingResult bindingResult, Principal principal) throws NotFoundException {
        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s,e)->s+e));
        }
        return aziendaService.createAzienda(aziendaDto, principal.getName());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Azienda> getAziende(){
        return aziendaService.getAllAziende();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Azienda getAziendaById(@PathVariable int id) throws NotFoundException {
        return aziendaService.getAzienda(id);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Azienda updateAzienda(@PathVariable int id, @RequestBody @Validated AziendaDto aziendaDto) throws NotFoundException {
        return aziendaService.updateAzienda(id, aziendaDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.GONE)
    public void deleteAzienda(@PathVariable int id) throws NotFoundException {
        aziendaService.deleteAzienda(id);
    }


}
