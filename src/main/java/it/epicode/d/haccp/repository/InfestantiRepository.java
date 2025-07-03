package it.epicode.d.haccp.repository;

import it.epicode.d.haccp.enumeration.Conformita;
import it.epicode.d.haccp.model.Infestanti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface InfestantiRepository extends JpaRepository<Infestanti,Integer> {

    @Query("SELECT i FROM Infestanti i WHERE (i.roditori = 'NON_CONFORME' OR i.insettiStriscianti = 'NON_CONFORME' OR i.insettiVolanti = 'NON_CONFORME') AND i.azienda.id = :aziendaId")
    List<Infestanti> findControlliNonConformiByAziendaId(@Param("aziendaId") int aziendaId);

    @Query("SELECT i FROM Infestanti i WHERE (i.roditori = 'CONFORME' OR i.insettiStriscianti = 'CONFORME' OR i.insettiVolanti = 'CONFORME') AND i.azienda.id = :aziendaId")
    List<Infestanti> findControlliConformiByAziendaId(@Param("aziendaId") int aziendaId);

    List<Infestanti> findByDataAndAziendaId(LocalDate data, int aziendaId);

    List<Infestanti> findByDataBetweenAndAziendaId(LocalDate start, LocalDate end, int aziendaId);

    List<Infestanti> findByRoditoriAndAziendaId(Conformita conformita, int aziendaId);

    List<Infestanti> findByInsettiStrisciantiAndAziendaId(Conformita conformita, int aziendaId);

    List<Infestanti> findByInsettiVolantiAndAziendaId(Conformita conformita, int aziendaId);

    List<Infestanti> findByAziendaId(int aziendaId);
}
