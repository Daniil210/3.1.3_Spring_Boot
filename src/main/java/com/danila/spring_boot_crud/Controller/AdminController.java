package com.danila.spring_boot_crud.Controller;


import com.danila.spring_boot_crud.Model.User;
import com.danila.spring_boot_crud.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
@Log
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;


    @GetMapping(value = "/users")
    public String listUsers(Model model, Principal principal ) {
        model.addAttribute("one_user",userService.findByEmail(principal.getName()));
        model.addAttribute("listUsers", userService.findAll());
        return "user";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user) throws ValidationException {
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute(user);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user) throws ValidationException {
        userService.saveUser(user);
        return "redirect:/admin/users";
    }


}

/*@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Log
@CrossOrigin
public class UsersController {

    private final UserService usersService;

    @PostMapping("/save")
    public User saveUsers(@RequestBody User usersDto) throws ValidationException {
        log.info("Handling save users: " + usersDto);
        return usersService.saveUser(usersDto);
    }

    @GetMapping("/findAll")
    public List<User> findAllUsers() {
        log.info("Handling find all users request");
        return usersService.findAll();
    }

    @GetMapping("/findByLogin")
    public User findByLogin(@RequestParam String login) {
        log.info("Handling find by login request: " + login);
        return usersService.findByEmail(login);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long id) {
        log.info("Handling delete user request: " + id);
        usersService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}*/
