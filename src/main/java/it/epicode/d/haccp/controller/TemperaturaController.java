package it.epicode.d.haccp.controller;


import it.epicode.d.haccp.dto.TemperaturaGiornalieraDto;
import it.epicode.d.haccp.enumeration.Conformita;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.TemperaturaGiornaliera;
import it.epicode.d.haccp.service.TemperaturaGiornalieraService;
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
@RequestMapping("/temperature")
public class TemperaturaController {
    @Autowired
    private TemperaturaGiornalieraService temperaturaGiornalieraService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public TemperaturaGiornaliera createTemperatura(@RequestBody @Validated TemperaturaGiornalieraDto dto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s,e)->s+e));
        }
        return temperaturaGiornalieraService.createTemperatura(dto);
    }


    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<TemperaturaGiornaliera> getTemperature(){
        return temperaturaGiornalieraService.getAllTemperature();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public TemperaturaGiornaliera updateTemperatura(@PathVariable int id, @RequestBody @Validated TemperaturaGiornalieraDto temperaturaGiornalieraDto) throws NotFoundException {
        return temperaturaGiornalieraService.updateTemperatura(id,temperaturaGiornalieraDto);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public void deleteTemperatura(@PathVariable int id) throws NotFoundException {
        temperaturaGiornalieraService.deleteTemperatura(id);
    }


    @GetMapping("/frigoriferi/{frigo}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<TemperaturaGiornaliera> getTemperatureByFrigo(@PathVariable int frigo){
        return temperaturaGiornalieraService.findByFrigo(frigo);
    }

    @GetMapping("/data")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<TemperaturaGiornaliera> getTemperatureByData(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data){
        return temperaturaGiornalieraService.findByData(data);
    }

    @GetMapping("/range")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<TemperaturaGiornaliera> getTemperatureByRange(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return temperaturaGiornalieraService.findByDate(start, end);
    }


    @GetMapping("/conformita")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<TemperaturaGiornaliera> getTemperatureByConformita(@RequestParam("conformita") Conformita conformita){
        return temperaturaGiornalieraService.findByConformita(conformita);
    }


    @GetMapping("/{frigo}/range")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<TemperaturaGiornaliera> findByDateAndFrigo( @PathVariable int frigo,@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                                            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end){
        return temperaturaGiornalieraService.findByDateAndFrigo(frigo, start, end);

    }

    @GetMapping("/{frigo}/range/conformita")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<TemperaturaGiornaliera> findByAll(@PathVariable int frigo,@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                                  @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,@RequestParam("conformita") Conformita conformita){

        return temperaturaGiornalieraService.findByDateAndConformitaFrigo(frigo, conformita, start, end);
    }
    @GetMapping("/valore")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<TemperaturaGiornaliera> findByTemperatura(@RequestParam("temperatura") int temperatura){
        return temperaturaGiornalieraService.findByTemperatura(temperatura);
    }

}
