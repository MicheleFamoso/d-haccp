package it.epicode.d.haccp.controller;


import it.epicode.d.haccp.dto.FornituraDto;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.Fornitura;
import it.epicode.d.haccp.service.FornituraService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/forniture")
public class FornituraController {

    @Autowired
    private FornituraService service;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Fornitura saveFornitura(@RequestBody @Validated FornituraDto dto, BindingResult bindingResult) throws NotFoundException {
        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s,e)->s+e));
        }
        return service.createFornitura(dto);
    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Fornitura> getAllForniture() {
        return service.getAllForniture();
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
    public List<Fornitura> findByData(@RequestParam("data") LocalDate data) {
        return service.findByData(data);
    }

    @GetMapping("/range")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Fornitura> findByDataBetween(@RequestParam("start") LocalDate start, @RequestParam("end") LocalDate end) {
        return service.findByDataBetween(start, end);
    }

    @GetMapping("/prodotto")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Fornitura> findByProdotto(@RequestParam("prodotto") String prodotto) {
        return service.findByProdottoContainingIgnoreCase(prodotto);
    }

    @GetMapping("/fornitore")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Fornitura> findByNomeFornitore(@RequestParam("nomeFornitore") String nomeFornitore) {
        return service.findByNomeFornitoreContainingIgnoreCase(nomeFornitore);
    }


}
