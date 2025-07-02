package it.epicode.d.haccp.service;


import it.epicode.d.haccp.dto.FornitoreDto;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.Fornitore;
import it.epicode.d.haccp.repository.FornitoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornitoreService {

    @Autowired
    private FornitoreRepository fornitoreRepository;


    public Fornitore createFornitore(FornitoreDto dto){
        Fornitore fornitore = new Fornitore();
        fornitore.setNomeFornitore(dto.getNomeFornitore());
        fornitore.setSede(dto.getSede());
        fornitore.setEmail(dto.getEmail());
        fornitore.setTelefono(dto.getTelefono());
        fornitore.setProdottiForniti(dto.getProdottiForniti());
        return fornitoreRepository.save(fornitore);
    }
    public List<Fornitore> getAllFornitori() {
        return fornitoreRepository.findAll();
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
    public List<Fornitore> findByProdotto(String prodotto) {
        return fornitoreRepository.findByProdottiFornitiContaining(prodotto);
    }

    public List<Fornitore> findByNomeFornitore(String nomeFornitore) {
        return fornitoreRepository.findByNomeFornitoreContainingIgnoreCase(nomeFornitore);
    }

}
