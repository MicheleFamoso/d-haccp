package it.epicode.d.haccp.service;

import it.epicode.d.haccp.dto.FornitoreDto;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.Azienda;
import it.epicode.d.haccp.model.Fornitore;
import it.epicode.d.haccp.repository.AziendaRepository;
import it.epicode.d.haccp.repository.FornitoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornitoreService {

    @Autowired
    private FornitoreRepository fornitoreRepository;

    @Autowired
    private AziendaRepository aziendaRepository;

    public Fornitore createFornitore(FornitoreDto dto, int aziendaId) throws NotFoundException {
        Azienda azienda = aziendaRepository.findById(aziendaId)
                .orElseThrow(() -> new NotFoundException("Azienda non trovata"));

        Fornitore fornitore = new Fornitore();
        fornitore.setNomeFornitore(dto.getNomeFornitore());
        fornitore.setSede(dto.getSede());
        fornitore.setEmail(dto.getEmail());
        fornitore.setTelefono(dto.getTelefono());
        fornitore.setProdottiForniti(dto.getProdottiForniti());
        fornitore.setAzienda(azienda);

        return fornitoreRepository.save(fornitore);
    }

    public List<Fornitore> getAllFornitori(int aziendaId) {
        return fornitoreRepository.findByAziendaId(aziendaId);
    }

    public Fornitore getFornitoreById(int id) throws NotFoundException {
        return fornitoreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Fornitore non trovato con id: " + id));
    }

    public Fornitore updateFornitore(int id, FornitoreDto dto) throws NotFoundException {
        Fornitore fornitore = getFornitoreById(id);
        fornitore.setNomeFornitore(dto.getNomeFornitore());
        fornitore.setSede(dto.getSede());
        fornitore.setEmail(dto.getEmail());
        fornitore.setTelefono(dto.getTelefono());
        fornitore.setProdottiForniti(dto.getProdottiForniti());
        return fornitoreRepository.save(fornitore);
    }

    public void deleteFornitore(int id) throws NotFoundException {
        Fornitore fornitore = getFornitoreById(id);
        fornitoreRepository.delete(fornitore);
    }

    public List<Fornitore> findByProdotto(String prodotto, int aziendaId) {
        return fornitoreRepository.findByProdottiFornitiContainingAndAziendaId(prodotto, aziendaId);
    }

    public List<Fornitore> findByNomeFornitore(String nomeFornitore, int aziendaId) {
        return fornitoreRepository.findByNomeFornitoreContainingIgnoreCaseAndAziendaId(nomeFornitore, aziendaId);
    }
}
