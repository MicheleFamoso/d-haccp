package it.epicode.d.haccp.controller;


import it.epicode.d.haccp.dto.InfestantiDto;
import it.epicode.d.haccp.enumeration.Conformita;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.Infestanti;
import it.epicode.d.haccp.service.InfestantiService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/infestanti")
public class InfestantiController {


    @Autowired
    private InfestantiService infestantiService;



    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Infestanti saveInfestante(@RequestBody @Validated InfestantiDto infestantiDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s,e)->s+e));
        }
        return infestantiService.saveInfestanti(infestantiDto);
    }


    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Infestanti> getAllInfestanti(){
        return infestantiService.getAllInfestanti();
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

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Infestanti> getInfestantiByData(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data){
        return infestantiService.findInfestantiByData(data);
    }
    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Infestanti> getInfestantiByRange(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return infestantiService.findInfestantiByDate(start, end);
    }

    @GetMapping("/roditori")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Infestanti> getRoditoriByConformita(@RequestParam("conformita") Conformita conformita){
        return infestantiService.findByRoditori(conformita);
    }
    @GetMapping("/striscianti")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Infestanti> getStrisciantiByConformita(@RequestParam("conformita") Conformita conformita){
        return infestantiService.findByInsettiStriscianti(conformita);
    }
    @GetMapping("/volanti")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Infestanti> getVolantiByConformita(@RequestParam("conformita") Conformita conformita){
        return infestantiService.findByInsettiVolanti(conformita);
    }
    @GetMapping("/conformi")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Infestanti> getAllConformi(){
        return infestantiService.findByConformi();
    }

    @GetMapping("/non_conformi")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Infestanti> getAllNonConformi(){
        return infestantiService.findByNonConformi();
    }

}
