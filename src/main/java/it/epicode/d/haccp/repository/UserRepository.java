package it.epicode.d.haccp.repository;

import it.epicode.d.haccp.model.User;
import it.epicode.d.haccp.model.Azienda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    public Optional<User> findByUsername(String username);

    List<User> findByAzienda(Azienda azienda);
}
