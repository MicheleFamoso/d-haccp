package it.epicode.d.haccp.service;


import it.epicode.d.haccp.dto.AziendaDto;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.Azienda;
import it.epicode.d.haccp.model.User;
import it.epicode.d.haccp.repository.AziendaRepository;
import it.epicode.d.haccp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AziendaService {

    @Autowired
    private AziendaRepository aziendaRepository;
    @Autowired
    private UserRepository userRepository;

    public Azienda createAzienda(AziendaDto dto,String adminUsername) throws NotFoundException {
        Azienda azienda = new Azienda();
        azienda.setDenominazioneAziendale(dto.getDenominazioneAziendale());
        azienda.setRagioneSociale(dto.getRagioneSociale());
        azienda.setTipologiaAttivita(dto.getTipologiaAttivita());
        azienda.setSedeOperativa(dto.getSedeOperativa());
        azienda.setPartitaIva(dto.getPartitaIva());
        azienda.setTelefono(dto.getTelefono());
        azienda.setEmail(dto.getEmail());
        Azienda salvata= aziendaRepository.save(azienda);
        User admin = userRepository.findByUsername(adminUsername)
                .orElseThrow(() -> new NotFoundException("Utente admin non trovato"));
        admin.setAzienda(salvata);
        userRepository.save(admin);

        return salvata;


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
