package com.danila.spring_boot_crud.service;

import com.danila.spring_boot_crud.dao.UserDao;
import com.danila.spring_boot_crud.dto.UsersDto;
import com.danila.spring_boot_crud.exception_handling.ValidationException;
import com.danila.spring_boot_crud.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UsersConverter usersConverter;


    @Transactional
    @Override
    public UsersDto saveUser(UsersDto usersDto) throws ValidationException {
        validateUserDto(usersDto);
        User savedUser = userDao.save(usersConverter.fromUserDtoToUser(usersDto));
        return usersConverter.fromUserToUserDto(savedUser);
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        userDao.deleteById(userId);
    }

    @Override
    public UsersDto findByEmail(String email) {
        User user = userDao.findByEmail(email);
        if(user != null){
            return usersConverter.fromUserToUserDto(user);
        }
        return null;
    }

    @Override
    public UsersDto findById(Long id) {
        Optional<User> users = userDao.findById(id);
        User user = users.get();
        if(user != null){
            return usersConverter.fromUserToUserDto(user);
        }
        return null;
    }

    @Override
    public List<UsersDto> findAll() {
        return userDao.findAll()
                .stream()
                .map(usersConverter::fromUserToUserDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void update(UsersDto usersDto, Long id) throws ValidationException {
        validateUserDto(usersDto);
        usersDto.setId(id);
        User updateUser = userDao.save(usersConverter.fromUserDtoToUser(usersDto));
    }

    private void validateUserDto(UsersDto usersDto) throws ValidationException {
        if (isNull(usersDto)) {
            throw new ValidationException("Object user is null");
        }
        if (isNull(usersDto.getEmail()) || usersDto.getEmail().isEmpty()) {
            throw new ValidationException("Login is empty");
        }
    }


}
