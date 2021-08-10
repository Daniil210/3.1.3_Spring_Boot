package com.danila.spring_boot_crud.Service;

import com.danila.spring_boot_crud.Dao.UserDao;
import com.danila.spring_boot_crud.Model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;


@Service
@AllArgsConstructor
public class DefaultUserService implements UserService {

    private final UserDao userDao;
    //private final UsersConverter usersConverter;

    @Override
    public User saveUser(User user)  {
        //validateUserDto(userDto);
        //User savedUser = userDao.save(user);
        //return usersConverter.fromUserToUserDto(savedUser);
        return userDao.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userDao.deleteById(userId);
    }

    @Override
    public User findByEmail(String email) {

        /*User users = userDao.findByEmail(email);
        if (users != null) {
            return usersConverter.fromUserToUserDto(users);
        }*/
        return userDao.findByEmail(email);
    }

    @Override
    public User findById(Long id) {
        Optional<User> users = userDao.findById(id);
        User user = users.get();
        /*if (user != null) {
            return usersConverter.fromUserToUserDto(user);
        }*/
        return user;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    /*private void validateUserDto(UserDto usersDto) throws ValidationException {
        if (isNull(usersDto)) {
            throw new ValidationException("Object user is null");
        }
        if (isNull(usersDto.getEmail()) || usersDto.getEmail().isEmpty()) {
            throw new ValidationException("Login is empty");
        }
    }*/

}
