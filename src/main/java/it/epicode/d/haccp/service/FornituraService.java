package it.epicode.d.haccp.service;


import it.epicode.d.haccp.dto.FornituraDto;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.Fornitore;
import it.epicode.d.haccp.model.Fornitura;
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

    public Fornitura createFornitura(FornituraDto dto) throws NotFoundException {
        Fornitore fornitore= fornitoreRepository.findById(dto.getFornitoreId()).orElseThrow(()->new NotFoundException("Fornitore non trovato"));
        Fornitura fornitura = new Fornitura();
        fornitura.setData(dto.getData());
        fornitura.setFornitore(fornitore);
        fornitura.setProdotto(dto.getProdotto());
        fornitura.setConformita(dto.getConformita());
        fornitura.setLotto(dto.getLotto());

        return fornituraRepository.save(fornitura);

    }
    public List<Fornitura> getAllForniture() {
        return fornituraRepository.findAll();
    }

    public Fornitura getFornituraById(int id) throws NotFoundException {
        return fornituraRepository.findById(id).orElseThrow(()-> new NotFoundException("Fornitura non trovata"));
    }

    public Fornitura updateFornitura(int id,FornituraDto dto) throws NotFoundException {
        Fornitura fornitura = getFornituraById(id);
        Fornitore fornitore = fornitoreRepository.findById(dto.getFornitoreId()).orElseThrow(()->new NotFoundException("Fornitore non trovato"));
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
    public List<Fornitura> findByData(LocalDate data) {
        return fornituraRepository.findByData(data);
    }
    public List<Fornitura> findByDataBetween(LocalDate start, LocalDate end) {
        return fornituraRepository.findByDataBetween(start, end);
    }
    public List<Fornitura> findByProdottoContainingIgnoreCase(String prodotto) {
        return fornituraRepository.findByProdottoContainingIgnoreCase(prodotto);
    }
    public List<Fornitura> findByNomeFornitoreContainingIgnoreCase(String nomeFornitore) {
        return fornituraRepository.findByNomeFornitoreContainingIgnoreCase(nomeFornitore);
    }
}
