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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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

}
