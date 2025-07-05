package it.epicode.d.haccp.repository;

import it.epicode.d.haccp.model.OperazioniPulizia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OperazioniPuliziaRepository extends JpaRepository<OperazioniPulizia,Integer> {

    List<OperazioniPulizia> findByAziendaId(Integer aziendaId);



    List<OperazioniPulizia> findByOggettoAndAziendaId(String oggetto, Integer aziendaId);

    List<OperazioniPulizia> findByFrequenzaAndAziendaId(String frequenza, Integer aziendaId);
}
