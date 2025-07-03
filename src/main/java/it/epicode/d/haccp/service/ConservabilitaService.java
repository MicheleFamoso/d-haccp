package it.epicode.d.haccp.service;

import it.epicode.d.haccp.dto.ConservabilitaDto;
import it.epicode.d.haccp.enumeration.TipoConservazione;
import it.epicode.d.haccp.enumeration.TipoProdotto;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.Azienda;
import it.epicode.d.haccp.model.Conservabilita;
import it.epicode.d.haccp.repository.AziendaRepository;
import it.epicode.d.haccp.repository.ConservabilitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class ConservabilitaService {

    @Autowired
    private ConservabilitaRepository conservabilitaRepository;

    @Autowired
    private AziendaRepository aziendaRepository;

    public Conservabilita createConservabilita(ConservabilitaDto dto, int aziendaId) throws NotFoundException {
        Azienda azienda = aziendaRepository.findById(aziendaId)
            .orElseThrow(() -> new NotFoundException("Azienda non trovata"));

        Conservabilita conservabilita = new Conservabilita();
        conservabilita.setProdotto(dto.getProdotto());
        conservabilita.setTipoProdotto(dto.getTipoProdotto());
        conservabilita.setTipoConservazione(dto.getTipoConservazione());
        conservabilita.setNote(dto.getNote());
        conservabilita.setGiorniMassimaConservazione(dto.getGiorniMassimaConservazione());
        conservabilita.setAzienda(azienda);

        return conservabilitaRepository.save(conservabilita);
    }

    public List<Conservabilita> getAllConservabilita(int aziendaId){
        return conservabilitaRepository.findByAziendaId(aziendaId);
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

    public List<Conservabilita> findByTipoProdotto(TipoProdotto tipoProdotto, int aziendaId){
        return conservabilitaRepository.findByTipoProdottoAndAziendaId(tipoProdotto, aziendaId);
    }

    public List<Conservabilita> findByTipoConservazione(TipoConservazione tipoConservazione, int aziendaId){
        return conservabilitaRepository.findByTipoConservazioneAndAziendaId(tipoConservazione, aziendaId);
    }

    public List<Conservabilita> findByGiorniConservazione(int giorni, int aziendaId){
        return conservabilitaRepository.findByGiorniMassimaConservazioneAndAziendaId(giorni, aziendaId);
    }

    public List<Conservabilita> findByRangeGiorni(int min, int max, int aziendaId){
        return conservabilitaRepository.findByGiorniMassimaConservazioneBetweenAndAziendaId(min, max, aziendaId);
    }

    public List<Conservabilita> findByTipoProdottoEConservazione(TipoConservazione tipoConservazione,TipoProdotto tipoProdotto, int aziendaId){
        return conservabilitaRepository.findByTipoConservazioneAndTipoProdottoAndAziendaId(tipoConservazione, tipoProdotto, aziendaId);
    }

    public List<Conservabilita> findByProdotto(String nome, int aziendaId){
        return conservabilitaRepository.findByProdottoContainingIgnoreCaseAndAziendaId(nome, aziendaId);
    }
}
