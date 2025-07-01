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
}
