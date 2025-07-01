package it.epicode.d.haccp.service;


import it.epicode.d.haccp.dto.TemperaturaGiornalieraDto;
import it.epicode.d.haccp.enumeration.Conformita;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.TemperaturaGiornaliera;
import it.epicode.d.haccp.repository.TemperaturaGiornalieraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TemperaturaGiornalieraService {

    @Autowired
    private TemperaturaGiornalieraRepository temperaturaGiornalieraRepository;



    public TemperaturaGiornaliera createTemperatura(TemperaturaGiornalieraDto dto){
        TemperaturaGiornaliera temperaturaGiornaliera = new TemperaturaGiornaliera();
        temperaturaGiornaliera.setFrigo(dto.getFrigo());
        temperaturaGiornaliera.setData(dto.getData());
        temperaturaGiornaliera.setTemperatura(dto.getTemperatura());
        temperaturaGiornaliera.setConformita(dto.getConformita());
        return temperaturaGiornalieraRepository.save(temperaturaGiornaliera);
    }

    public List<TemperaturaGiornaliera> getAllTemperature(){
        return temperaturaGiornalieraRepository.findAll();
    }

    public TemperaturaGiornaliera getTemperaturaById(int id) throws NotFoundException {
        return temperaturaGiornalieraRepository.findById(id).orElseThrow(()->new NotFoundException("Temperatura non trovata"));
    }

    public TemperaturaGiornaliera updateTemperatura(int id, TemperaturaGiornalieraDto dto) throws NotFoundException {
        TemperaturaGiornaliera temperaturaGiornaliera = getTemperaturaById(id);
        temperaturaGiornaliera.setFrigo(dto.getFrigo());
        temperaturaGiornaliera.setData(dto.getData());
        temperaturaGiornaliera.setTemperatura(dto.getTemperatura());
        temperaturaGiornaliera.setConformita(dto.getConformita());
        return temperaturaGiornalieraRepository.save(temperaturaGiornaliera);
    }

    public void deleteTemperatura(int id) throws NotFoundException {
        TemperaturaGiornaliera temperaturaGiornaliera = getTemperaturaById(id);
        temperaturaGiornalieraRepository.delete(temperaturaGiornaliera);
    }

    public List<TemperaturaGiornaliera> findByFrigo(int frigo){
        return temperaturaGiornalieraRepository.findByFrigo(frigo);
    }

    public List<TemperaturaGiornaliera> findByData(LocalDate data){
        return temperaturaGiornalieraRepository.findByData(data);
    }

    public List<TemperaturaGiornaliera> findByConformita(Conformita conformita){
        return temperaturaGiornalieraRepository.findByConformita(conformita);
    }


    public List<TemperaturaGiornaliera> findByDate(LocalDate start,LocalDate end){
        return temperaturaGiornalieraRepository.findByDataBetween(start, end);
    }

    public List<TemperaturaGiornaliera> findByDateAndFrigo(int frigo ,LocalDate start,LocalDate end){
        return temperaturaGiornalieraRepository.findByFrigoAndDataBetween(frigo, start, end);
    }

    public List<TemperaturaGiornaliera> findByDateAndConformitaFrigo( int frigo,Conformita conformita ,LocalDate start,LocalDate end){
        return temperaturaGiornalieraRepository.findByFrigoAndConformitaAndDataBetween(frigo, conformita, start, end);
    }

    public List<TemperaturaGiornaliera> findByTemperatura(int temperatura){
        return temperaturaGiornalieraRepository.findByTemperatura(temperatura);
    }



}
