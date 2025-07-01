package it.epicode.d.haccp.repository;

import it.epicode.d.haccp.enumeration.TipoConservazione;
import it.epicode.d.haccp.enumeration.TipoProdotto;
import it.epicode.d.haccp.model.Conservabilita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConservabilitaRepository extends JpaRepository<Conservabilita,Integer> {
    List<Conservabilita> findByTipoConservazione(TipoConservazione tipoConservazione);
    List<Conservabilita> findByTipoProdotto(TipoProdotto tipoProdotto);

    List<Conservabilita> findByGiorniMassimaConservazione(int giorniMassimaConservazione);
    List<Conservabilita> findByGiorniMassimaConservazioneBetween(int min, int max);
    List<Conservabilita> findByTipoConservazioneAndTipoProdotto(TipoConservazione tipoConservazione, TipoProdotto tipoprodotto);
    List<Conservabilita> findByProdottoContainingIgnoreCase(String nomeParziale);


}
