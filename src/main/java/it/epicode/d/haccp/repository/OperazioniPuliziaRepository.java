package it.epicode.d.haccp.repository;

import it.epicode.d.haccp.model.OperazioniPulizia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface OperazioniPuliziaRepository extends JpaRepository<OperazioniPulizia,Integer> {

    List<OperazioniPulizia> findByAziendaId(Integer aziendaId);



    List<OperazioniPulizia> findByOggettoAndAziendaId(String oggetto, Integer aziendaId);

    List<OperazioniPulizia> findByFrequenzaAndAziendaId(String frequenza, Integer aziendaId);

    List<OperazioniPulizia> findByOggettoAndFrequenzaAndAziendaId(String oggetto, String frequenza, Integer aziendaId);

    @Query("SELECT p FROM OperazioniPulizia p WHERE p.azienda.id = :aziendaId AND (" +
           "LOWER(p.oggetto) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(p.frequenza) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(p.detergente) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(p.attrezzatureUtilizzate) LIKE LOWER(CONCAT('%', :query, '%')))")
    List<OperazioniPulizia> cercaInOggettoFrequenzaDetergente(
            @Param("query") String query,
            @Param("aziendaId") int aziendaId);
}
