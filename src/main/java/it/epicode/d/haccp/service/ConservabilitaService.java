package it.epicode.d.haccp.service;


import it.epicode.d.haccp.dto.ConservabilitaDto;
import it.epicode.d.haccp.enumeration.TipoConservazione;
import it.epicode.d.haccp.enumeration.TipoProdotto;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.Conservabilita;
import it.epicode.d.haccp.repository.ConservabilitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class ConservabilitaService {

@Autowired
    private ConservabilitaRepository conservabilitaRepository;


    public Conservabilita createConservabilita(ConservabilitaDto dto){
        Conservabilita conservabilita = new Conservabilita();
        conservabilita.setProdotto(dto.getProdotto());
        conservabilita.setTipoProdotto(dto.getTipoProdotto());
        conservabilita.setTipoConservazione(dto.getTipoConservazione());
        conservabilita.setNote(dto.getNote());
        conservabilita.setGiorniMassimaConservazione(dto.getGiorniMassimaConservazione());
        return conservabilitaRepository.save(conservabilita);
    }

    public List<Conservabilita> getAllConservabilita(){
        return conservabilitaRepository.findAll();
    }

    public Conservabilita getConservabilitaById(int id) throws NotFoundException {
        return conservabilitaRepository.findById(id).orElseThrow(()->new NotFoundException("Prodotto non trovato"));
    }

    public Conservabilita updateConservabilita(ConservabilitaDto dto,int id) throws NotFoundException {
        Conservabilita conservabilita = getConservabilitaById(id);
        conservabilita.setProdotto(dto.getProdotto());
        conservabilita.setTipoProdotto(dto.getTipoProdotto());
        conservabilita.setTipoConservazione(dto.getTipoConservazione());
        conservabilita.setNote(dto.getNote());
        conservabilita.setGiorniMassimaConservazione(dto.getGiorniMassimaConservazione());
        return conservabilitaRepository.save(conservabilita);
    }

    public void deleteConservabilita(int id) throws NotFoundException {
        Conservabilita conservabilita = getConservabilitaById(id);
        conservabilitaRepository.delete(conservabilita);
    }

    public List<Conservabilita> findByTipoProdotto(TipoProdotto tipoProdotto){
        return conservabilitaRepository.findByTipoProdotto(tipoProdotto);
    }

    public List<Conservabilita> findByTipoConservazione(TipoConservazione tipoConservazione){
        return conservabilitaRepository.findByTipoConservazione(tipoConservazione);
    }

    public List<Conservabilita> findByGiorniConservazione(int giorni){
        return conservabilitaRepository.findByGiorniMassimaConservazione(giorni);
    }
    public List<Conservabilita> findByRangeGiorni(int min, int max){
        return conservabilitaRepository.findByGiorniMassimaConservazioneBetween(min, max);
    }
    public List<Conservabilita> findByTipoProdottoEConservazione(TipoConservazione tipoConservazione,TipoProdotto tipoProdotto){
        return conservabilitaRepository.findByTipoConservazioneAndTipoProdotto(tipoConservazione, tipoProdotto);
    }
    public List<Conservabilita> findByProdotto(String nome){
        return conservabilitaRepository.findByProdottoContainingIgnoreCase(nome);
    }


}
