package com.danila.spring_boot_crud.service;



import com.danila.spring_boot_crud.dto.UsersDto;
import com.danila.spring_boot_crud.exception_handling.ValidationException;
import com.danila.spring_boot_crud.model.User;

import java.util.List;

public interface UserService {

    UsersDto saveUser(UsersDto userDto) throws ValidationException;

    void deleteUser(Long userId);

    UsersDto findByEmail(String email);

    UsersDto findById(Long id);

    List<UsersDto> findAll();

    void update(UsersDto user, Long id) throws ValidationException;

}
