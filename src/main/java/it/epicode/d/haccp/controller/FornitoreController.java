package it.epicode.d.haccp.controller;


import it.epicode.d.haccp.dto.FornitoreDto;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.Fornitore;
import it.epicode.d.haccp.service.FornitoreService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.ProtectionDomain;
import java.util.List;

@RestController
@RequestMapping("/fornitori")
public class FornitoreController {
    @Autowired
    private FornitoreService fornitoreService;


    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Fornitore saveFornitore(@RequestBody @Validated FornitoreDto fornitoreDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s,e)->s+e));
        }
        return fornitoreService.createFornitore(fornitoreDto);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Fornitore> getAllFornitori(){
        return fornitoreService.getAllFornitori();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Fornitore updateFornitore(@RequestBody @Validated FornitoreDto fornitoreDto, @PathVariable int id) throws NotFoundException {
        return fornitoreService.updateFornitore(id,fornitoreDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteFornitore(@PathVariable int id) throws NotFoundException {
        fornitoreService.deleteFornitore(id);
    }

    @GetMapping("/prodotto")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Fornitore> getFornitoreByProdotto(@RequestParam("prodotto") String prodotto){
        return fornitoreService.findByProdotto(prodotto);
    }
    @GetMapping("/fornitore")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Fornitore> getFornitore(@RequestParam("fornitore") String fornitore){
        return fornitoreService.findByNomeFornitore(fornitore);
    }

}
