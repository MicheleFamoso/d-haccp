package it.epicode.d.haccp.repository;

import it.epicode.d.haccp.enumeration.TipoConservazione;
import it.epicode.d.haccp.enumeration.TipoProdotto;
import it.epicode.d.haccp.model.Conservabilita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConservabilitaRepository extends JpaRepository<Conservabilita,Integer> {


    List<Conservabilita> findByTipoConservazioneAndAziendaId(TipoConservazione tipoConservazione, int aziendaId);
    List<Conservabilita> findByTipoProdottoAndAziendaId(TipoProdotto tipoProdotto, int aziendaId);
    List<Conservabilita> findByGiorniMassimaConservazioneAndAziendaId(int giorni, int aziendaId);
    List<Conservabilita> findByGiorniMassimaConservazioneBetweenAndAziendaId(int min, int max, int aziendaId);
    List<Conservabilita> findByTipoConservazioneAndTipoProdottoAndAziendaId(TipoConservazione tipoConservazione, TipoProdotto tipoprodotto, int aziendaId);
    List<Conservabilita> findByProdottoContainingIgnoreCaseAndAziendaId(String nomeParziale, int aziendaId);
    List<Conservabilita> findByAziendaId(int aziendaId);
}
