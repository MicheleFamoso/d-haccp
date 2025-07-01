package it.epicode.d.haccp.service;


import it.epicode.d.haccp.dto.InfestantiDto;
import it.epicode.d.haccp.enumeration.Conformita;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.Infestanti;
import it.epicode.d.haccp.repository.InfestantiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InfestantiService {
    @Autowired
    private InfestantiRepository infestantiRepository;

    public Infestanti saveInfestanti(InfestantiDto dto){
        Infestanti infestanti = new Infestanti();
        infestanti.setData(dto.getData());
        infestanti.setRoditori(dto.getRoditori());
        infestanti.setInsettiStriscianti(dto.getInsettiStriscianti());
        infestanti.setInsettiVolanti(dto.getInsettiVolanti());
        return infestantiRepository.save(infestanti);
    }

    public List<Infestanti>getAllInfestanti(){
        return infestantiRepository.findAll();
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

    public List<Infestanti> findInfestantiByData(LocalDate data){
        return infestantiRepository.findByData(data);
    }

    public List<Infestanti> findInfestantiByDate(LocalDate start,LocalDate end){
        return infestantiRepository.findByDataBetween(start, end);
    }

    public List<Infestanti> findByRoditori(Conformita conformita){
        return infestantiRepository.findByRoditori(conformita);
    }

    public List<Infestanti> findByInsettiStriscianti(Conformita conformita){
        return infestantiRepository.findByInsettiStriscianti(conformita);
    }

    public List<Infestanti> findByInsettiVolanti(Conformita conformita){
        return infestantiRepository.findByInsettiVolanti(conformita);
    }

    public List<Infestanti> findByConformi(){
        return infestantiRepository.findControlliConformi();
    }
    public List<Infestanti> findByNonConformi(){
        return infestantiRepository.findControlliNonConformi();
    }
}
