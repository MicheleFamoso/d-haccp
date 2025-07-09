package it.epicode.d.haccp.repository;

import it.epicode.d.haccp.model.Fornitore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FornitoreRepository extends JpaRepository<Fornitore,Integer> {
    List<Fornitore> findByProdottiFornitiContainingAndAziendaId(String prodotto, int aziendaId);

    List<Fornitore> findByNomeFornitoreContainingIgnoreCaseAndAziendaId(String nomeFornitore, int aziendaId);
    List<Fornitore> findByAziendaId(int aziendaId);

    @Query("SELECT f FROM Fornitore f WHERE f.azienda.id = :aziendaId AND (" +
           "LOWER(f.nomeFornitore) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "EXISTS (SELECT p FROM f.prodottiForniti p WHERE LOWER(p) LIKE LOWER(CONCAT('%', :query, '%'))))")
    List<Fornitore> cercaPerNomeOFornitura(@Param("query") String query, @Param("aziendaId") int aziendaId);
}
