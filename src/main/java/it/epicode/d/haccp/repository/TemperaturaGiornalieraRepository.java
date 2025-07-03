package it.epicode.d.haccp.repository;

import it.epicode.d.haccp.enumeration.Conformita;
import it.epicode.d.haccp.model.TemperaturaGiornaliera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TemperaturaGiornalieraRepository extends JpaRepository<TemperaturaGiornaliera,Integer> {

    List<TemperaturaGiornaliera> findByFrigo(int frigo);
    List<TemperaturaGiornaliera> findByTemperatura(int temperatura);
    List<TemperaturaGiornaliera> findByData(LocalDate data);
    List<TemperaturaGiornaliera> findByConformita(Conformita conformita);
    List<TemperaturaGiornaliera> findByDataBetween(LocalDate start, LocalDate end);
    List<TemperaturaGiornaliera> findByFrigoAndDataBetween(int frigo, LocalDate start, LocalDate end);
    List<TemperaturaGiornaliera> findByFrigoAndConformitaAndDataBetween(int frigo, Conformita conformita, LocalDate start, LocalDate end);

    List<TemperaturaGiornaliera> findByDataBetweenAndAziendaId(LocalDate start, LocalDate end, int aziendaId);
    List<TemperaturaGiornaliera> findByDataAndAziendaId(LocalDate data, int aziendaId);
    List<TemperaturaGiornaliera> findByFrigoAndAziendaId(int frigo, int aziendaId);
    List<TemperaturaGiornaliera> findByTemperaturaAndAziendaId(int temperatura, int aziendaId);
    List<TemperaturaGiornaliera> findByConformitaAndAziendaId(Conformita conformita, int aziendaId);
    List<TemperaturaGiornaliera> findByFrigoAndDataBetweenAndAziendaId(int frigo, LocalDate start, LocalDate end, int aziendaId);
    List<TemperaturaGiornaliera> findByFrigoAndConformitaAndDataBetweenAndAziendaId(int frigo, Conformita conformita, LocalDate start, LocalDate end, int aziendaId);
}
