package it.epicode.d.haccp.service;


import it.epicode.d.haccp.dto.OperazioniPuliziaDto;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.OperazioniPulizia;
import it.epicode.d.haccp.repository.OperazioniPuliziaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperazioniRepositoryService {


    @Autowired
    private OperazioniPuliziaRepository operazioniPuliziaRepository;


    public OperazioniPulizia createPulizia(OperazioniPuliziaDto dto){
        OperazioniPulizia pulizia = new OperazioniPulizia();
        pulizia.setFrequenza(dto.getFrequenza());
        pulizia.setDetergente(dto.getDetergente());
        pulizia.setOggetto(dto.getOggetto());
        pulizia.setAttrezzatureUtilizzate(dto.getAttrezzatureUtilizzate());
        return operazioniPuliziaRepository.save(pulizia);
    }

    public List<OperazioniPulizia> getOperazioniPulizia(){
        return operazioniPuliziaRepository.findAll();
    }

    public OperazioniPulizia getOperazioniPuliziaById(int id) throws NotFoundException {
        return operazioniPuliziaRepository.findById(id).orElseThrow(()->new NotFoundException("Operazione non trovata"))   ;
    }

    public OperazioniPulizia updatePulizia(int id,OperazioniPuliziaDto dto) throws NotFoundException {
        OperazioniPulizia pulizia = getOperazioniPuliziaById(id);
        pulizia.setFrequenza(dto.getFrequenza());
        pulizia.setDetergente(dto.getDetergente());
        pulizia.setOggetto(dto.getOggetto());
        pulizia.setAttrezzatureUtilizzate(dto.getAttrezzatureUtilizzate());
        return operazioniPuliziaRepository.save(pulizia);
    }

    public void deletePulizia(int id) throws NotFoundException {
        OperazioniPulizia pulizia = getOperazioniPuliziaById(id);
        operazioniPuliziaRepository.delete(pulizia);
    }

}
