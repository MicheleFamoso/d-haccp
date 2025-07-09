package it.epicode.d.haccp.service;

import it.epicode.d.haccp.dto.UserDto;
import it.epicode.d.haccp.enumeration.Role;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.User;
import it.epicode.d.haccp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(UserDto userDto){
        User user = new User();
        user.setNome(userDto.getNome());
        user.setCognome(userDto.getCognome());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    public User creaUtente(UserDto dto, String adminUsername) throws NotFoundException {
        User admin = userRepository.findByUsername(adminUsername)
                .orElseThrow(() -> new NotFoundException("Admin non trovato"));

        User nuovoUtente = new User();
        nuovoUtente.setNome(dto.getNome());
        nuovoUtente.setCognome(dto.getCognome());
        nuovoUtente.setUsername(dto.getUsername());
        nuovoUtente.setEmail(dto.getEmail());
        nuovoUtente.setPassword(passwordEncoder.encode(dto.getPassword()));
       nuovoUtente.setRole(Role.USER);
        nuovoUtente.setAzienda(admin.getAzienda());


        return userRepository.save(nuovoUtente);
    }

    public List<User> getAllUser(){

        return userRepository.findAll();
    }

    public List<User> getAllUserByAdmin(String adminUsername) throws NotFoundException {
        User admin = userRepository.findByUsername(adminUsername)
                .orElseThrow(() -> new NotFoundException("Admin non trovato"));
        return userRepository.findByAzienda(admin.getAzienda());
    }

    public User getUser(int id) throws NotFoundException {
        return userRepository.findById(id).
                orElseThrow(() -> new NotFoundException("User con id " + id + " non trovato"));
    }

    public User updateUser(int id, UserDto userDto) throws NotFoundException {
        User userDaAggiornare = getUser(id);

        userDaAggiornare.setNome(userDto.getNome());
        userDaAggiornare.setCognome(userDto.getCognome());
        userDaAggiornare.setUsername(userDto.getUsername());
        if(!passwordEncoder.matches(userDto.getPassword(), userDaAggiornare.getPassword())) {
            userDaAggiornare.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        return userRepository.save(userDaAggiornare);
    }

    public void deleteUser(int id) throws NotFoundException {
        User userDaCancellare = getUser(id);

        userRepository.delete(userDaCancellare);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
