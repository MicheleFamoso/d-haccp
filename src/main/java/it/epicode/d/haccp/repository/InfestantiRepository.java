package it.epicode.d.haccp.repository;

import it.epicode.d.haccp.enumeration.Conformita;
import it.epicode.d.haccp.model.Infestanti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface InfestantiRepository extends JpaRepository<Infestanti,Integer> {
    List<Infestanti> findByData(LocalDate data);

    List<Infestanti> findByDataBetween(LocalDate start, LocalDate end);

    List<Infestanti> findByRoditori(Conformita conformita);
    List<Infestanti> findByInsettiStriscianti(Conformita conformita);
    List<Infestanti> findByInsettiVolanti(Conformita conformita);

    @Query("SELECT i FROM Infestanti i WHERE i.roditori = 'NON_CONFORME' OR i.insettiStriscianti = 'NON_CONFORME' OR i.insettiVolanti = 'NON_CONFORME'")
    List<Infestanti> findControlliNonConformi();

    @Query("SELECT i FROM Infestanti i WHERE i.roditori = 'CONFORME' OR i.insettiStriscianti = 'CONFORME' OR i.insettiVolanti = 'CONFORME'")
    List<Infestanti> findControlliConformi();
}
