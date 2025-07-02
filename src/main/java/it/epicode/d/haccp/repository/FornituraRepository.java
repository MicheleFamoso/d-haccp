package it.epicode.d.haccp.repository;

import it.epicode.d.haccp.model.Fornitura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FornituraRepository extends JpaRepository<Fornitura,Integer> {

    List<Fornitura> findByData(LocalDate data);
    List<Fornitura> findByProdottoContainingIgnoreCase(String prodotto);

    @Query("SELECT f FROM Fornitura f WHERE LOWER(f.fornitore.nomeFornitore) LIKE LOWER(CONCAT('%', :nomeFornitore, '%'))")
    List<Fornitura> findByNomeFornitoreContainingIgnoreCase(@Param("nomeFornitore") String nomeFornitore);


    List<Fornitura> findByDataBetween(LocalDate start, LocalDate end);
}
