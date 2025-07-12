package it.epicode.d.haccp.service;


import it.epicode.d.haccp.dto.InfestantiDto;
import it.epicode.d.haccp.enumeration.Conformita;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.Azienda;
import it.epicode.d.haccp.model.Infestanti;
import it.epicode.d.haccp.repository.AziendaRepository;
import it.epicode.d.haccp.repository.InfestantiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InfestantiService {
    @Autowired
    private InfestantiRepository infestantiRepository;
    @Autowired
    private AziendaRepository aziendaRepository;

    public Infestanti saveInfestanti(InfestantiDto dto,int aziendaId) throws NotFoundException {
        Azienda azienda = aziendaRepository.findById(aziendaId).orElseThrow(()-> new NotFoundException("Azienda non trovata"));
        Infestanti infestanti = new Infestanti();
        infestanti.setData(dto.getData());
        infestanti.setRoditori(dto.getRoditori());
        infestanti.setInsettiStriscianti(dto.getInsettiStriscianti());
        infestanti.setInsettiVolanti(dto.getInsettiVolanti());
        infestanti.setAzienda(azienda);
        return infestantiRepository.save(infestanti);
    }

    public List<Infestanti>getAllInfestanti(int aziendaId){
        return infestantiRepository.findByAziendaId(aziendaId);
    }
    public Infestanti getInfestantiById(int id) throws NotFoundException {
        return infestantiRepository.findById(id).orElseThrow(()->new NotFoundException("Infestante non trovato"));
    }
    public Infestanti updateInfestante(int id,InfestantiDto dto) throws NotFoundException {
        Infestanti infestanti = getInfestantiById(id);
        infestanti.setData(dto.getData());
        infestanti.setRoditori(dto.getRoditori());
        infestanti.setInsettiStriscianti(dto.getInsettiStriscianti());
        infestanti.setInsettiVolanti(dto.getInsettiVolanti());
        return infestantiRepository.save(infestanti);
    }
    public void deleteInfestante(int id) throws NotFoundException {
        Infestanti infestanti = getInfestantiById(id);
        infestantiRepository.delete(infestanti);
    }

    public List<Infestanti> findInfestantiByData(LocalDate data, int aziendaId) {
        return infestantiRepository.findByDataAndAziendaId(data, aziendaId);
    }

    public List<Infestanti> findInfestantiByDate(LocalDate start, LocalDate end, int aziendaId) {
        return infestantiRepository.findByDataBetweenAndAziendaId(start, end, aziendaId);
    }

    public List<Infestanti> findByRoditori(Conformita conformita, int aziendaId) {
        return infestantiRepository.findByRoditoriAndAziendaId(conformita, aziendaId);
    }

    public List<Infestanti> findByInsettiStriscianti(Conformita conformita, int aziendaId) {
        return infestantiRepository.findByInsettiStrisciantiAndAziendaId(conformita, aziendaId);
    }

    public List<Infestanti> findByInsettiVolanti(Conformita conformita, int aziendaId) {
        return infestantiRepository.findByInsettiVolantiAndAziendaId(conformita, aziendaId);
    }

    public List<Infestanti> findByConformi(int aziendaId) {
        return infestantiRepository.findControlliConformiByAziendaId(aziendaId);
    }

    public List<Infestanti> findByNonConformi(int aziendaId) {
        return infestantiRepository.findControlliNonConformiByAziendaId(aziendaId);
    }

    public List<Infestanti> findAllConformiByAziendaId(int aziendaId) {
        return infestantiRepository.findAllConformiByAziendaId(aziendaId);
    }

    public List<Infestanti> findAllNonConformiByAziendaId(int aziendaId) {
        return infestantiRepository.findAllNonConformiByAziendaId(aziendaId);
    }
}
