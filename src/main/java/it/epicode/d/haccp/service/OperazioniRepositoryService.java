package it.epicode.d.haccp.service;

import it.epicode.d.haccp.dto.OperazioniPuliziaDto;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.Azienda;
import it.epicode.d.haccp.model.OperazioniPulizia;
import it.epicode.d.haccp.repository.AziendaRepository;
import it.epicode.d.haccp.repository.OperazioniPuliziaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperazioniRepositoryService {

    @Autowired
    private OperazioniPuliziaRepository operazioniPuliziaRepository;

    @Autowired
    private AziendaRepository aziendaRepository;

    public OperazioniPulizia createPulizia(OperazioniPuliziaDto dto, int aziendaId) throws NotFoundException {
        Azienda azienda = aziendaRepository.findById(aziendaId)
                .orElseThrow(() -> new NotFoundException("Azienda non trovata"));

        OperazioniPulizia pulizia = new OperazioniPulizia();
        pulizia.setFrequenza(dto.getFrequenza());
        pulizia.setDetergente(dto.getDetergente());
        pulizia.setOggetto(dto.getOggetto());
        pulizia.setAttrezzatureUtilizzate(dto.getAttrezzatureUtilizzate());
        pulizia.setAzienda(azienda);

        return operazioniPuliziaRepository.save(pulizia);
    }

    public List<OperazioniPulizia> getOperazioniPulizia(int aziendaId) {
        return operazioniPuliziaRepository.findByAziendaId(aziendaId);
    }

    public OperazioniPulizia getOperazioniPuliziaById(int id) throws NotFoundException {
        return operazioniPuliziaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Operazione non trovata"));
    }

    public OperazioniPulizia updatePulizia(int id, OperazioniPuliziaDto dto) throws NotFoundException {
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






    public List<OperazioniPulizia> findByOggettoAndAziendaId(String oggetto, int aziendaId) {
        return operazioniPuliziaRepository.findByOggettoAndAziendaId(oggetto, aziendaId);
    }

    public List<OperazioniPulizia> findByFrequenzaAndAziendaId(String frequenza, int aziendaId) {
        return operazioniPuliziaRepository.findByFrequenzaAndAziendaId(frequenza, aziendaId);
    }

}
