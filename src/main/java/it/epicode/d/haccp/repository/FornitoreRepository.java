package it.epicode.d.haccp.repository;

import it.epicode.d.haccp.model.Fornitore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FornitoreRepository extends JpaRepository<Fornitore,Integer> {
    List<Fornitore> findByProdottiFornitiContaining(String prodotto);

    List<Fornitore> findByNomeFornitoreContainingIgnoreCase(String nomeFornitore);

}
