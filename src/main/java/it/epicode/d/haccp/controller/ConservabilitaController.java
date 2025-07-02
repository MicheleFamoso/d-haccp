package it.epicode.d.haccp.controller;


import it.epicode.d.haccp.dto.ConservabilitaDto;
import it.epicode.d.haccp.enumeration.TipoConservazione;
import it.epicode.d.haccp.enumeration.TipoProdotto;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.Conservabilita;

import it.epicode.d.haccp.service.ConservabilitaService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conservabilita")
public class ConservabilitaController {
    @Autowired
    private ConservabilitaService conservabilitaService;


    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Conservabilita saveConservabilita(@RequestBody @Validated ConservabilitaDto conservabilitaDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s,e)->s+e));
        }
        return conservabilitaService.createConservabilita(conservabilitaDto);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Conservabilita> getAllConservabilita(){
        return conservabilitaService.getAllConservabilita();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public void deleteInfestanti( @PathVariable int id) throws NotFoundException {
        conservabilitaService.deleteConservabilita(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Conservabilita updateConservabilita(@PathVariable int id,@RequestBody @Validated ConservabilitaDto conservabilitaDto) throws NotFoundException {
        return conservabilitaService.updateConservabilita(conservabilitaDto,id);
    }

    @GetMapping("/prodotto/tipo")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Conservabilita> findByTipoProdotto(@RequestParam("tipo") TipoProdotto tipoProdotto){
        return conservabilitaService.findByTipoProdotto(tipoProdotto);
    }

    @GetMapping("/conservazione/tipo")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Conservabilita> findByTipoConservazione(@RequestParam("tipo") TipoConservazione tipoConservazione){
        return conservabilitaService.findByTipoConservazione(tipoConservazione);
    }

    @GetMapping("/conservazione/giorni")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Conservabilita> findByGiorniConservazione(@RequestParam("giorni") int giorni){
        return conservabilitaService.findByGiorniConservazione(giorni);
    }

    @GetMapping("/conservazione/range")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Conservabilita> findByRangeGiorniConservazione(@RequestParam("min") int min,
                                                               @RequestParam("max") int max){
        return conservabilitaService.findByRangeGiorni(min, max);
    }
    @GetMapping("/prodotto-e-conservazione")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Conservabilita> findByTipoProdottoEConservazione(@RequestParam("tipoProdotto") TipoProdotto tipoProdotto,
                                                                 @RequestParam("tipoConservazione") TipoConservazione tipoConservazione){
        return conservabilitaService.findByTipoProdottoEConservazione(tipoConservazione, tipoProdotto);
    }
    @GetMapping("/prodotto")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Conservabilita> findByNomeProdotto(@RequestParam("nome") String nome){
        return conservabilitaService.findByProdotto(nome);
    }


}
