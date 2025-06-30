package it.epicode.d.haccp.service;


import it.epicode.d.haccp.dto.AziendaDto;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.Azienda;
import it.epicode.d.haccp.model.User;
import it.epicode.d.haccp.repository.AziendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AziendaService {

    @Autowired
    private AziendaRepository aziendaRepository;


    public Azienda createAzienda(AziendaDto dto){
        Azienda azienda = new Azienda();
        azienda.setDenominazioneAziendale(dto.getDenominazioneAziendale());
        azienda.setRagioneSociale(dto.getRagioneSociale());
        azienda.setTipologiaAttivita(dto.getTipologiaAttivita());
        azienda.setSedeOperativa(dto.getSedeOperativa());
        azienda.setPartitaIva(dto.getPartitaIva());
        azienda.setTelefono(dto.getTelefono());
        azienda.setEmail(dto.getEmail());


        return aziendaRepository.save(azienda);
    }


    public List<Azienda> getAllAziende(){
        return aziendaRepository.findAll();
    }

    public Azienda getAzienda(int id) throws NotFoundException {
        return aziendaRepository.findById(id).orElseThrow(()->new NotFoundException("Azienda con id " + id +" non trovata."));
    }



    public Azienda updateAzienda(int id,AziendaDto dto) throws NotFoundException {
        Azienda azienda= getAzienda(id);

            azienda.setDenominazioneAziendale(dto.getDenominazioneAziendale());

            azienda.setRagioneSociale(dto.getRagioneSociale());
            azienda.setTipologiaAttivita(dto.getTipologiaAttivita());

            azienda.setSedeOperativa(dto.getSedeOperativa());

            azienda.setPartitaIva(dto.getPartitaIva());

            azienda.setTelefono(dto.getTelefono());

            azienda.setEmail(dto.getEmail());
        return aziendaRepository.save(azienda);
    }

    public void deleteAzienda(int id) throws NotFoundException {
        Azienda azienda = getAzienda(id);
        aziendaRepository.delete(azienda);
    }
}
