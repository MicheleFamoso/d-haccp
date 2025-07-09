package it.epicode.d.haccp.controller;


import it.epicode.d.haccp.dto.UserDto;
import it.epicode.d.haccp.exception.NotFoundException;
import it.epicode.d.haccp.model.User;
import it.epicode.d.haccp.service.UserService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public User creaDipendente(@RequestBody @Validated UserDto userDto, Principal principal, BindingResult bindingResult) throws NotFoundException {
        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s,e)->s+e));
        }

        return userService.creaUtente(userDto,principal.getName());
    }



    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getAllUsers(Principal principal) throws NotFoundException {
        return userService.getAllUserByAdmin(principal.getName());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User aggiornaUtente(@PathVariable int id, @RequestBody @Validated UserDto userDto, BindingResult bindingResult) throws NotFoundException {
        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream()
                .map(objectError -> objectError.getDefaultMessage())
                .reduce("", (s,e) -> s + e));
        }
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void eliminaUtente(@PathVariable int id) throws NotFoundException {
        userService.deleteUser(id);
    }
}
