package it.epicode.d.haccp.controller;


import it.epicode.d.haccp.dto.OperazioniPuliziaDto;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.OperazioniPulizia;
import it.epicode.d.haccp.service.OperazioniRepositoryService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pulizie")
public class OperazioniPuliziaController {

    @Autowired
    private OperazioniRepositoryService operazioniRepositoryService;


    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public OperazioniPulizia createPulizia(@RequestBody @Validated OperazioniPuliziaDto operazioniPuliziaDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s,e)->s+e));
        }
        return operazioniRepositoryService.createPulizia(operazioniPuliziaDto);

    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<OperazioniPulizia>getPulizie(){
        return operazioniRepositoryService.getOperazioniPulizia();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public OperazioniPulizia getPuliziaById(@PathVariable int id) throws NotFoundException {
        return operazioniRepositoryService.getOperazioniPuliziaById(id);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public OperazioniPulizia updatePulizia( @PathVariable int id, @RequestBody @Validated OperazioniPuliziaDto operazioniPuliziaDto) throws NotFoundException {

        return operazioniRepositoryService.updatePulizia(id, operazioniPuliziaDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.OK)
    public void deletePulizia(@PathVariable int id) throws NotFoundException {
        operazioniRepositoryService.deletePulizia(id);
    }
}
