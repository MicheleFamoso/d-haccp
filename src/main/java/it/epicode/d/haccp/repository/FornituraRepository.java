package it.epicode.d.haccp.repository;

import it.epicode.d.haccp.model.Fornitura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FornituraRepository extends JpaRepository<Fornitura,Integer> {


    List<Fornitura> findByDataAndAziendaId(LocalDate data, int aziendaId);

    List<Fornitura> findByProdottoContainingIgnoreCaseAndAziendaId(String prodotto, int aziendaId);

    @Query("SELECT f FROM Fornitura f WHERE LOWER(f.fornitore.nomeFornitore) LIKE LOWER(CONCAT('%', :nomeFornitore, '%')) AND f.azienda.id = :aziendaId")
    List<Fornitura> findByNomeFornitoreContainingIgnoreCaseAndAziendaId(@Param("nomeFornitore") String nomeFornitore, @Param("aziendaId") int aziendaId);

    List<Fornitura> findByDataBetweenAndAziendaId(LocalDate start, LocalDate end, int aziendaId);
    List<Fornitura> findByAziendaId(int aziendaId);

    @Query("SELECT f FROM Fornitura f WHERE f.azienda.id = :aziendaId AND (" +
           "LOWER(f.fornitore.nomeFornitore) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(f.prodotto) LIKE LOWER(CONCAT('%', :query, '%')))")
    List<Fornitura> cercaPerProdottoOFornitore(@Param("query") String query, @Param("aziendaId") int aziendaId);
}
