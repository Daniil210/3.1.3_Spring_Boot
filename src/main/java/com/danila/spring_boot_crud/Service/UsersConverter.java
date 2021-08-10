/*
package com.danila.spring_boot_crud.Service;

import com.danila.spring_boot_crud.Dto.UserDto;
import com.danila.spring_boot_crud.Model.User;
import org.springframework.stereotype.Component;

@Component
public class UsersConverter {

    public User fromUserDtoToUser(UserDto usersDto) {
        User users = new User();
        users.setId(usersDto.getId());
        users.setEmail(usersDto.getEmail());
        users.setPassword(usersDto.getPassword());
        return users;
    }

    public UserDto fromUserToUserDto(User users) {
        return UserDto.builder()
                .id(users.getId())
                .email(users.getEmail())
                .password(users.getPassword())
                .build();
    }
}
*/
