package it.epicode.d.haccp.service;

import it.epicode.d.haccp.dto.FornituraDto;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.Azienda;
import it.epicode.d.haccp.model.Fornitore;
import it.epicode.d.haccp.model.Fornitura;
import it.epicode.d.haccp.repository.AziendaRepository;
import it.epicode.d.haccp.repository.FornitoreRepository;
import it.epicode.d.haccp.repository.FornituraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FornituraService {

    @Autowired
    private FornituraRepository fornituraRepository;

    @Autowired
    private FornitoreRepository fornitoreRepository;

    @Autowired
    private AziendaRepository aziendaRepository;

    public Fornitura createFornitura(FornituraDto dto, int aziendaId) throws NotFoundException {
        Fornitore fornitore= fornitoreRepository.findById(dto.getFornitoreId())
                .orElseThrow(() -> new NotFoundException("Fornitore non trovato"));
        Azienda azienda = aziendaRepository.findById(aziendaId)
                .orElseThrow(() -> new NotFoundException("Azienda non trovata"));

        Fornitura fornitura = new Fornitura();
        fornitura.setData(dto.getData());
        fornitura.setFornitore(fornitore);
        fornitura.setProdotto(dto.getProdotto());
        fornitura.setConformita(dto.getConformita());
        fornitura.setLotto(dto.getLotto());
        fornitura.setAzienda(azienda);

        return fornituraRepository.save(fornitura);
    }

    public List<Fornitura> getAllForniture(int aziendaId) {
        return fornituraRepository.findByAziendaId(aziendaId);
    }

    public Fornitura getFornituraById(int id) throws NotFoundException {
        return fornituraRepository.findById(id).orElseThrow(() -> new NotFoundException("Fornitura non trovata"));
    }

    public Fornitura updateFornitura(int id,FornituraDto dto) throws NotFoundException {
        Fornitura fornitura = getFornituraById(id);
        Fornitore fornitore = fornitoreRepository.findById(dto.getFornitoreId())
                .orElseThrow(() -> new NotFoundException("Fornitore non trovato"));
        fornitura.setData(dto.getData());
        fornitura.setFornitore(fornitore);
        fornitura.setProdotto(dto.getProdotto());
        fornitura.setConformita(dto.getConformita());
        fornitura.setLotto(dto.getLotto());

        return fornituraRepository.save(fornitura);
    }

    public void deleteFornitura(int id) throws NotFoundException {
        Fornitura fornitura = getFornituraById(id);
        fornituraRepository.delete(fornitura);
    }

    public List<Fornitura> findByData(LocalDate data, int aziendaId) {
        return fornituraRepository.findByDataAndAziendaId(data, aziendaId);
    }

    public List<Fornitura> findByDataBetween(LocalDate start, LocalDate end, int aziendaId) {
        return fornituraRepository.findByDataBetweenAndAziendaId(start, end, aziendaId);
    }

    public List<Fornitura> findByProdottoContainingIgnoreCase(String prodotto, int aziendaId) {
        return fornituraRepository.findByProdottoContainingIgnoreCaseAndAziendaId(prodotto, aziendaId);
    }

    public List<Fornitura> findByNomeFornitoreContainingIgnoreCase(String nomeFornitore, int aziendaId) {
        return fornituraRepository.findByNomeFornitoreContainingIgnoreCaseAndAziendaId(nomeFornitore, aziendaId);
    }
}
