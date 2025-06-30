package it.epicode.d.haccp.repository;

import it.epicode.d.haccp.model.Azienda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AziendaRepository extends JpaRepository<Azienda,Integer> {


}
