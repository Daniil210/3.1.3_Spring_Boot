package com.danila.spring_boot_crud.Service;



import com.danila.spring_boot_crud.Model.User;

import java.util.List;

public interface UserService {

    User saveUser(User userDto) ;

    void deleteUser(Long userId);

    User findByEmail(String email);

    User findById(Long id);

    List<User> findAll();

}
