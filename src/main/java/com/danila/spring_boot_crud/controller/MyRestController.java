package com.danila.spring_boot_crud.controller;

import com.danila.spring_boot_crud.dto.UsersDto;
import com.danila.spring_boot_crud.exception_handling.NoSuchUserException;
import com.danila.spring_boot_crud.exception_handling.ValidationException;
import com.danila.spring_boot_crud.model.User;
import com.danila.spring_boot_crud.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class MyRestController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UsersDto> showListUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public UsersDto getUser(@PathVariable Long id){

        UsersDto user;
        try{
            user = userService.findById(id);
        } catch (NoSuchElementException exc) {
            throw new NoSuchUserException("There is no user with ID = " +
                    id + " in Database");
        }
        return user;
    }
    @GetMapping("/usersFetch")
    public UsersDto getUserFetch(Principal principal){

        return userService.findByEmail(principal.getName());
    }


    @PostMapping("/users")
    public UsersDto addNewUser(@RequestBody UsersDto user) throws ValidationException {

        userService.saveUser(user);
        return user;
    }

    @PutMapping("/users/{id}")
    public UsersDto updateUser(@RequestBody UsersDto user, @PathVariable Long id) throws ValidationException {

        userService.update(user, id);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {

        try {
            userService.deleteUser(id);
        } catch (Exception exc){
            throw new NoSuchUserException("There is no user with ID = " +
                    id + " in Database");
        }
        return "User with ID = " + id + " was Deleted";
    }


}
