package it.epicode.d.haccp.service;


import it.epicode.d.haccp.dto.TemperaturaGiornalieraDto;
import it.epicode.d.haccp.enumeration.Conformita;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.TemperaturaGiornaliera;
import it.epicode.d.haccp.model.Azienda;
import it.epicode.d.haccp.repository.TemperaturaGiornalieraRepository;
import it.epicode.d.haccp.repository.AziendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TemperaturaGiornalieraService {

    @Autowired
    private TemperaturaGiornalieraRepository temperaturaGiornalieraRepository;

    @Autowired
    private AziendaRepository aziendaRepository;



    public TemperaturaGiornaliera createTemperatura(TemperaturaGiornalieraDto dto, int aziendaId) throws NotFoundException {
        Azienda azienda = aziendaRepository.findById(aziendaId)
                .orElseThrow(() -> new NotFoundException("Azienda non trovata"));

        TemperaturaGiornaliera temperaturaGiornaliera = new TemperaturaGiornaliera();
        temperaturaGiornaliera.setFrigo(dto.getFrigo());
        temperaturaGiornaliera.setData(dto.getData());
        temperaturaGiornaliera.setTemperatura(dto.getTemperatura());
        temperaturaGiornaliera.setConformita(dto.getConformita());
        temperaturaGiornaliera.setAzienda(azienda);

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

    public List<TemperaturaGiornaliera> findByFrigo(int frigo, int aziendaId){
        return temperaturaGiornalieraRepository.findByFrigoAndAziendaId(frigo, aziendaId);
    }

    public List<TemperaturaGiornaliera> findByData(LocalDate data, int aziendaId){
        return temperaturaGiornalieraRepository.findByDataAndAziendaId(data, aziendaId);
    }

    public List<TemperaturaGiornaliera> findByConformita(Conformita conformita, int aziendaId){
        return temperaturaGiornalieraRepository.findByConformitaAndAziendaId(conformita, aziendaId);
    }


    public List<TemperaturaGiornaliera> findByDate(LocalDate start,LocalDate end, int aziendaId){
        return temperaturaGiornalieraRepository.findByDataBetweenAndAziendaId(start, end, aziendaId);
    }

    public List<TemperaturaGiornaliera> findByDateAndFrigo(int frigo ,LocalDate start,LocalDate end, int aziendaId){
        return temperaturaGiornalieraRepository.findByFrigoAndDataBetweenAndAziendaId(frigo, start, end, aziendaId);
    }

    public List<TemperaturaGiornaliera> findByDateAndConformitaFrigo( int frigo,Conformita conformita ,LocalDate start,LocalDate end, int aziendaId){
        return temperaturaGiornalieraRepository.findByFrigoAndConformitaAndDataBetweenAndAziendaId(frigo, conformita, start, end, aziendaId);
    }

    public List<TemperaturaGiornaliera> findByTemperatura(int temperatura, int aziendaId){
        return temperaturaGiornalieraRepository.findByTemperaturaAndAziendaId(temperatura, aziendaId);
    }



}
